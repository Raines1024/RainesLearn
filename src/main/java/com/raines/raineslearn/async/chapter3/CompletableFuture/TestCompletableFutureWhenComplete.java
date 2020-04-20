package com.raines.raineslearn.async.chapter3.CompletableFuture;

import java.util.concurrent.*;
import java.util.function.BiConsumer;
import java.util.function.Supplier;

/**
 * 基于whenComplete设置回调函数，当异步任务执行完毕后进行回调，不会阻塞调用线程
 */
public class TestCompletableFutureWhenComplete {

    public static void main(String[] args) throws InterruptedException, ExecutionException, TimeoutException {

        // 1.创建一个CompletableFuture对象
        CompletableFuture<String> future = CompletableFuture.supplyAsync(new Supplier<String>() {

            @Override
            public String get() {
                // 1.1模拟异步任务执行
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                // 1.2返回计算结果
                return "hello,jiaduo";
            }
        });

        /**
         * 2.添加回调函数
         * 在返回的future上调用whenComplete设置一个回调函数，然后main线程就返回了。在整个异步任务的执行过程中，main函数所在线程是不会被阻塞的，等异步任务执行完毕后会回调设置的回调函数，在回调函数内，代码2.1表示如果发现异步任务执行正常则打印执行结果，否则打印异常信息。这里代码3挂起了main函数所在线程，是因为具体执行异步任务的是ForkJoin的commonPool线程池，其中线程都是Deamon线程，所以，当唯一的用户线程main线程退出后整个JVM进程就退出了，会导致异步任务得不到执行
         */
        future.whenComplete(new BiConsumer<String, Throwable>() {

            @Override
            public void accept(String t, Throwable u) {
                // 2.1如果没有异常，打印异步任务结果
                if (null == u) {
                    System.out.println("1");
                } else {
                    // 2.2打印异常信息
                    System.out.println(u.getLocalizedMessage());
                }
            }
        });
//
//        // 2.添加回调函数
//        future.whenComplete(new BiConsumer<String, Throwable>() {
//
//            @Override
//            public void accept(String t, Throwable u) {
//                // 2.1如果没有异常，打印异步任务结果
//                if (null == u) {
//                    System.out.println("2--");
//                } else {
//                    // 2.2打印异常信息
//                    System.out.println(u.getLocalizedMessage());
//
//                }
//            }
//        });

        System.out.println(Thread.currentThread().getName());
        // 3.挂起当前线程，等待异步任务执行完毕
        Thread.currentThread().join();
    }
}
