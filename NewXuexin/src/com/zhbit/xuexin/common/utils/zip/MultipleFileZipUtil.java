package com.zhbit.xuexin.common.utils.zip;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by zouxiang on 2017/6/6.
 * 使用多线程方式生成文件并且压缩,此类是线程安全的
 */
public class MultipleFileZipUtil {
    private static final int DEFAULT_POOL_SIZE = 4;
    private volatile boolean isSubmitEnd;
    private MyZipUtil zipUtil;
    private final List<Future<CompressFile>> futures;
    private ExecutorService threadPool;
    private Future<?> processTheadFuture;
    private int poolSize;

    public MultipleFileZipUtil(OutputStream zipFileOut) {
        this(zipFileOut, DEFAULT_POOL_SIZE);
    }

    public MultipleFileZipUtil(OutputStream zipFileOut, int poolSize) {
        if (poolSize < 1) {
            throw new IllegalArgumentException("线程池大小不能小于1");
        }
        this.poolSize = poolSize;
        this.isSubmitEnd = false;
        this.zipUtil = new MyZipUtil(zipFileOut);
        this.futures = new LinkedList<>();
        //在原来的基础上添加一个处理线程
        this.threadPool = Executors.newFixedThreadPool(poolSize + 1);
        startProcessThead();
    }

    /**
     * 提交新任务
     * @param task
     */
    public void submitTask(Callable<CompressFile> task) {
		synchronized (futures) {
	        if (isSubmitEnd) {
	        	throw new RuntimeException("关闭提交功能后不能再提交任务!");
	        }
	        Future<CompressFile> future = threadPool.submit(task);
	        futures.add(future);
	        futures.notify();
        }
    }

    /**
     * 停止提交任务,并且等待所有任务执行完成,此方法需要保证调用线程与调用submitTask方法相同
     */
    public void endSubmitAndShutdown() {
        endSubmit();
        shutdown();
    }

    /**
     * 停止提交任务,此方法需要保证调用线程与submitTask相同
     */
    public void endSubmit() {
        synchronized (futures) {
        	this.isSubmitEnd = true;
            futures.notify();
        }
    }

    /**
     * 阻塞等待所有任务执行完成
     */
    public void shutdown() {
        if (processTheadFuture != null) {
            try {
                processTheadFuture.get();
            } catch (InterruptedException e) {
                e.printStackTrace();
                throw new RuntimeException("task 被取消执行", e);
            } catch (ExecutionException e) {
                e.printStackTrace();
                throw new RuntimeException("task 执行异常", e);
            }
        }
        threadPool.shutdown();
        zipUtil.close();
    }

    private void startProcessThead() {
        this.processTheadFuture = threadPool.submit(new Callable<Void>() {

            @Override
            public Void call() throws Exception {
                while (true) {
                    List<Future<CompressFile>> doneFuture = new ArrayList<>(poolSize);
                    synchronized (futures) {
                        while (futures.isEmpty()) {
                            if (isSubmitEnd) {
                                return null;
                            }
                            futures.wait();
                        }
                        Iterator<Future<CompressFile>> iterator = futures.iterator();
                        int i = 1;
                        while (iterator.hasNext()) {
                            Future<CompressFile> next = iterator.next();
                            if (next.isDone()) {
                                iterator.remove();
                                doneFuture.add(next);
                            }
                            //只找前poolSize个Future
                            if (++i > poolSize) {
                                break;
                            }
                        }
                        if (doneFuture.isEmpty()) {
                            doneFuture.add(futures.get(0));
                            futures.remove(0);
                        }
                    }
                    for (Future<CompressFile> fileFuture : doneFuture) {
                        CompressFile compressFile = fileFuture.get();
                        if (compressFile == null) {
                            throw new RuntimeException("处理结果文件为空!");
                        }
                        //Thread.sleep(10);
                        zipUtil.putFile(compressFile.fileName, compressFile.file);
                    }

                }
            }
        });
    }

    
    public static class CompressFile {
        /**
         * 在压缩文件中的绝对路径
         */
        String fileName;
        /**
         * 压缩文件
         */
        File file;

        public CompressFile(String fileName, File file) {
            this.fileName = fileName;
            this.file = file;
        }
    }
}

