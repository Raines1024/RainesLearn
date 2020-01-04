package com.raines.raineslearn.dataStructure.stack;

import java.util.Arrays;

/**
 * 循环数组中比当前元素大的下一个元素
 *
 * 数组是循环数组，并且最后要求的不是距离而是下一个元素。
 */
public class NextGreater {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(nextGreaterElements(new int[]{1,5,4,3})));
    }

    public static int[] nextGreaterElements(int[] nums) {
        int n = nums.length;
        int[] next = new int[n];
        Arrays.fill(next, -1);
        Stack<Integer> pre = new Stack<>();
        for (int i = 0; i < n * 2; i++) {
            int num = nums[i % n];
            while (!pre.isEmpty() && nums[pre.peek()] < num) {
                next[pre.pop()] = num;
            }
            if (i < n){
                pre.push(i);
            }
        }
        return next;
    }

}




















