package com.hlkj.yoa.simpleweb.core;

import static org.junit.Assert.*;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;

import com.hlkj.yoa.simpleweb.Handler;
import com.hlkj.yoa.simpleweb.SimpleWebServlet;
import org.easymock.classextension.EasyMock;
import org.junit.Assert;
import org.junit.Test;

import com.hlkj.yoa.container.ContainerFactory;
import com.hlkj.yoa.simpleweb.core.Constants;
import com.hlkj.yoa.simpleweb.Handler;
import com.hlkj.yoa.simpleweb.SimpleWebServlet;


public class DefaultHandlerTest {

	@Test
	public void testHandle() {
		SimpleWebServlet simple = new SimpleWebServlet();
		ServletConfig config = EasyMock.createMock(ServletConfig.class);
		try {
			simple.init(config);
		} catch (ServletException e) {
			e.printStackTrace();
			Assert.fail(e.getMessage());
		}
		
		Handler handler = ContainerFactory.getContainer(Constants.SIMPLEWEB_CODE).getBean(Handler.class);
		try {
			handler.handle(null, null);
		} catch (IOException e) {
			e.printStackTrace();
			Assert.fail(e.getMessage());

		}
	}

}
