package com.raines.raineslearn.createThreadDemo;

import java.util.Arrays;
import java.util.List;

/**
 * 利用java8新特性 stream 实现并发
 */
public class CreatThreadDemo7 {

    public static void main(String[] args) {
        List<Integer> values = Arrays.asList(10,20,30,40);
        //parallel 平行的，并行的
        int result = values.parallelStream().mapToInt(p -> p*2).sum();
        System.out.println(result);
        //怎么证明它是并发处理呢
        values.parallelStream().forEach(p-> System.out.println(p));
    }

}
