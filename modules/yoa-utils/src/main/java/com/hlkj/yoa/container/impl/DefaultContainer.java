/**
 * Copyright 2009-2010 taop.cn
 * All rights reserved. 
 */
package com.hlkj.yoa.container.impl;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hlkj.yoa.container.Container;
import com.hlkj.yoa.utils.LocalUtil;

/**
 * Ioc容器的默认实现类
 * 
 * @author duduwolf
 */
public class DefaultContainer implements Container {

	/**
	 * 添加bean到容器里
	 * @param base bean实现的接口
	 * @param bean bean的一个实现
	 */
	public <T> void addBean(Class<T> base, T bean) {
		addParameter(base.getName(), bean);
	}

	public void addParameter(String askFor, Object valueParam)
			throws ContainerException {
		Object value = valueParam;

		//如果value是一个String，value就是一个需要实例化的类名称
		if (value instanceof String) {
			try {
				Class<?> impl = LocalUtil.classForName((String) value);
				value = impl.newInstance();
			} catch (ClassNotFoundException ex) {
				//throw new ContainerException(ex);
			} catch (InstantiationException ex) {
				throw new ContainerException("Unable to instantiate " + value);
			} catch (IllegalAccessException ex) {
				throw new ContainerException("Unable to access " + value);
			}
		}

		//value是askFor的类实现
		if (!(value instanceof String)) {
			try {
				Class<?> iface = LocalUtil.classForName(askFor);
				if (!iface.isAssignableFrom(value.getClass())) {
					log.error("Can't cast: " + value + " to " + askFor);
				}
			} catch (ClassNotFoundException ex) {
				//throw new ContainerException(ex);
			}
		}

		log.debug("value: {} = {}", askFor, value);
		log.debug("impl: {} = {}", askFor, value.getClass().getName());

		beans.put(askFor, value);
	}

	public void setupFinished() {
		for (Object bean : beans.values()) {
			initializeBean(bean);
		}

		callInitializingBeans();
	}

	public void initializeBean(Object bean) {

		if (bean instanceof ServletContext || bean instanceof ServletConfig) {
			log.debug("skipping injecting into: {}", bean.getClass().getName());
			return;
		}

		if (!(bean instanceof String)) {
			log.debug("autowire: {}", bean.getClass().getName());

			Method[] methods = bean.getClass().getMethods();
			methods: for (Method setter : methods) {
				if (setter.getName().startsWith("set")
						&& setter.getName().length() > 3
						&& setter.getParameterTypes().length == 1) {
					String name = Character.toLowerCase(setter.getName()
							.charAt(3))
							+ setter.getName().substring(4);
					Class<?> propertyType = setter.getParameterTypes()[0];

					// 首先尝试通过bean的name进行自动装载类实例
					Object setting = beans.get(name);
					if (setting != null) {
						if (propertyType.isAssignableFrom(setting.getClass())) {
							log.debug("by name: {} = {}", name, setting);
							invoke(setter, bean, setting);

							continue methods;
						} else if (setting.getClass() == String.class) {
							try {
								Object value = LocalUtil.simpleConvert(
										(String) setting, propertyType);

								log.debug("by name: {} = {}", name, value);
								invoke(setter, bean, value);
							} catch (IllegalArgumentException ex) {
								// Ignore - this was a speculative convert
								// anyway
							}

							continue methods;
						}
					}

					// 其次尝试通过type进行自动装载
					Object value = beans.get(propertyType.getName());
					if (value != null) {
						log.debug("by type: {} = {}", name, value.getClass().getName());
						invoke(setter, bean, value);

						continue methods;
					}

					log.debug("no properties for: {}", name);
				}
			}
		}
	}

	private static void invoke(Method setter, Object bean, Object value) {
		try {
			setter.invoke(bean, value);
		} catch (InvocationTargetException ex) {
			log.error("Exception during auto-wire: ", ex.getTargetException());
		} catch (Exception ex) {
			log.error("Error calling setter: " + setter, ex);
		}
	}

	protected void callInitializingBeans() {
		Collection<String> beanNames = getBeanNames();
		
		for (String name : beanNames) {
			Object bean = getBean(name);
			
			if (bean instanceof InitializingBean) {
				InitializingBean startMeUp = (InitializingBean) bean;
				startMeUp.afterContainerSetup(this);
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * @see cn.taop.simpleweb.Container#getBean(java.lang.Class)
	 */
	@Override
	public <T> T getBean(Class<T> type) {
		Object bean = getBean(type.getName());
		try {
			return type.cast(bean);
		} catch (ClassCastException ex) {
			log.error("ClassCastException: Asked for implementation of "
					+ type.getName() + " but the container has a type "
					+ bean.getClass().getName());
			log.error("容器里没有找到对应{}类型的bean实例", type.getName());
			throw ex;
		}
	}

	/*
	 * (non-Javadoc)
	 * @see cn.taop.simpleweb.Container#getBean(java.lang.String)
	 */
	@Override
	public Object getBean(String name) {
		Object result = beans.get(name);
		if (result == null) {
			log.warn("在容器里没有找到对应key({})的bean实例：", name);
		}

		return result;
	}

	/*
	 * (non-Javadoc)
	 * @see cn.taop.simpleweb.Container#getBeanNames()
	 */
	@Override
	public Collection getBeanNames() {
		return Collections.unmodifiableCollection(beans.keySet());
	}

	/**
	 * 用来装载bean实例的容器，在这里用TreeMap实例化容器， 通过getBeanNames可得到固定排序的bean集合
	 */
	protected Map beans = new TreeMap();

	static final Logger log = LoggerFactory.getLogger(DefaultContainer.class);
}
