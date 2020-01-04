package com.raines.raineslearn.dataStructure.stack;

import java.util.Arrays;

/**
 * 数组中元素与下一个比它大的元素之间的距离
 * <p>
 * 在遍历数组时用栈把数组中的数存起来，如果当前遍历的数比栈顶元素来的大，说明栈顶元素的下一个比它大的数就是当前元素。
 *
 * Input: [73, 74, 75, 71, 69, 72, 76, 73]
 * Output: [1, 1, 4, 2, 1, 1, 0, 0]
 */
public class Location {

    public static int[] dailyTemperatures(int[] temperatures) {
        int n = temperatures.length;
        int[] dist = new int[n];
        Stack<Integer> indexs = new Stack<>();
        for (int curIndex = 0; curIndex < n; curIndex++) {
            //数组当前下标的数值大于数组
            while (!indexs.isEmpty() && temperatures[curIndex] > temperatures[indexs.peek()]) {
                //栈顶元素的下一个比它大的数就是当前元素
                int preIndex = indexs.pop();
                //结果数组当前下标的值为当前元素的下标减去栈内下标的，差为距离
                dist[preIndex] = curIndex - preIndex;
            }
            //数组下标顺序
            indexs.push(curIndex);
        }
        return dist;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(dailyTemperatures(new int[]{2, 2, 2, 3})));
    }

}
