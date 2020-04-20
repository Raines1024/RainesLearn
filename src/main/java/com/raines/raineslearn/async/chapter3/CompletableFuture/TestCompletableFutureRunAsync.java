package com.raines.raineslearn.async.chapter3.CompletableFuture;

import java.util.concurrent.*;
import java.util.function.Supplier;

/**
 * 使用CompletableFuture实现异步计算与结果转换
 * 不指定线程池的默认情况下，CompletableFuture的supplyAsync(Supplier<U>supplier)和runAsync(Runnable runnable)方法，
 * 是使用整个JVM内唯一的ForkJoinPool.commonPool()线程池来执行异步任务
 *
 */
public class TestCompletableFutureRunAsync {

    // 0.创建线程池
    private static final ThreadPoolExecutor bizPoolExecutor = new ThreadPoolExecutor(8, 8, 1, TimeUnit.MINUTES,
            new LinkedBlockingQueue<>(10));

    // 1. 没有返回值的异步执行：使用默认线程池执行
    public static void runAsync() throws InterruptedException, ExecutionException {
        // 1.1创建异步任务，并返回future
        CompletableFuture future = CompletableFuture.runAsync(new Runnable() {
            @Override
            public void run() {
                // 1.1.1休眠2s模拟任务计算
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("over");
            }
        });

        // 1.2 同步等待异步任务执行结束
        System.out.println(future.get());
    }

    // 2. 有返回值的异步执行
    public static void supplyAsync() throws InterruptedException, ExecutionException {
        // 2.1创建异步任务，并返回future
        CompletableFuture future = CompletableFuture.supplyAsync(new Supplier<String>() {
            @Override
            public String get() {
                // 2.1.1休眠2s模拟任务计算
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                // 2.1.2 返回异步计算结果
                return "hello";
            }
        });

        // 2.2 同步等待异步任务执行结束
        System.out.println(future.get());
    }

    // 3. 没有返回值的异步执行，异步任务有业务自己线程池执行
    public static void runAsyncWithBizExecutor() throws InterruptedException, ExecutionException {
        // 1.1创建异步任务，并返回future
        CompletableFuture future = CompletableFuture.runAsync(new Runnable() {
            @Override
            public void run() {
                // 1.1.1休眠2s模拟任务计算
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("over");
            }
        }, bizPoolExecutor);

        // 1.2 同步等待异步任务执行结束
        System.out.println(future.get());
    }

    // 4. 有返回值的异步执行
    public static void supplyAsyncWithBizExecutor() throws InterruptedException, ExecutionException {
        // 2.1创建异步任务，并返回future
        CompletableFuture future = CompletableFuture.supplyAsync(new Supplier<String>() {
            @Override
            public String get() {
                // 2.1.1休眠2s模拟任务计算
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                // 2.1.2 返回异步计算结果
                return "hello,jiaduo";
            }
        }, bizPoolExecutor);

        // 2.2 同步等待异步任务执行结束
        System.out.println(future.get());
    }

    public static void main(String[] args) throws InterruptedException, ExecutionException {

        // 1 runAsync
        runAsync();

        // 2. supplyAsync
        supplyAsync();

        // 3.runAsyncWithBizExecutor
        runAsyncWithBizExecutor();

        // 4. supplyAsyncWithBizExecutor
        supplyAsyncWithBizExecutor();
    }

}
