package com.hlkj.yoa.container;


import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hlkj.yoa.container.impl.DefaultContainer;

/**
 * 容器工厂
 * @author <a href="malito:denglq@wanwei.com.cn">邓利强</a>
 */
public class ContainerFactory {

	/**
	 * 手动添加容器到工厂中
	 * @param _container  容器
	 * @param code 该容器专有的编码
	 */
	public static void setContainer(Container _container, String code) {
		checkContainerCode(code);
		
		if (containerMap.containsKey(code)) {
			log.warn("当前指定的容器编码[{}]已存在", code);
		}
		containerMap.put(code, _container);
	}

	/**
	 * 获得容器
	 * @param code 要获得的容器的编码
	 * @return
	 */
	public static Container getContainer(String code) {
		checkContainerCode(code);
		Container container = (Container)containerMap.get(code);
		if (container == null) {
			container = new DefaultContainer();
			containerMap.put(code, container);
		}
		
		return container;
	}
	
	public static Object getBean(String id, String code) {
		checkContainerCode(code);
		return getContainer(code).getBean(id);
	}
	
	/**
	 * 检查容器编码有效性<br/>
	 * 容器编码必须为有效的字符串，不能为null，也不能为空字符串
	 * @param code 容器编码
	 */
	private static void checkContainerCode(String code) {
		if (code == null || code.length() == 0) {
			throw new ContainerFactoryException("没有指定容器编码");
		}
	}
	
	private static Map containerMap = new HashMap();
	
	static final Logger log = LoggerFactory.getLogger(ContainerFactory.class);
}
