/**
 * Copyright 2009-2010 taop.cn
 * All rights reserved. 
 */
package com.hlkj.yoa.container.impl;

/**
 * 容器相关的异常
 * 
 * @author duduwolf
 */
public class ContainerException extends RuntimeException {

	private static final long serialVersionUID = -1861502468285532180L;

	public ContainerException(String message) {
		super(message);
	}
	
	public ContainerException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public ContainerException(Throwable cause) {
		super(cause);
	}
}
