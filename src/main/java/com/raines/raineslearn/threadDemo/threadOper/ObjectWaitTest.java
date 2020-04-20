package com.raines.raineslearn.threadDemo.threadOper;


/**
 * wait、notify形式通过一个object作为信号，object的wait()方法是锁门的动作，notify()、notifyAll()是开门的动作，某一线程一旦关上门后其他线程都将阻塞，直到别的线程打开门。notify()准许阻塞的一个线程通过，notifyAll()允许所有线程通过。
 * 如下例子：主线程分别启动两个线程，随后通知子线程暂停等待，再逐个唤醒后线程抛异常退出。
 */
public class ObjectWaitTest {
    public static Object waitObject = new Object();

    public static void notifyAllThread() {
        System.out.println("notifyAllThread");
        synchronized (waitObject) {
            waitObject.notifyAll();
        }
    }

    public static void notifyThread() {
        System.out.println("notifyThread");
        synchronized (waitObject) {
            waitObject.notify();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        MyThread tm1 = new MyThread(waitObject);
        tm1.setName("tm1");
        tm1.start();
        MyThread tm2 = new MyThread(waitObject);
        tm2.setName("tm2");
        tm2.start();
        Thread.currentThread().sleep(1000);
        tm1.suspendThread();
        tm2.suspendThread();
        Thread.currentThread().sleep(1000);
        notifyThread();
        Thread.currentThread().sleep(1000);
        notifyThread();
    }

}

class MyThread extends Thread {
    public Object waitObject = null;
    private boolean isStop = false;

    public MyThread(Object waitObject) {
        this.waitObject = waitObject;
    }

    public void run() {
        while (true) {
            synchronized (waitObject) {
                if (isStop) {
                    System.out.println(Thread.currentThread().getName() + " is stop");
                    try {
                        waitObject.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + " is resume");
                    System.out.println(Thread.currentThread().getName() + " will  exit");
                    throw new RuntimeException(Thread.currentThread().getName() + " exit");
                }
            }
        }
    }

    public void suspendThread() {
        this.isStop = true;
    }
}