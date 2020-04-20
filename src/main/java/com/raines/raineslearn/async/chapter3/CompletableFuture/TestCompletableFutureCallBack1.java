package com.raines.raineslearn.async.chapter3.CompletableFuture;

import java.util.concurrent.*;
import java.util.function.Consumer;
import java.util.function.Supplier;

//有返回结果的回调
public class TestCompletableFutureCallBack1 {
    // 0.创建线程池
    private static final ThreadPoolExecutor bizPoolExecutor = new ThreadPoolExecutor(8, 8, 1, TimeUnit.MINUTES,
            new LinkedBlockingQueue<>(10));

    /**
     * I thenRun不能访问oneFuture的结果
     * 在oneFuture上调用thenRun方法添加异步执行事件，当oneFuture计算完成后回调该事件，并返回twoFuture，
     * 另外，在twoFuture上调用get()方法也会返回null，因为回调事件是没有返回值的。
     */
    public static void thenRun() throws InterruptedException, ExecutionException {
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
        // 2.在future上施加事件，当future计算完成后回调该事件，并返回新future
        CompletableFuture twoFuture = oneFuture.thenRun(new Runnable() {

            @Override
            public void run() {
                // 2.1.1当oneFuture任务计算完成后做一件事情
                try {
                    Thread.sleep(1000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName());
                System.out.println("---after oneFuture over doSomething---");
            }
        });

        // 3.同步等待twoFuture对应的任务完成，返回结果固定为null
        System.out.println(twoFuture.get());

    }

    /**
     * II thenRun不能访问oneFuture的结果：使用thenAccept访问oneFuture的结果
     * 基于thenAccept实现异步任务A，执行完毕后，激活异步任务B执行，需要注意的是，这种方式激活的异步任务B是可以拿到任务A的执行结果的。
	 * 需要注意的是，这里可以在回调的方法accept(String t)的参数t中来获取oneFuture对应的任务结果，
	 * 另外需要注意的是，由于accept(String t)方法没有返回值，所以在twoFuture上调用get()方法最终也会返回null。
	 *
     */
    public static void thenAccept() throws InterruptedException, ExecutionException {
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
        // 2.在future上施加事件，当future计算完成后回调该事件，并返回新future
        CompletableFuture twoFuture = oneFuture.thenAccept(new Consumer<String>() {

            @Override
            public void accept(String t) {
                // 2.1.1对oneFuture返回的结果进行加工
                try {
                    Thread.sleep(1000);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                System.out.println("---after oneFuture over doSomething---" + t);
            }
        });

        // 3.在future上施加事件，当future计算完成后回调该事件，并返回新future
        CompletableFuture threeFuture = oneFuture.thenAccept(new Consumer<String>() {

            @Override
            public void accept(String t) {
                // 2.1.1对oneFuture返回的结果进行加工
                try {
                    Thread.sleep(1000);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                System.out.println("---after oneFuture over doSomething---" + t);
            }
        });

        // 3.同步等待twoFuture对应的任务完成，返回结果固定为null
        System.out.println(twoFuture.get());

    }

    // III thenRun不能访问oneFuture的结果
    public static void thenRunAsync() throws InterruptedException, ExecutionException {
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
        // 2.在future上施加事件，当future计算完成后回调该事件，并返回新future
        CompletableFuture twoFuture = oneFuture.thenRunAsync(new Runnable() {

            @Override
            public void run() {
                // 2.1.1当oneFuture任务计算完成后做一件事情
                try {
                    Thread.sleep(1000);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                System.out.println("---after oneFuture over doSomething---");
            }
        });

        // 3.同步等待twoFuture对应的任务完成，返回结果固定为null
        System.out.println(twoFuture.get());

    }

    // IV thenRun不能访问oneFuture的结果
    public static void thenAcceptAsync() throws InterruptedException, ExecutionException {
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
        // 2.在future上施加事件，当future计算完成后回调该事件，并返回新future
        CompletableFuture twoFuture = oneFuture.thenAcceptAsync(new Consumer<String>() {

            @Override
            public void accept(String t) {
                // 2.1.1对oneFuture返回的结果进行加工
                try {
                    Thread.sleep(1000);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                System.out.println("---after oneFuture over doSomething---" + t);
            }
        });

        // 3.同步等待twoFuture对应的任务完成，返回结果固定为null
        System.out.println(twoFuture.get());

    }

    public static void main(String[] args) throws InterruptedException, ExecutionException {

        // 1.thenRun test
        // thenRun();

        // 2.thenAccept test
        thenAccept();

        // 3.thenRunAsync test
        // thenRunAsync();

        // 4.thenAcceptAsync test;
        // thenAcceptAsync();
    }

}
