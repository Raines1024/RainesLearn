package com.raines.raineslearn.concurrentTest;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * Java多线程压测工具类
 */
public class ConcurrentTestUtil {

    /**
     * 多线程并发执行某项任务
     * @param concurrentThreads 并发线程数，可以用来模拟并发访问用户数
     * @param times 总共执行多少次
     * @param task  任务
     * @param resultHandler 结果处理器
     * @param executeTimeoutMillis 执行任务总超时
     * @throws InterruptedException
     * @throws ExecutionException
     */
    public static <T> void concurrentTest(long concurrentThreads, int times, final Callable<T> task,
                                          ResultHandler<T> resultHandler, long executeTimeoutMillis)
            throws InterruptedException, ExecutionException {
        ExecutorService executor = Executors.newFixedThreadPool((int) concurrentThreads);
        List<Future<T>> results = new ArrayList<Future<T>>(times);
        long startTimeMillis = System.currentTimeMillis();
        for(int i = 0; i < times; i++) {
            results.add(executor.submit(task));
        }
        executor.shutdown();
//      while(!executor.awaitTermination(1, TimeUnit.SECONDS));   // 每隔1s钟检查线程池是否已关闭
        boolean executeCompleteWithinTimeout = executor.awaitTermination(executeTimeoutMillis,
                TimeUnit.MILLISECONDS);
        if(!executeCompleteWithinTimeout) {
            System.out.println("Execute tasks out of timeout [" + executeTimeoutMillis + "ms]");

            /*
             * 取消所有任务
             */
            for (Future<T> r : results) {
                r.cancel(true);
            }
        }
        else {
            long totalCostTimeMillis = System.currentTimeMillis() - startTimeMillis;
            // 线程池此时肯定已关闭，处理任务结果
            for(Future<T> r: results) {
                /*
                 * r.get()本义是等待任务执行结果，但这里不需要等待，因为上面已经把线程池关闭了
                 */
                if(resultHandler != null) {
                    resultHandler.handle(r.get());
                }
            }

            System.out.println("concurrent threads: " + concurrentThreads + ", times: "
                    + times);
            System.out.println("total cost time(ms): " + totalCostTimeMillis
                    + "ms, avg time(ms): " + ((double) totalCostTimeMillis / times));
            System.out.println("tps: " + (double) (times * 1000) / totalCostTimeMillis);
        }
    }

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ConcurrentTestUtil.concurrentTest(100, 3000,
                new Callable<String>() {
                    @Override
                    public String call() throws Exception {
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                        return "ok";
                    }
                },
                new ResultHandler<String>() {
                    @Override
                    public void handle(String result) {
                        System.out.println("result: " + result);
                    }
                }, 6000);
    }

}
