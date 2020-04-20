package com.raines.raineslearn.threadDemo.threadOper;

import java.util.concurrent.locks.LockSupport;

/**
 * LockSupport 提供的 park 和 unPark 方法，提供避免死锁和竞态条件，很好地代替 suspend 和 resume 组合。
 * <p>
 * park 与 unPark 方法控制的颗粒度更加细小，能准确决定线程在某个点停止，进而避免死锁的产生。
 * park 与 unPark 引入了许可机制，许可逻辑为：
 * ①park 将许可在等于0的时候阻塞，等于1的时候返回并将许可减为0；
 * ②unPark 尝试唤醒线程，许可加1。根据这两个逻辑，对于同一条线程，park 与 unPark 先后操作的顺序似乎并不影响程序正确地执行，假如先执行 unPark 操作，许可则为1，之后再执行park操作，此时因为许可等于1直接返回往下执行，并不执行阻塞操作。
 * <p>
 * park 与 unPark 组合真正解耦了线程之间的同步，不再需要另外的对象变量存储状态，并且也不需要考虑同步锁，wait与notify要保证必须有锁才能执行，而且执行notify操作释放锁后还要将当前线程扔进该对象锁的等待队列，LockSupport则完全不用考虑对象、锁、等待队列等问题。
 */
public class ThreadParkTest {
    public static void main(String[] args) throws Exception {
        Thread t = new Thread(() -> {
            System.out.println("start");
            LockSupport.park(); //一直wait
            System.out.println("continue");
        });
        t.start();

        Thread.sleep(1000);
        LockSupport.unpark(t); //指定t线程解除wait态
    }
}