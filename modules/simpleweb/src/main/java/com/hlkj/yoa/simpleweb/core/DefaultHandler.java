/**
 * Copyright 2010, taop.cn
 */
package com.hlkj.yoa.simpleweb.core;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hlkj.yoa.simpleweb.Handler;

/**
 * 默认的来自Servlet请求的处理器
 * 
 * @author duduwolf
 */
public class DefaultHandler implements Handler {

	/* (non-Javadoc)
	 * @see cn.taop.simpleweb.com.hlkj.yoa.simple.Handler#handle(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		if (request == null) return;
		StringBuffer sb = new StringBuffer();
		sb.append("com.hlkj.yoa.simple.Handler is excuting...\nrequest info list:");
		sb.append("\nrequest.characterEncoding = " + request.getCharacterEncoding());
		sb.append("\nrequest.contextPath = " + request.getContextPath());
		sb.append("\nrequest.localAddr = " + request.getLocalAddr());
		sb.append("\nrequest.protocol = " + request.getProtocol());
		sb.append("\nreqeust.servletPath = " + request.getServletPath());
		sb.append(request.getPathInfo() != null ? new String(request.getPathInfo().getBytes("ISO-8859-1"), "utf-8") : "");
		sb.append("\nrequest.serverName = " + request.getServerName());
		sb.append("\nrequest.serverPort = " + request.getServerPort());
		sb.append("\nrequest address = ")
			.append(request.getProtocol().indexOf("HTTP") >= 0 ? "http://" : "https://")
			.append(request.getServerName())
			.append(request.getServerPort() == 80 ? "" : ":" + request.getServerPort())
			.append(request.getServletPath())
			.append(request.getPathInfo() != null ? new String(request.getPathInfo().getBytes("ISO-8859-1"), "utf-8") : "");
		
		sb.append("\nreqeust.Header.accept = " + request.getHeader("accept"));
		response.setContentType("text/html;charset=utf-8");
		log.debug("工作目录:" + System.getProperty("user.dir"));
		log.debug(getClass().getResource("/").getPath());
		//response.getWriter().println("" + sb.toString().replaceAll("\n", "<br>") + "");
		response.getWriter().flush();
	}

	static final Logger log = LoggerFactory.getLogger(DefaultHandler.class);
}
