package com.zhbit.xuexin.common.utils;

import java.util.Date;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;

public class JsonUtil {

	/**
	 * 为树状对象构造Json
	 * 
	 * @param m
	 *            对象
	 * @param hasChildren
	 *            是否有叶节点
	 */
	public static JSONObject jsonObjectForTree(Object m, Boolean hasChildren) {
		try {
			JSONObject jo = new JSONObject();;
			jo = JSONObject.fromObject(m,jsonConfig);
			if (hasChildren) {// 孩子 有自己的孩子
				jo.put("state", "closed");
			} else {
				jo.put("state", "open");
			}
			return jo;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 为对象构造Json
	 * 
	 * @param m
	 *            对象
	 * @param hasChildren
	 *            是否有叶节点
	 */
	public static Object jsonObject(Object m) {
		try {
			Object json;
			if (m instanceof JSONArray) {
				json = JSONArray.fromObject(m, jsonConfig);
			} else {
				json = JSONObject.fromObject(m, jsonConfig);
			}
			return json;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	/**
	 * 为对象构造Json
	 * 
	 * @param m
	 *            对象
	 * @param hasChildren
	 *            是否有叶节点
	 */
	public static JSONArray jsonArray(Object m) {
		try {
			JSONArray json = JSONArray.fromObject(m, jsonConfig);
			return json;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	private static JsonConfig jsonConfig = new JsonConfig();
	// 当输出时间格式时，采用和JS兼容的格式输出
	private static JsonValueProcessor jvprocess = new JsonValueProcessor() {

		public Object processArrayValue(Object arg0, JsonConfig arg1) {
			if (null != arg0) {
				return ((Date) arg0).getTime();
			} else {
				return "";
			}
		}

		public Object processObjectValue(String arg0, Object arg1,
				JsonConfig arg2) {
			if (null != arg1) {
				return ((Date) arg1).getTime();
			} else {
				return "";
			}
		}
	};
	static {
		jsonConfig.registerJsonValueProcessor(Date.class, jvprocess);
	}

}
