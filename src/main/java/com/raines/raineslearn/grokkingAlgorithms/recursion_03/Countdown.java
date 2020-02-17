package com.raines.raineslearn.grokkingAlgorithms.recursion_03;

/**
 * 倒计时递归演示
 */
public class Countdown {

    private static void countdown(int i) {
        System.out.println(i);

        // base case(基线条件)
        if (i <= 0) {
            return;
        } else {//递归条件
            countdown(i - 1);
        }
    }

    public static void main(String[] args) {
        countdown(5);
    }
}