package com.raines.raineslearn.computerStructure.recursion.processAbstract;

/**
 * 定义过程计算数值
 */
public class SumCount {

    /**
     * 计算从a到b各整数之和
     */
    public int sumIntegers(int a, int b) {
        if (a > b) return 0;
        return a + sumIntegers(a + 1, b);
    }

    /**
     * 计算给定范围内(a-b)整数的立方之和
     */
    public int sumCubes(int a, int b) {
        if (a > b) return 0;
        return cube(a) + sumCubes(a + 1, b);
    }

    public double piSum(int a, int b) {
        if (a > b) return 0;
        return 1.0 / (a * (a + 2)) + piSum(a + 4, b);
    }

    /**
     * 计算x的立方
     */
    private int cube(int x) {
        return x * x * x;
    }

}




































