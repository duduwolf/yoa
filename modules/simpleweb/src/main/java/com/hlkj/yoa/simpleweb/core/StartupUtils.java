/**
 * Copyright 2010, taop.cn
 */
package com.hlkj.yoa.simpleweb.core;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;

import com.hlkj.yoa.container.Container;
import com.hlkj.yoa.container.ContainerFactory;
import com.hlkj.yoa.container.impl.DefaultContainer;
import com.hlkj.yoa.simpleweb.Handler;
import com.hlkj.yoa.simpleweb.WebContextFactory;

/**
 * 系统加载时的启动助手类
 * 
 * @author duduwolf
 */
public class StartupUtils {

	public static void initWebContext(ServletConfig config,
			ServletContext servletContext) {
		WebContextFactory.set(null, null, config, servletContext);
	}

	public static Container initContainer() {
		DefaultContainer container = new DefaultContainer();
		
		container.addBean(Handler.class, new DefaultHandler());
		
		//设置默认的容器，并装载入容器工厂
		ContainerFactory.setContainer(container, Constants.SIMPLEWEB_CODE);
		
		return container;
	}
}
