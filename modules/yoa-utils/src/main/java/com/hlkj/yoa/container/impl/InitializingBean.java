/**
 * Copyright 2009-2010 taop.cn
 * All rights reserved. 
 */
package com.hlkj.yoa.container.impl;

import com.hlkj.yoa.container.Container;

/**
 * 继承了此接口的bean，可以自定义类的初始化动作
 * 
 * @author duduwolf
 */
public interface InitializingBean {

	void afterContainerSetup(Container container);
}
