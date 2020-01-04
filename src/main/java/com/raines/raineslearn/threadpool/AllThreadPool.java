package com.raines.raineslearn.threadpool;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 所有的线程池类型都从此类获取
 */
@Component
public class AllThreadPool {

    @Bean(name = "rejectThreadPool")
    public ThreadPoolExecutor getRejectExecutor(){
        /**cpu核心线程数*/
        //8 16
        //左移
        //1000   10000
        int coreNum=Runtime.getRuntime().availableProcessors();
        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                coreNum<<1,
                20,
                120L,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(50000),
//                new NamedThreadFactory("Etian-Pool"),
                new RejectedTaskPolicyWithReport("Etian-Pool"));
        return executor;
    }
}
