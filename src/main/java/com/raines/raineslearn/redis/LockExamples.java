package com.raines.raineslearn.redis;

import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

public class LockExamples {

    //lock aquired
    //lock released
    //lock aquired by thread
    //lock released by thread
    public static void main(String[] args) throws InterruptedException {
        // connects to 127.0.0.1:6379 by default
        Config config = new Config();
        config.useSingleServer().setAddress("redis://172.16.100.4:6379").setDatabase(5);
        RedissonClient redisson = Redisson.create(config);
        // implements java.util.concurrent.locks.Lock
        RLock lock = redisson.getLock("lock");
        lock.lock();
        System.out.println("lock aquired");
        Thread t = new Thread() {
            public void run() {
                RLock lock1 = redisson.getLock("lock");
                lock1.lock();
                System.out.println("lock aquired by thread");
                lock1.unlock();
                System.out.println("lock released by thread");
            };
        };
        t.start();
        t.join(1000);
        lock.unlock();
        System.out.println("lock released");
        t.join();
        redisson.shutdown();
    }
}