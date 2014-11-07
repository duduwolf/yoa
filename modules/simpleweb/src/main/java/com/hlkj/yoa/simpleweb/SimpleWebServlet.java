package com.hlkj.yoa.simpleweb; /**
 * Copyright 2010, taop.cn
 */

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hlkj.yoa.container.Container;
import com.hlkj.yoa.simpleweb.core.StartupUtils;

/**
 * 这个servlet是SimpleWeb运行时的总入口
 * <p>
 * 
 * @author duduwolf
 */
public class SimpleWebServlet extends HttpServlet {

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		
		ServletContext servletContext = (ServletContext) config.getServletContext();
		
		//初始化容器，把系统中要用到的常用类全部加载到容器中
		container = StartupUtils.initContainer();
		//初始化Web上下文
		StartupUtils.initWebContext(config, servletContext);
		
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		Handler handler = (Handler) container.getBean(Handler.class.getName());
		handler.handle(req, resp);
	}

	private static Container container = null;
}
