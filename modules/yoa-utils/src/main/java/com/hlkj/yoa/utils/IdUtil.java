package com.hlkj.yoa.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class IdUtil {
	private static String step = "00001";// 每天最多有99999个订单，可按需求调节
	private static Map idMap = new HashMap();

	static {
		long maxId = 0L;
		maxId = getMaxIdFromDB();
		if (maxId != 0) {
			String key = (maxId + "").substring(0, 8);
			Long value = new Long(maxId);
			idMap.put(key, value);
		}
	}

	// 项目第一次启动或重启时，从数据库拿到最大的id,数据库里没有记录,则返回0
	public static long getMaxIdFromDB() {
		// return 0L;//数据库里没有记录的情况
		return 2009071000002L;// 项目重启且数据库里有记录的情况
	}

	public static synchronized long getId() {
		long id = 0L;

		String key = new SimpleDateFormat("yyyyMMdd").format(new Date());
		Long value = (Long) idMap.get(key);
		if (value == null) {
			idMap.clear();
			id = Long.parseLong(key + step);
		} else {
			id = value.longValue() + 1;
		}
		idMap.put(key, new Long(id));

		return id;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		long id1 = getId();
		long id2 = getId();
		System.out.println(id1 + "," + id2);
	}

}
