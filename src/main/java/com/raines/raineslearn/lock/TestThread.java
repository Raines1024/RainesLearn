package com.raines.raineslearn.lock;

/**
 * synchronized锁多对象，使代码段不能在多线程环境下同时运行解决方案
 */
//锁同一个对象
class Sync {
    public synchronized void test() {
        System.out.println("test方法开始，当前线程为 "+Thread.currentThread().getName());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("test方法结束，当前线程为 "+Thread.currentThread().getName());
    }
}

class MyThread extends Thread{
    private Sync sync;
    public MyThread(Sync sync){
        this.sync = sync;
    }
    @Override
    public void run() {
        this.sync.test();
    }
}

public class TestThread {
    public static void main(String[] args) {
        Sync sync = new Sync();
        for(int i = 0;i < 3;i++){
            new Thread(new MyThread(sync)).start();
        }
    }
}

