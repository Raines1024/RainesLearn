package com.raines.raineslearn.threadDemo;

/**
 * 如何正确的终止一个线程
 */
public class StopThread {
    public static void main(String[] args) throws InterruptedException {
        MyThread myThread = new MyThread();
        myThread.start();
        Thread.sleep(3000);
        myThread.flag = false;
        System.out.println("主线程结束！");
    }
}
class MyThread extends Thread {
    public volatile boolean flag = true;

    public void run() {
        while (flag) {
            System.out.println("线程正在执行");
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("线程已终止");
    }
}


