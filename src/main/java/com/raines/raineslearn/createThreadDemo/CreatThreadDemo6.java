package com.raines.raineslearn.createThreadDemo;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 线程池创建线程
 *
 * Executor 管理多个异步任务的执行，而无需程序员显式地管理线程的生命周期。这里的异步是指多个任务的执行互不干扰，不需要进行同步操作。
 * 主要有三种 Executor：
 * CachedThreadPool：一个任务创建一个线程；
 * FixedThreadPool：所有任务只能使用固定大小的线程；
 * SingleThreadExecutor：相当于大小为 1 的 FixedThreadPool。
 */
public class CreatThreadDemo6 {
    public static void main(String[] args) {
        //创建一个具有10个线程的线程池
        ExecutorService threadPool = Executors.newFixedThreadPool(10);
        long threadpoolUseTime = System.currentTimeMillis();
        for (int i = 0;i<10;i++){
            threadPool.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName()+"线程执行了...");
                }
            });
        }
        long threadpoolUseTime1 = System.currentTimeMillis();
        System.out.println("多线程用时"+(threadpoolUseTime1-threadpoolUseTime));
        //销毁线程池
        threadPool.shutdown();
        threadpoolUseTime = System.currentTimeMillis();
    }
}
















