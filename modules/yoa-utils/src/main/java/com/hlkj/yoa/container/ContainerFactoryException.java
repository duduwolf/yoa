/**
 * Copyright 2009-2010 taop.cn
 * All rights reserved. 
 */
package com.hlkj.yoa.container;

/**
 * 容器相关的异常
 * 
 * @author duduwolf
 */
public class ContainerFactoryException extends RuntimeException {

	private static final long serialVersionUID = 665097383668634902L;

	public ContainerFactoryException(String message) {
		super(message);
	}
	
	public ContainerFactoryException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public ContainerFactoryException(Throwable cause) {
		super(cause);
	}
}
