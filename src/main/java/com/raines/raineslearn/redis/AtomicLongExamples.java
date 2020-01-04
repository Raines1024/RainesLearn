package com.raines.raineslearn.redis;


import org.redisson.Redisson;
import org.redisson.api.RAtomicLong;
import org.redisson.api.RedissonClient;

public class AtomicLongExamples {
    public static void main(String[] args) {
        // connects to 127.0.0.1:6379 by default
        RedissonClient redisson = Redisson.create();
        RAtomicLong atomicLong = redisson.getAtomicLong("myLong");
        System.out.println("Init value: " + atomicLong.get());
        atomicLong.incrementAndGet();
        System.out.println("Current value: " + atomicLong.get());
        atomicLong.addAndGet(10L);
        System.out.println("Final value: " + atomicLong.get());
        redisson.shutdown();
    }
}