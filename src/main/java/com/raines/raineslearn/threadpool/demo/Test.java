package com.raines.raineslearn.threadpool.demo;

import com.raines.raineslearn.demo.SaveEveryDayMileage;
import com.raines.raineslearn.restAuthorized.Authorized;
import com.raines.raineslearn.threadpool.RejectedRunnable;
import com.sun.corba.se.impl.orbutil.concurrent.Sync;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@Slf4j
@RestController
public class Test {

    @Resource
    private ThreadPoolExecutor rejectThreadPool;

    //    @Authorized
    @RequestMapping("/threadDemo")
    public void demo() throws Exception {
        CountDownLatch latch = new CountDownLatch(40);
        for (int i = 0; i < 40; i++) {
            rejectThreadPool.execute(new ReceiveStrTask(i + "", latch));
        }
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("=====sdfsdfsd");
    }

    class ReceiveStrTask implements RejectedRunnable {

        private String string;

        CountDownLatch latch;

        public ReceiveStrTask(String string, CountDownLatch latch) {
            this.string = string;
            this.latch = latch;
        }

        @Override
        public void rejected() {
            log.error("任务队列已满，更新任务无法执行，字符串：{}", string);
        }

        @Override
        public void run() {
            System.out.println("======-执行");
            demo();
            latch.countDown();
        }

        //synchronized(this)以及非static的synchronized方法，只能防止多个线程同时执行同一个对象的同步代码段。即synchronized锁住的是括号里的对象，而不是代码。
        // 对于非static的synchronized方法，锁的就是对象本身也就是this。
        public void demo() {
            //synchronized (ReceiveStrTask.class)实现了全局锁的效果。因此，如果要想锁的是代码段，锁住多个对象的同一方法，使用这种全局锁，锁的是类而不是this。
            synchronized (ReceiveStrTask.class) {
                System.out.println(Thread.currentThread().getName() + "进来了");
//            Lock lock = new ReentrantLock();
//            lock.lock();
                try {
                    Thread.sleep(1000l);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("okok-------------" + string);
//            lock.unlock();
            }
        }

    }

}
























































