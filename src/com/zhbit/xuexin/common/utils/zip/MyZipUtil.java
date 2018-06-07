package com.zhbit.xuexin.common.utils.zip;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.zip.CRC32;
import java.util.zip.CheckedOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * Created by zouxiang on 2017/6/5.
 * 文件压缩工具类
 */
public class MyZipUtil {
    public static int BUFFER_LENGTH = 1024;
    private ZipOutputStream zos;
    private byte[] data;

    public MyZipUtil(OutputStream out) {
        this.zos = new ZipOutputStream(new CheckedOutputStream(out,new CRC32()));
        data = new byte[BUFFER_LENGTH];
    }

    /**
     * 添加一个文件到zip中
     * @param fileName 添加到zip后的文件名称
     * @param file 该文件
     * @throws IOException
     */
    public void putFile(String fileName, File file) throws IOException {
        BufferedInputStream bis = null;
        try {
            ZipEntry entry = new ZipEntry(fileName);
            zos.putNextEntry(entry);
            bis = new BufferedInputStream(new FileInputStream(file));
            int count = 0;
            while ((count = bis.read(data, 0, BUFFER_LENGTH)) != -1) {
                zos.write(data, 0, count);
            }
        }finally {
            if(null != bis) {
                bis.close();
            }
            zos.closeEntry();
        }
    }

    public void close(){
        if(null != zos){
            try {
                zos.flush();
                zos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
