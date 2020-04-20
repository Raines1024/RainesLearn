package com.raines.raineslearn.computerStructure.recursion.tree;

/**
 * 线性迭代计算斐波那契数列
 */
public class iterFibonacci {

    public static int fib(int n) {
        return fibIter(1, 0, n);
    }

    public static int fibIter(int a, int b, int count) {
        if (count == 0) {
            return b;
        } else {
            return fibIter(a + b, a, --count);
        }
    }

}

























