package com.raines.raineslearn.redis;

import org.redisson.Redisson;
import org.redisson.api.RMap;
import org.redisson.api.RedissonClient;

import java.io.IOException;

public class MapExamples {
    public static void main(String[] args) throws IOException {
        // connects to 127.0.0.1:6379 by default
        RedissonClient redisson = Redisson.create();
        // implements java.util.concurrent.ConcurrentMap
        RMap<String, Integer> map =  redisson.getMap("myMap");
        map.put("a", 1);
        map.put("b", 2);
        map.put("c", 3);
        boolean contains = map.containsKey("a");
        System.out.println("Map size: " + map.size());
        System.out.println("Is map contains key 'a': " + contains);
        Integer value = map.get("c");
        System.out.println("Value mapped by key 'c': " + value);
        boolean added = map.putIfAbsent("c", 4) == null;
        System.out.println("Is value mapped by key 'c' added: " + added);
        redisson.shutdown();
    }
}