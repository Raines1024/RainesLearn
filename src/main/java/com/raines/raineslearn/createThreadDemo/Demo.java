package com.raines.raineslearn.createThreadDemo;

import java.util.ArrayList;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Demo {
    public static void main(String[] args) throws Exception{
//        ExecutorService executorService = Executors.newFixedThreadPool(10);
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(10, 10,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>());
        CountDownLatch countDownLatch = new CountDownLatch(1000); //数量时任务的总量
        ArrayList<Future> futures = new ArrayList<>();

        for (int i = 0; i < 1000; i++) {
            final int finalI = i;
            Future<?> future = threadPoolExecutor.submit((Runnable) ()->{

                try {
                    System.out.println("###########corePoolSize: " + threadPoolExecutor.getCorePoolSize());
                    System.out.println("###########taskCount: " + threadPoolExecutor.getTaskCount());
                    System.out.println("###########i: " + finalI);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    countDownLatch.countDown();  //这个不管是否异常都需要数量减,否则会被堵塞无法结束
                }
            });
            futures.add(future);
        }

        try {
            countDownLatch.await(); //保证线程池中的所有的线任务都完成后，主线程才会继续向下执行；

        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("======");




        System.out.println("game over");
    }

}
