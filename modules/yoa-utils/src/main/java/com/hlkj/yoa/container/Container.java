package com.hlkj.yoa.container;
/**
 * Copyright 2009-2010 taop.cn
 * All rights reserved. 
 */


import java.util.Collection;

/**
 * 简易IoC容器
 * 
 * @author duduwolf
 */
public interface Container {

	/**
	 * 通过指定Class得到bean实例
	 * @param <T>
	 * @param type
	 * @return
	 */
	<T> T getBean(Class<T> type);
	 
	/**
	 * 通过指定key得到bean实例
	 * @param <T> 返回bean实例
	 * @param name 存储bean的key
	 * @return
	 */
	Object getBean(String name);
	
	/**
	 * 得到容器类所有bean的集合
	 * @return
	 */
	Collection getBeanNames();
}
