package com.raines.raineslearn.executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 异步处理类
 */
public class MyExecutor {
    private ExecutorService executor = Executors.newCachedThreadPool();

    public void fun() throws Exception {
        executor.submit(() -> {
            try {
                //要执行的业务代码，我们这里没有写方法，可以让线程休息几秒进行测试
                Thread.sleep(3000);
                System.out.println("异步处理执行成功");
            } catch (Exception e) {
                throw new RuntimeException("报错啦！！");
            }
        });
    }
}