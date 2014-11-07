package com.hlkj.yoa.simpleweb;

import static org.junit.Assert.*;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import junit.framework.Assert;

import org.easymock.classextension.EasyMock;
import org.junit.Test;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

public class SimpleWebServletTest extends BaseTest {

	@Test
	public void testServlet() {
		HttpServletRequest request = EasyMock.createMock(HttpServletRequest.class);
		HttpServletResponse response = EasyMock.createMock(HttpServletResponse.class);
		ServletConfig config = EasyMock.createMock(ServletConfig.class);
		ServletContext context = EasyMock.createMock(ServletContext.class);
		
		SimpleWebServlet simple = new SimpleWebServlet();
		try {
			simple.init(config);
		} catch (ServletException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
		//EasyMock.expect(config.getServletContext()).andReturn(context).anyTimes();
		//EasyMock.expect(request.getParameter("userName")).andReturn("Denglq");
		
//		PrintWriter pw = new PrintWriter(System.out, true);
//		try {
//			EasyMock.expect(response.getWriter()).andReturn(pw).anyTimes();
//		} catch (IOException e) {
//			e.printStackTrace();
//			fail(e.getMessage());
//		}
//		
//		EasyMock.replay(request);
//		EasyMock.replay(response);
////		EasyMock.replay(config);
////		EasyMock.replay(context);
//		
//		try {
//			simple.doPost(request, response);
//		} catch (ServletException e) {
//			e.printStackTrace();
//			fail(e.getMessage());
//		} catch (IOException e) {
//			fail(e.getMessage());
//			e.printStackTrace();
//		}
//		
//		pw.flush();
//		
//		EasyMock.verify(request);
//		EasyMock.verify(response);
//		EasyMock.verify(config);
//		EasyMock.verify(context);
	}
	
	@Test
	public void testServlet2() throws ClientProtocolException, IOException {
		HttpClient httpclient = new DefaultHttpClient();
        HttpGet httpGet = new HttpGet( "http://localhost:8080/taop/simpleweb?id=6" );
        HttpResponse response = httpclient.execute( httpGet );

        Assert.assertEquals( 200, response.getStatusLine().getStatusCode() );
        log.debug(EntityUtils.toString(response.getEntity()));
	}
}
