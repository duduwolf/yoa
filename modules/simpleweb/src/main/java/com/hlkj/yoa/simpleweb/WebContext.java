package com.hlkj.yoa.simpleweb; /**
 * Copyright 2009-2010 taop.cn
 * All rights reserved. 
 */

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 自定义的web容器上下文，通过此容器可在web之外获取Servlet相关变量
 * 
 * @author duduwolf
 */
public interface WebContext {

	/**
	 * 获取session对象
	 * @return
	 */
	HttpSession getSession();
	
	/**
	 * 获取request对象
	 * @return
	 */
	HttpServletRequest getHttpServletRequest();
	
	/**
	 * 获取response对象
	 * @return
	 */
	HttpServletResponse getHttpServletResponse();
	
	/**
	 * 获取ServletConfig对象
	 * @return
	 */
	ServletConfig getServletConfig();
	
	/**
	 * 获取ServletContext对象
	 * @return
	 */
	ServletContext getServletContext();
}
