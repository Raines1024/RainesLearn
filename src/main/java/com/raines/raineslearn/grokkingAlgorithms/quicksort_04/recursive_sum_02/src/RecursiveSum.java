package com.raines.raineslearn.grokkingAlgorithms.quicksort_04.recursive_sum_02.src;

import java.util.Arrays;

public class RecursiveSum {

    private static int sum(int[] arr) {
        if (arr.length == 0) {
            return 0;
        } else {
            int res = arr[0] + sum(Arrays.copyOfRange(arr, 1, arr.length));
            System.out.println(res);
            return res;
        }
    }

    public static void main(String[] args) {
        System.out.println(sum(new int[]{1, 2, 3, 4})); // 10
    }
}