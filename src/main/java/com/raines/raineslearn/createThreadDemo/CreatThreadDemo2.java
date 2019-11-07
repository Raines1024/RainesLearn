package com.raines.raineslearn.createThreadDemo;

/**
 * 实现runnable接口，作为线程任务存在
 * Runnable 只是来修饰线程所执行的任务，它不是一个线程对象。想要启动Runnable对象，必须将它放到一个线程对象里。
 */
public class CreatThreadDemo2 implements Runnable {
    @Override
    public void run() {
        while (true){
            System.out.println("线程执行了...");
        }
    }

    public static void main(String[] args) {
        //将线程任务传给线程对象
        Thread thread = new Thread(new CreatThreadDemo2());
        //启动线程
        thread.start();
    }
}