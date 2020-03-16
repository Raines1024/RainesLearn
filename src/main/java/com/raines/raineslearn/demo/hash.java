package com.raines.raineslearn.demo;

import java.util.HashMap;
import java.util.Map;

public class hash {

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        String time;
        Map<String, String> map = new HashMap<>();
        for (int i = 0; i < 1000000; i++) {
            time = String.valueOf(System.currentTimeMillis());
            map.put(i + "_" + time, time);
        }
        System.out.println(System.currentTimeMillis() - start);
    }

}