package com.zhbit.xuexin.common.utils;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;

import org.apache.log4j.Logger;
import org.dom4j.Document;

/**
 * 输出到HttpServletResponse工具类
 * 
 * @author 梁日宇
 * @email 1912813835@qq.com
 * 
 */
public class OutUtil {
	private static Logger log = Logger.getLogger(OutUtil.class);
	private static String charsetName = "utf-8";

	private static String getFileContentType(String name) {
		String s = "";
		String n = name.toLowerCase();
		if (n.endsWith(".png")) {
			s = "image/png";
		} else if (n.endsWith(".gif")) {
			s = "image/gif";
		} else if (n.endsWith(".jpg") || n.endsWith(".jpeg")) {
			s = "image/jpeg";
		} else if (n.endsWith(".doc")) {
			s = "application/msword";
		} else if (n.endsWith(".xls")) {
			s = "application/x-excel";
		} else if (n.endsWith(".zip")) {
			s = "application/zip";
		} else if (n.endsWith(".pdf")) {
			s = "application/pdf";
		}else {
			s = "application/octet-stream";
		}
		return s;
	}

	/**
	 * 输出下载文件
	 * 
	 * @param m
	 * @param response
	 */
	public static void outDownFile(byte[] b, String downName, HttpServletResponse response) {
		try {
			downName = new String(downName.getBytes("GBK"), "iso8859-1");
			response.setHeader("content-disposition", "attachment;filename=" + downName);
			response.setContentType(getFileContentType(downName) + "; charset=" + charsetName);
			response.setContentLength(b.length);
			out(new ByteArrayInputStream(b), response.getOutputStream());
		} catch (Exception err) {
			log.error(err.getMessage(), err);
		}
	}

	/**
	 * 输出下载文件
	 * 
	 * @param m
	 * @param response
	 */
	public static void outDownFile(String path, String downName, HttpServletResponse response) {
		try {
			downName = new String(downName.getBytes("GBK"), "iso8859-1");
			response.setHeader("content-disposition", "attachment;filename=" + downName);
			response.setContentType("application/octet-stream; charset=" + charsetName);
			File file = new File(path);
			response.setContentLength((int) file.length());
			out(new FileInputStream(file), response.getOutputStream());
		} catch (Exception err) {
			log.error(err.getMessage(), err);
		}
	}

	/**
	 * 输出下载文件
	 * 
	 * @param m
	 * @param response
	 */
	public static void outOpenFile(String path, HttpServletResponse response) {
		try {
			File file = new File(path);
			response.setContentLength((int) file.length());
			out(new FileInputStream(file), response.getOutputStream());
		} catch (Exception err) {
			log.error(err.getMessage(), err);
		}
	}

	/**
	 * 输出字符信息
	 * 
	 * @param m
	 * @param response
	 */
	public static void outString(String s, HttpServletResponse response) {
		try {
			byte[] b = s.getBytes(charsetName);
			response.setContentType("text/html; charset=" + charsetName);
			response.setContentLength(b.length);
			out(new ByteArrayInputStream(b), response.getOutputStream());
		} catch (Exception err) {
			log.error(err.getMessage(), err);
		}
	}

	/**
	 * 输出Json
	 * 
	 * @param m
	 * @param response
	 */
	public static void outJson(Object m, HttpServletResponse response) {
		try {
			Object json;
			if (m instanceof JSONArray) {
				json = JSONArray.fromObject(m, jsonConfig);
			} else {
				json = JSONObject.fromObject(m, jsonConfig);
			}
			//log.info("\r\n===>"+json.toString());
			byte[] b = json.toString().getBytes(charsetName);
			response.setContentType("text/html; charset=" + charsetName);
			response.setContentLength(b.length);
			out(new ByteArrayInputStream(b), response.getOutputStream());
		} catch (Exception err) {
			log.error(err.getMessage(), err);
		}
	}
	
	
	/**
	 * 输出Json
	 * 
	 * @param m
	 * @param response
	 */
	public static void outJsonByNgboss(Object m, HttpServletResponse response) {
		try {
			Object json;
			byte[] b;
			if (m instanceof JSONArray) {
				json = JSONArray.fromObject(m, jsonConfig);
			} else {
				json = JSONObject.fromObject(m, jsonConfig);
			}
			if(json.toString()==null||json.toString().equals("null")){
				b = "".getBytes(charsetName);
			}else{
				 b = json.toString().getBytes(charsetName);
			}
			log.info("\r\n===>"+json.toString());
			response.setContentType("text/html; charset=" + charsetName);
			response.setContentLength(b.length);
			out(new ByteArrayInputStream(b), response.getOutputStream());
		} catch (Exception err) {
			log.error(err.getMessage(), err);
		}
	}
	
	
	/**
	 * 输出Json
	 * 
	 * @param m
	 * @param response
	 */
	public static String outJson(Object m) {
		try {
			Object json;
			if (m instanceof JSONArray) {
				json = JSONArray.fromObject(m, jsonConfig);
			} else {
				json = JSONObject.fromObject(m, jsonConfig);
			}
			return json.toString();
		} catch (Exception err) {
			log.error(err.getMessage(), err);
			return null;
		}
	}
	

	/**
	 * 输出XML
	 * 
	 * @param document
	 * @param response
	 * @param charsetName
	 */
	public static void outXml(Document document, HttpServletResponse response) {
		try {
			document.setXMLEncoding(charsetName);
			byte[] b = document.asXML().getBytes(charsetName);
			response.setContentType("text/xml; charset=" + charsetName);
			response.setContentLength(b.length);
			out(new ByteArrayInputStream(b), response.getOutputStream());
		} catch (Exception err) {
			log.error(err.getMessage(), err);
		}
	}

	/**
	 * 基础字节数组输出
	 * 
	 * @param b
	 * @param response
	 */
	public static void out(InputStream is, OutputStream os) {
		try {
			byte[] buffer = new byte[10240];
			int length = -1;
			while ((length = is.read(buffer)) != -1) {
				os.write(buffer, 0, length);
				os.flush();
			}
		} catch (Exception err) {
			log.error(err.getMessage(), err);
		} finally {
			try {
				os.close();
			} catch (IOException e) {
			}
			try {
				is.close();
			} catch (IOException e) {
			}
		}
	}

	private static JsonConfig jsonConfig = new JsonConfig();
	private static JsonValueProcessor jvprocess = new JsonValueProcessor() {

		public Object processArrayValue(Object arg0, JsonConfig arg1) {
			if (null != arg0) {
				return ((Date) arg0).getTime();
			} else {
				return "";
			}
		}

		public Object processObjectValue(String arg0, Object arg1, JsonConfig arg2) {
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
