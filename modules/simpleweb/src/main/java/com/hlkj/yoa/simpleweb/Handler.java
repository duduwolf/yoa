package com.hlkj.yoa.simpleweb; /**
 * Copyright 2010, taop.cn
 */

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 所有servlet请求的处理接口
 * <p>
 * 所有来自servlet请求需求处理时必须继承此接口，然后由请求类型来选择不同的处理方式
 * 如JavascriptHandler、CssHandler、PngHandler等等
 * 
 * @author duduwolf
 */
public interface Handler {

	/**
	 * 处理来自web的请求
	 * @param request
	 * @param response
	 * @throws java.io.IOException
	 */
	void handle(HttpServletRequest request, HttpServletResponse response) throws IOException;
}
