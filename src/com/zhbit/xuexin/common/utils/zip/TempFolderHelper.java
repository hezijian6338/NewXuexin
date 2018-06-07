package com.zhbit.xuexin.common.utils.zip;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.UUID;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * Created by zouxiang on 2017/6/5.
 * 临时文件夹工具类
 */
public class TempFolderHelper {
	private String baseFloderPath;

	public TempFolderHelper() {
		String usersHome = System.getProperty("user.home");
		String path = usersHome + File.separator + "myTemp" + File.separator + UUID.randomUUID() + File.separator;
		while (new File(path).exists()) {
			path = usersHome + File.separator + "myTemp" + File.separator + UUID.randomUUID() + File.separator;
		}
		baseFloderPath = path;
	}

	/**
	 * 获取相对于临时文件的文件输出流
	 *
	 * @param path
	 *            文件夹路径
	 * @param fileName
	 *            文件名称
	 * @return 输出流
	 */
	public File getFile(String path, String fileName) throws FileNotFoundException {
		// 防止路径分隔符不统一
		path = path.replace("\\", File.separator).replace("/", File.separator);
		// 去除路径头部的路径分隔符
		if (path.startsWith(File.separator)) {
			path = path.substring(1);
		}
		File file = new File(baseFloderPath + path);
		// 不存在则创建
		if (!file.exists()) {
			file.mkdirs();
		}
		return new File(file, fileName);
	}
	/**
	 * 获取相对于临时文件的文件输出流
	 * @Title: getFile
	 * @Description: TODO
	 * @param fileName
	 * @return
	 * @throws FileNotFoundException
	 * @return: File
	 */
	public File getFile(String fileName) throws FileNotFoundException {
		// 防止路径分隔符不统一
		fileName = fileName.replace("\\", File.separator).replace("/", File.separator);
		int i = fileName.lastIndexOf(File.separator);
		if(i != -1){
			return getFile(fileName.substring(0,i+1),fileName.substring(i+1));
		}
		return getFile("",fileName);
	}

	/**
	 * 删除该文件夹
	 */
	public boolean drop() {
		return deleteDir(new File(baseFloderPath));
	}

	/**
	 * 递归删除目录下的所有文件及子目录下所有文件
	 * 
	 * @param dir
	 *            将要删除的文件目录
	 * @return boolean Returns "true" if all deletions were successful. If a
	 *         deletion fails, the method stops attempting to delete and returns
	 *         "false".
	 */
	private static boolean deleteDir(File dir) {
		if(!dir.exists()){
			return true;
		}
		if (dir.isDirectory()) {
			File[] childrens = dir.listFiles();
			if (null != childrens) {
				// 递归删除目录中的子目录下
				for (File children : childrens) {
					boolean success = deleteDir(children);
					if (!success) {
						return false;
					}
				}
			}
		}
		// 目录此时为空，可以删除
		return dir.delete();
	}

}
