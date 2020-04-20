package com.raines.raineslearn.async.chapter3.CompletableFuture;

import java.util.concurrent.*;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * 基于thenApply实现异步任务A，执行完毕后，激活异步任务B执行。需要注意的是，这种方式激活的异步任务B是可以拿到任务A的执行结果的，并且可以获取到异步任务B的执行结果。
 *
 */
public class TestCompletableFutureCallBack {

    // 0.创建线程池
    private static final ThreadPoolExecutor bizPoolExecutor = new ThreadPoolExecutor(8, 8, 1, TimeUnit.MINUTES,
            new LinkedBlockingQueue<>(10));

    public static void main(String[] args) throws InterruptedException, ExecutionException {

        // 1.创建异步任务，并返回future
        CompletableFuture<String> oneFuture = CompletableFuture.supplyAsync(new Supplier<String>() {
            @Override
            public String get() {
                // 1.1休眠2s，模拟任务计算
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                // 1.2返回计算结果
                return "hello";
            }
        });
        /**
         * 2.在future上施加事件，当future计算完成后回调该事件，并返回新future
         * 需要注意的是，这里可以在回调方法apply(String t)的参数t中获取oneFuture对应的任务结果，
         * 另外需要注意的是，由于apply(String t)方法有返回值，所以在twoFuture上调用get()方法最终也会返回回调方法返回的值。
         */
        CompletableFuture<String> twoFuture = oneFuture.thenApply(new Function<String, String>() {

            // 2.1对步骤1计算结果基础上进行计算，这里t为步骤1返回的hello
            @Override
            public String apply(String t) {
                // 2.1.1对oneFuture返回的结果进行加工
                try {
                    Thread.sleep(1000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                // 2.1.2返回加工后结果
                return t + " jiduo";
            }
        });
        /**
         * 默认情况下oneFuture对应的异步任务和在oneFuture上添加的回调事件都是使用ForkJoinPool.commonPool()中的同一个线程来执行的，
         * 大家可以使用thenApplyAsync(Function<?super T，?extends U>fn，Executor executor)来指定设置的回调事件使用自定义线程池线程来执行，
         * 也就是oneFuture对应的任务与在其上设置的回调执行将不会在同一个线程中执行。
         */
        CompletableFuture<String> threeFuture = twoFuture.thenApplyAsync(t2->{
            try {
                Thread.sleep(1000);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return t2+"three";
        },bizPoolExecutor);

        System.out.println(oneFuture.get());
        // 3.同步等待twoFuture对应的任务完成，并获取结果
        System.out.println(twoFuture.get());
        System.out.println(threeFuture.get());

    }

}
