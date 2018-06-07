package com.zhbit.xuexin.common.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.zip.Adler32;
import java.util.zip.CheckedInputStream;

/**
 * 文件操作工具
 * 
 */
public class FileOP {
	private static final int buffer = 10240;

	/**
	 * 读取文件
	 * 
	 * @param file
	 * @return
	 * @throws Exception
	 */
	public static byte[] readFile(File file) throws Exception {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		InputStream fis = new UnicodeInputStream(new FileInputStream(file));
		byte[] b = new byte[buffer];
		int pos = -1;
		while (-1 != (pos = fis.read(b))) {
			baos.write(b, 0, pos);
		}
		byte[] all = baos.toByteArray();
		fis.close();
		baos.close();
		return all;

	}

	public static byte[] readFile(String path) throws Exception {
		return readFile(new File(path));
	}

	public static String readFile(String path, String encoding) throws Exception {
		return new String(readFile(new File(path)), encoding);
	}

	public static String readFile(File file, String encoding) throws Exception {
		return new String(readFile(file), encoding);
	}

	/**
	 * 写入文件
	 * 
	 * @param path
	 *            路径
	 * @param file
	 *            文件
	 * @throws Exception
	 */
	public static void writeFile(OutputStream os, InputStream is) throws Exception {
		byte[] b = new byte[buffer];
		int pos = -1;
		while (-1 != (pos = is.read(b))) {
			os.write(b, 0, pos);
		}
		os.flush();
		os.close();
		is.close();
	}

	public static void writeFile(String path, String content, String encoding, boolean append) throws Exception {
		File f = new File(path);
		f.getParentFile().mkdirs();
		OutputStream os = new FileOutputStream(f, append);
		InputStream is = new ByteArrayInputStream(content.getBytes(encoding));
		writeFile(os, is);
	}

	public static void writeFile(String path, String content, String encoding) throws Exception {
		writeFile(path, content, encoding, false);
	}

	public static void writeFile(OutputStream os, String content, String encoding) throws Exception {
		InputStream is = new ByteArrayInputStream(content.getBytes(encoding));
		writeFile(os, is);
	}

	public static void writeFile(OutputStream os, byte[] b) throws Exception {
		InputStream is = new ByteArrayInputStream(b);
		writeFile(os, is);
	}

	public static void writeFile(String path, File file) throws Exception {
		File f = new File(path);
		f.getParentFile().mkdirs();
		OutputStream os = new FileOutputStream(f);
		InputStream is = new FileInputStream(file);
		writeFile(os, is);
	}

	public static void writeFile(String path, byte[] b) throws Exception {
		File f = new File(path);
		f.getParentFile().mkdirs();
		OutputStream os = new FileOutputStream(f);
		InputStream is = new ByteArrayInputStream(b);
		writeFile(os, is);
	}
	
	/**
	 * 获取文件校验码
	 * @param in
	 * @return
	 * @throws Exception
	 */
	public static long getCheckKey(InputStream in) throws Exception {
		CheckedInputStream checked = new CheckedInputStream(in, new Adler32());
		byte[] b = new byte[10240];
		while ((checked.read(b)) != -1) {
		}
		in.close();
		checked.close();
		return checked.getChecksum().getValue();
	}
}
