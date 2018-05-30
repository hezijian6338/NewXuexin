package com.zhbit.xuexin.common.utils;

import java.io.*;

/**
 * 过滤bom，如果有则正确解析
 */
public class UnicodeInputStream extends InputStream {
	public static void main(String[] args) throws IOException {
		String path = "E:/xxx.txt";
		BufferedReader br = new BufferedReader(new InputStreamReader(new UnicodeInputStream(new FileInputStream(path), "utf-8")));
		String s = br.readLine();
		System.out.println(s);
		br.close();
	}

	private PushbackInputStream internalIn;
	private boolean isInited = false;
	private String defaultEnc;
	private String encoding;

	private static final int BOM_SIZE = 4;

	public UnicodeInputStream(InputStream in, String defaultEnc) {
		internalIn = new PushbackInputStream(in, BOM_SIZE);
		this.defaultEnc = defaultEnc;
	}

	public UnicodeInputStream(InputStream in) {
		internalIn = new PushbackInputStream(in, BOM_SIZE);
		this.defaultEnc = "utf-8";
	}

	public String getDefaultEncoding() {
		return defaultEnc;
	}

	public String getEncoding() {
		if (!isInited) {
			try {
				init();
			} catch (IOException ex) {
				IllegalStateException ise = new IllegalStateException("Init method failed.");
				ise.initCause(ise);
				throw ise;
			}
		}
		return encoding;
	}

	/**
	 * Read-ahead four bytes and check for BOM marks. Extra bytes are unread
	 * back to the stream, only BOM bytes are skipped.
	 */
	private void init() throws IOException {
		if (isInited)
			return;
		byte bom[] = new byte[BOM_SIZE];
		int n, unread;
		n = internalIn.read(bom, 0, bom.length);

		if ((bom[0] == (byte) 0x00) && (bom[1] == (byte) 0x00) && (bom[2] == (byte) 0xFE) && (bom[3] == (byte) 0xFF)) {
			encoding = "UTF-32BE";
			unread = n - 4;
		} else if ((bom[0] == (byte) 0xFF) && (bom[1] == (byte) 0xFE) && (bom[2] == (byte) 0x00) && (bom[3] == (byte) 0x00)) {
			encoding = "UTF-32LE";
			unread = n - 4;
		} else if ((bom[0] == (byte) 0xEF) && (bom[1] == (byte) 0xBB) && (bom[2] == (byte) 0xBF)) {
			encoding = "UTF-8";
			unread = n - 3;
		} else if ((bom[0] == (byte) 0xFE) && (bom[1] == (byte) 0xFF)) {
			encoding = "UTF-16BE";
			unread = n - 2;
		} else if ((bom[0] == (byte) 0xFF) && (bom[1] == (byte) 0xFE)) {
			encoding = "UTF-16LE";
			unread = n - 2;
		} else {
			encoding = defaultEnc;
			unread = n;
		}
		if (unread > 0) {
			internalIn.unread(bom, (n - unread), unread);
		}
		isInited = true;
	}

	public void close() throws IOException {
		init();
		internalIn.close();
	}

	public int read() throws IOException {
		init();
		return internalIn.read();
	}
}
