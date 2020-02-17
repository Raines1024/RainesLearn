package com.raines.raineslearn.grokkingAlgorithms.quicksort_04.loop_sum_01.src;

public class LoopSum {

    private static int sum(int[] arr) {
        int total = 0;
        for (int x = 0; x < arr.length; x++) {
            total += arr[x];
        }

        return total;
    }

    public static void main(String[] args) {
        System.out.println(sum(new int[]{1, 2, 3, 4})); // 10
    }
}