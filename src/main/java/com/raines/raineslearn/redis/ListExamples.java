package com.raines.raineslearn.redis;

import org.redisson.Redisson;
import org.redisson.api.RList;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

public class ListExamples {
    /**
     * List size: 3
     * Does list contain value '1': true
     * List element: 1
     * List element: 2
     * List element: 3
     */
    public static void main(String[] args) {
        // connects to 127.0.0.1:6379 by default
        Config config = new Config();
        config.useSingleServer().setAddress("redis://172.16.100.4:6379").setDatabase(5);
        RedissonClient redisson = Redisson.create(config);
        // implements java.util.List
        RList<String> list = redisson.getList("myList");
        list.add("1");
        list.add("2");
        list.add("3");
        boolean contains = list.contains("1");
        System.out.println("List size: " + list.size());
        System.out.println("Is list contains value '1': " + contains);
        for (String element : list) {
            System.out.println("List element: " + element);
        }
        redisson.shutdown();
    }
}