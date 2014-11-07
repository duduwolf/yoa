package com.hlkj.yoa.simpleweb; /**
 * Copyright 2009-2010 taop.cn
 * All rights reserved. 
 */

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hlkj.yoa.simpleweb.core.DefaultWebContext;

/**
 * WebContext上下文容器的生产工厂，WebContext实例必须通过工厂获得
 * 
 * @author duduwolf
 */
public class WebContextFactory {
	
	public static WebContext get() {
		return (WebContext) user.get();
	}
	
	public static void set(HttpServletRequest request, HttpServletResponse response, ServletConfig config, ServletContext context) {
		try {
			WebContext wc = new DefaultWebContext(request, response, config, context);
			user.set(wc);
		} catch (Exception e) {
			log.error("严重错误，错误原因为：{}", e.getMessage());
		}
	}
	
	public void unset() {
		user.set(null);
	}
	
	private static ThreadLocal<WebContext> user = new ThreadLocal<WebContext>();
	private static final Logger log = LoggerFactory.getLogger(WebContextFactory.class);
}
