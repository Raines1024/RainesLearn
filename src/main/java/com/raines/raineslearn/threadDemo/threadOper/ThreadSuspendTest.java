package com.raines.raineslearn.threadDemo.threadOper;

/**
 * 早期JAVA采用suspend()、resume()对线程进行阻塞与唤醒，但这种方式产生死锁的风险很大，因为线程被挂起以后不会释放锁，可能与其他线程、主线程产生死锁
 * resume():恢复任务
 * suspend():暂停任务
 */
public class ThreadSuspendTest {
    public static void main(String[] args) {
        Thread a = new MyThread();
        a.start();
        try {
            Thread.currentThread().sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        a.suspend();
        System.out.println("suspend complete?");
        a.resume();
    }

    static class MyThread extends Thread {
        public void run() {
            while (true) {
//                System.out.println("I'm OK.");
            }
        }
    }
}