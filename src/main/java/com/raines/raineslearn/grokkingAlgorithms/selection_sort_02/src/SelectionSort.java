package com.raines.raineslearn.grokkingAlgorithms.selection_sort_02.src;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 选择排序
 * 使用ArrayList实现
 */
public class SelectionSort {

    /**
     * 将数组元素按从小到大的顺序排列
     * @param arr
     * @return
     */
    private static List<Integer> selectionSort(List<Integer> arr) {
        List<Integer> newArr = new ArrayList<>(arr.size());
        //依次寻找最小的元素放入集合并在原集合中把该元素移除
        int size = arr.size();
        for (int i = 0; i < size; i++) {
            int smallest = findSmallest(arr);
            newArr.add(arr.get(smallest));
            arr.remove(smallest);
        }

        return newArr;
    }

    /**
     * 编写一个用于找出数组中最小元素索引的函数
     *
     * @param arr
     * @return
     */
    private static int findSmallest(List<Integer> arr) {
        int smallest = arr.get(0);
        int smallestIndex = 0;
        for (int i = 0; i < arr.size(); i++) {
            if (arr.get(i) < smallest) {
                smallest = arr.get(i);
                smallestIndex = i;
            }
        }
        return smallestIndex;
    }

    public static void main(String[] args) {
        List<Integer> arr = new ArrayList<>(Arrays.asList(5, 3, 6, 2, 10));
        System.out.println(selectionSort(arr)); //[2, 3, 5, 6, 10]
    }
}