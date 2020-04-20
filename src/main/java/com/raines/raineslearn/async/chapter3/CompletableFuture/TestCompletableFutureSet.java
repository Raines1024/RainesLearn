package com.raines.raineslearn.async.chapter3.CompletableFuture;

import java.util.concurrent.*;
import java.util.function.BiConsumer;

/**
 * 使用CompletableFuture实现了通知等待模型，主线程调用future的get()方法等待future返回结果，
 * 一开始由于future结果没有设置，所以主线程被阻塞挂起，
 * 等异步任务休眠3s，然后调用future的complete方法模拟主线程等待的条件完成，这时候主线程就会从get()方法返回。
 */
public class TestCompletableFutureSet {
    // 0自定义线程池
    private final static int AVALIABLE_PROCESSORS = Runtime.getRuntime().availableProcessors();
    private final static ThreadPoolExecutor POOL_EXECUTOR = new ThreadPoolExecutor(AVALIABLE_PROCESSORS,
            AVALIABLE_PROCESSORS * 2, 1, TimeUnit.MINUTES, new LinkedBlockingQueue<>(5),
            new ThreadPoolExecutor.CallerRunsPolicy());

    public static void main(String[] args) throws InterruptedException, ExecutionException, TimeoutException {

        // 1.创建一个CompletableFuture对象
        CompletableFuture<String> future = new CompletableFuture<>();

        // 2.将代码提交到异步线程池中执行：开启线程计算任务结果，并设置
        POOL_EXECUTOR.execute(() -> {
            // 2.1休眠3s，模拟任务计算
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            // 2.2设置计算结果到future
            System.out.println("----" + Thread.currentThread().getName() + " set future result----");
            //调用future的complete方法设置future的结果，设置完结果后，所有由于调用fture的get()方法而被阻塞的线程会被激活，并返回设置的结果。
            future.complete("hello,Raines");
        });

        // 3.等待计算结果
        //调用future的get()方法企图获取future的结果，如果future的结果没有被设置，则调用线程会被阻塞。
        System.out.println("---main thread wait future result---");
        System.out.println(future.get());
        // System.out.println(future.get(1000,TimeUnit.MILLISECONDS));
        System.out.println("---main thread got future result---");

        future.whenComplete(new BiConsumer<String, Throwable>() {

            @Override
            public void accept(String t, Throwable u) {

                if (null == u) {
                    System.out.println(t);
                } else {
                    System.out.println(u.getLocalizedMessage());

                }
            }
        });
    }
}