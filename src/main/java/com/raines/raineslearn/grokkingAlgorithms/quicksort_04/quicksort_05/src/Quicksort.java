package com.raines.raineslearn.grokkingAlgorithms.quicksort_04.quicksort_05.src;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 快速排序
 */
public class Quicksort {
    public static void main(String[] args) {
        System.out.println(quicksort(Arrays.asList(10, 5, 2, 3))); // [2, 3, 5, 10]
    }

    private static List<Integer> quicksort(List<Integer> list) {
        if (list.size() < 2) {
            //基线条件
            // base case, arrays with 0 or 1 element are already "sorted"
            return list;
        } else {
            //递归条件
            // recursive case
            Integer pivot = list.get(0);

            //由所有小于等于基准值的元素组成的子数组（跳过基准值）
            // sub-array of all the elements less than the pivot
            List<Integer> less = list.stream().skip(1).filter(el -> el <= pivot)
                    .collect(Collectors.toList());

            // sub-array of all the elements greater than the pivot
            List<Integer> greater = list.stream().skip(1).filter(el -> el > pivot)
                    .collect(Collectors.toList());

            return Stream.of(
                    quicksort(less).stream(),
                    Stream.of(pivot),
                    quicksort(greater).stream())
                    .flatMap(Function.identity()).collect(Collectors.toList());
        }
    }
}