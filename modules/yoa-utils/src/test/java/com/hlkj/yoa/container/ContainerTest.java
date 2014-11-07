package com.hlkj.yoa.container;


import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hlkj.yoa.container.impl.DefaultContainer;

public class ContainerTest {
	
	private static final String CODE = "CONTAINER_TEST";

	@Before
	public void setUp() throws Exception {
		container = ContainerFactory.getContainer(CODE);
	}

	@After
	public void tearDown() throws Exception {
	}

	/**
	 * 测试容器工厂
	 */
	@Test
	public void testContainerFactory() {
		Container _container = ContainerFactory.getContainer(CODE);
		
		assertNotNull(_container);
	}
	
	/**
	 * 测试容器工厂手动注入容器
	 */
	@Test
	public void testContainerAutoware() {
		int len = 2;
		
		DefaultContainer _container = new DefaultContainer();
		_container.addBean(Map.class, new HashMap());
		
		Map map = new HashMap();
		map.put("key1", "value1");
		
		_container.addParameter("map", map);
		
		assertEquals(len, _container.getBeanNames().size());
		assertEquals(0, container.getBeanNames().size());
		
		ContainerFactory.setContainer(_container, CODE);
		container = ContainerFactory.getContainer(CODE);
		assertEquals(len, container.getBeanNames().size());
		
		assertEquals(_container, container);
	}
	
	/**
	 * 测试从容器中获取bean
	 */
	@Test
	public void testContainerGetBean() {
		//assertEquals(2, container.getBeanNames().size());
		
		Object o1 = container.getBean(Map.class);
		assertNotNull(o1);
		assertEquals(o1.getClass(), HashMap.class);
		
		Object o2 = container.getBean("map");
		assertNotNull(o2);
		assertEquals("value1", ((Map)o2).get("key1"));
	}
	
	private Container container = null;
	
	static final Logger log = LoggerFactory.getLogger(ContainerTest.class);
}
