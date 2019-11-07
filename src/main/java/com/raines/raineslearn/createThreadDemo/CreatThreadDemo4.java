package com.raines.raineslearn.createThreadDemo;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/**
 * 创建带返回值的线程
 */
public class CreatThreadDemo4 implements Callable {
    public static void main(String[] args) throws Exception {
        CreatThreadDemo4 demo4 = new CreatThreadDemo4();

        FutureTask<Integer> task = new FutureTask<Integer>(demo4); //FutureTask最终实现的是runnable接口

        Thread thread = new Thread(task);

        thread.start();

        System.out.println("我可以在这里做点别的业务逻辑...因为FutureTask是提前完成任务");
        //拿出线程执行的返回值
        Integer result = task.get();
        System.out.println("线程中运算的结果为:"+result);
    }

    //重写Callable接口的call方法
    @Override
    public Object call() throws Exception {
        int result = 1;
        System.out.println("业务逻辑计算中...");
        Thread.sleep(6000);
        return result;
    }
}
