package com.raines.raineslearn.leetcode.d1227;

import java.util.Arrays;

/**
 * 4. 寻找两个有序数组的中位数
 *
 * （中位数（Median）又称中值，统计学中的专有名词，是按顺序排列的一组数据中居于中间位置的数，代表一个样本、种群或概率分布中的一个数值，其可将数值集合划分为相等的上下两部分。对于有限的数集，可以通过把所有观察值高低排序后找出正中间的一个作为中位数。如果观察值有偶数个，通常取最中间的两个数值的平均数作为中位数。）
 *
 * 给定两个大小为 m 和 n 的有序数组 nums1 和 nums2。
 * <p>
 * 请你找出这两个有序数组的中位数，并且要求算法的时间复杂度为 O(log(m + n))。
 * <p>
 * 你可以假设 nums1 和 nums2 不会同时为空。
 * <p>
 * 示例 1:
 * <p>
 * nums1 = [1, 3]
 * nums2 = [2]
 * <p>
 * 则中位数是 2.0
 * 示例 2:
 * <p>
 * nums1 = [1, 2]
 * nums2 = [3, 4]
 * <p>
 * 则中位数是 (2 + 3)/2 = 2.5
 */
class Solution {

    /**
     * 查找一个数组元素的下标。
     * 二分法适用于已经排好序的数组，定义两个变量，一个low,一个high,则mid=(low+high)/2
     *
     * 算法核心：
     * 如果 value==arr[mid],中间值正好等于要查找的值，则返回下标，return mid;
     *
     * 如果 value<arr[mid],要找的值小于中间的值，则再往数组的小端找，high=mid-1;
     *
     * 如果 value>arr[mid],要找的值大于中间的值，则再往数组的大端找，low=mid+1;
     *
     */
//    public static void main(String[] args) {
//        int[] arr= {30,20,50,10,80,9,7,12,100,40,8};
//        Arrays.sort(arr);
//        System.out.println(Arrays.toString(arr));
//        System.out.println(myBinarySearch(arr,40));
//    }

    public static int myBinarySearch(int[] arr,int value) {
        int low=0;
        int high=arr.length-1;
        while(low<=high) {
            int mid=(low+high)/2;
            if(value==arr[mid]) {
                return mid;
            }
            if(value>arr[mid]) {
                low=mid+1;
            }
            if(value<arr[mid]) {
                high=mid-1;
            }

        }
        return -1;//没有找到返回-1
    }

    public static void main(String[] args) {
        System.out.println(findMedianSortedArrays(new int[]{1, 2}, new int[]{3, 4}));
    }

    /**
     * 递归
     * @param A
     * @param B
     * @return
     */
    public static double findMedianSortedArrays(int[] A, int[] B) {
        int m = A.length;
        int n = B.length;
        //如果数组A的长度大于数组B的长度，把B赋值给A，把A赋值给B，把B的长度赋值给A，把A的长度赋值给B（A，B互换） --> 确保m <= n，确保A比B长
        if (m > n) {
            int[] temp = A;
            A = B;
            B = temp;
            int tmp = m;
            m = n;
            n = tmp;
        }
        //
        int iMin = 0, iMax = m, halfLen = (m + n + 1) / 2;
        while (iMin <= iMax) {
            // 小数组长度的一半（向下取整）
            int i = (iMin + iMax) / 2;
            //两个数组总长度一半（向上取整） 减去  小数组长度的一半
            int j = halfLen - i;
            //
            if (i < iMax && B[j - 1] > A[i]) {
                iMin = i + 1; // i is too small
            } else if (i > iMin && A[i - 1] > B[j]) {
                iMax = i - 1; // i is too big
            } else { // i is perfect
                int maxLeft = 0;
                if (i == 0) {
                    maxLeft = B[j - 1];
                } else if (j == 0) {
                    maxLeft = A[i - 1];
                } else {
                    maxLeft = Math.max(A[i - 1], B[j - 1]);
                }
                if ((m + n) % 2 == 1) {
                    return maxLeft;
                }

                int minRight = 0;
                if (i == m) {
                    minRight = B[j];
                } else if (j == n) {
                    minRight = A[i];
                } else {
                    minRight = Math.min(B[j], A[i]);
                }

                return (maxLeft + minRight) / 2.0;
            }
        }
        return 0.0;
    }



}




















