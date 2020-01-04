package com.raines.raineslearn.algorithmThinking.doublePointer;

import java.util.Arrays;
import java.util.HashSet;

/**
 * 反转字符串中的元音字符
 * 使用双指针，一个指针从头向尾遍历，一个指针从尾到头遍历，当两个指针都遍历到元音字符时，交换这两个元音字符。
 * 为了快速判断一个字符是不是元音字符，我们将全部元音字符添加到集合 HashSet 中，从而以 O(1) 的时间复杂度进行该操作。
 * 时间复杂度为 O(N)：只需要遍历所有元素一次
 * 空间复杂度 O(1)：只需要使用两个额外变量
 * <p>
 * Given s = "leetcode", return "leotcede".
 */
public class ReverseVowels {

    private final static HashSet<Character> vowels = new HashSet<>(Arrays.asList('a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U'));

    public static void main(String[] args) {
        System.out.println(reverseVowels("leetcode"));
    }

    public static String reverseVowels(String s) {
        if (s == null) return null;
        int i = 0, j = s.length() - 1;
        char[] result = new char[s.length()];
        //i = j时把中间的字母写入result中
        while (i <= j) {
            char ci = s.charAt(i);
            char cj = s.charAt(j);
            if (!vowels.contains(ci)) {
                //左边指针不是元音字符，原位置不变动
                result[i++] = ci;
            } else if (!vowels.contains(cj)) {
                //右边指针不是元音字符，原位置不变动
                result[j--] = cj;
            } else {
                //俩边都是元音字符，交换位置
                result[i++] = cj;
                result[j--] = ci;
            }
        }
        return new String(result);
    }



}































