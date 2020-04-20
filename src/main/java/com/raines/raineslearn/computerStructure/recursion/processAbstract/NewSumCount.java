package com.raines.raineslearn.computerStructure.recursion.processAbstract;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 用高阶函数做抽象计算数值
 * 过程作为参数
 */
public class NewSumCount {

    private static int sum(Method term, Integer a, Method next, Integer b) throws InvocationTargetException, IllegalAccessException {
        if (a > b) return 0;
        return (Integer) term.invoke(Integer.class, a) + sum(term, (Integer) next.invoke(Integer.class, a), next, b);
    }

    /**
     * 计算从a到b各整数之和
     */
    public static int sumIntegers(int a, int b) throws Exception {
        return sum(NewSumCount.class.getDeclaredMethod("identity", Integer.class), a, NewSumCount.class.getDeclaredMethod("inc", Integer.class), b);
    }

    /**
     * 计算从a到b各整数的立方和
     */
    public static int sumCubes(int a, int b) throws Exception {
        return sum(NewSumCount.class.getDeclaredMethod("cube", Integer.class), a, NewSumCount.class.getDeclaredMethod("inc", Integer.class), b);
    }

    public static void main(String[] args) throws Exception {
        System.out.println(sumIntegers(1, 10));
        System.out.println(sumCubes(1, 10));
    }

    /**
     * 计算x的立方
     */
    private static int cube(Integer x) {
        return x * x * x;
    }

    private static int identity(Integer x) {
        return x;
    }

    private static int inc(Integer a) {
        return a + 1;
    }

}








































































