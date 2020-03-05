package com.raines.raineslearn.lock;

/**
 * synchronized锁多对象，使代码段不能在多线程环境下同时运行解决方案
 */
//让synchronized锁这个类对应的Class对象—全局锁
class Sync2 {
    //synchronized(this)以及非static的synchronized方法，只能防止多个线程同时执行同一个对象的同步代码段。即synchronized锁住的是括号里的对象，而不是代码。
    // 对于非static的synchronized方法，锁的就是对象本身也就是this。
    public synchronized void test() {
        //synchronized (Sync2.class)实现了全局锁的效果。因此，如果要想锁的是代码段，锁住多个对象的同一方法，使用这种全局锁，锁的是类而不是this。
        synchronized (Sync2.class) {
            System.out.println("test方法开始，当前线程为 " + Thread.currentThread().getName());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("test方法结束，当前线程为 " + Thread.currentThread().getName());
        }
    }
}

class MyThread2 extends Thread {
    @Override
    public void run() {
        Sync2 sync = new Sync2();
        sync.test();
    }
}

public class TestThread2 {
    public static void main(String[] args) {
        for (int i = 0; i < 3; i++) {
            new Thread(new MyThread2()).start();
        }
    }
}
