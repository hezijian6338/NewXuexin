package com.zhbit.xuexin.common.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * 获取配置文件
 * 
 * 
 */
public class PropUtil {

	/**
	 * 获取配置文件
	 * 
	 * @return
	 */
	public static Map<String, String> read() {
		Map<String, String> map = new HashMap<String, String>();
		try {
			Properties p = new Properties();
			InputStream in = PropUtil.class.getResourceAsStream("/common.properties");
			p.load(in);
			for (Object key : p.keySet()) {
				String value = p.getProperty((String) key);
				map.put((String) key, value);
			}
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}

	/**
	 * 获取配置文件
	 * 
	 * @return
	 */
	public static Properties read(String path) {		
		try {
			Properties p = new Properties();
			InputStreamReader in = new InputStreamReader(PropUtil.class.getResourceAsStream(path),"utf-8");
			p.load(in);
			return p;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 获取配置文件
	 * 
	 * @return
	 */
	public static void write(Properties p, String path) {
		try {
			File file = new File(path);
			if (file.exists()) {
				file.delete();
			}
			OutputStream out = new FileOutputStream(file);
			p.store(out, "Auto born! Not changed!");
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
