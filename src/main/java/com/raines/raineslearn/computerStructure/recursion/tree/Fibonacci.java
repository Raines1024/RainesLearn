package com.raines.raineslearn.computerStructure.recursion.tree;

/**
 * 计算模式：树形递归
 * 斐波那契数列（每个数都是前面两个数之和）
 * 0，1，1，2，3，5，8，13，···
 */
public class Fibonacci {

    public static int fib(int n){
        if (n == 0){
            return 0;
        }else if (n == 1){
            return 1;
        }else {
            return fib(n-1)+fib(n-2);
        }
    }

    public static void main(String[] args) {
        //    fib(5)
        //fib(4)+flib(3)
        //fib(3)+fib(2)+fib(2)+fib(1)
        //fib(2)+1+ 1+0+ 1+0+  1
        //1+0+1+1+0+1+0+1
        //5
        System.out.println(fib(5));
    }

}

































































