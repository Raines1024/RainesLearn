package com.raines.raineslearn.leetcode.d1226;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 3.无重复字符的最长子串
 * 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
 * 示例 1:
 * 输入: "abcabcbb"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 * 示例 2:
 * 输入: "bbbbb"
 * 输出: 1
 * 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
 * 示例 3:
 * 输入: "pwwkew"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
 *      请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
 */
class Solution {

    /**
     * 碰到重复字符的时候，把之前未重复的加上再重新计算
     *
     * @param s
     * @return
     */
    public static int lengthOfLongestSubstring(String s) {
        int max = 0;
        StringBuilder builder = new StringBuilder();
        Stack stringStack = new Stack(10);
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (stringStack.exist(stringStack, chars[i])) {
                builder.append(stringStack.length() + ",");
                stringStack = new Stack(10);
                for (int j = i - 1; j >= 0; j--) {
                    if (chars[i] != chars[j]) {
                        stringStack.push(chars[j]);
                    } else {
                        break;
                    }
                }
            }
            stringStack.push(chars[i]);
        }
        builder.append(stringStack.length());
        String[] strings = builder.toString().split(",");
        for (int i = 0; i < strings.length; i++) {
            int j = Integer.parseInt(strings[i]);
            if (j > max) {
                max = j;
            }
        }
        return max;
    }

    /**
     * 优化的滑动窗口
     * 滑动窗口是数组/字符串问题中常用的抽象概念。窗口通常是在数组/字符串中由开始和结束索引定义的一系列元素的集合，即 [i, j)（左闭，右开）。
     * 而滑动窗口是可以将两个边界向某一方向“滑动”的窗口。例如，我们将 [i, j) 向右滑动 11 个元素，则它将变为 [i+1, j+1)（左闭，右开）。
     *
     * 定义字符到索引的映射，而不是使用集合来判断一个字符是否存在。当我们找到重复的字符时，我们可以立即跳过该窗口。
     * 也就是说，如果 s[j] 在 [i, j)范围内有与 j' 重复的字符，我们不需要逐渐增加 i 。 我们可以直接跳过 [i，j'] 范围内的所有元素，并将 i 变为 j' + 1
     *
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring3(String s) {
        int n = s.length(), ans = 0;
        Map<Character, Integer> map = new HashMap<>(); // current index of character
        // try to extend the range [i, j]
        for (int j = 0, i = 0; j < n; j++) {
            //如果字符串的第j位是map的key，则取出value值与其比较，把大者赋值给i
            if (map.containsKey(s.charAt(j))) {
                i = Math.max(map.get(s.charAt(j)), i);
            }
            //比较ans和j-i+1的值，把大者赋值给ans
            ans = Math.max(ans, j - i + 1);
            //取出字符串的第j位当key，j+1当value
            map.put(s.charAt(j), j + 1);
        }
        return ans;
    }

    /**
     * 暴力法
     *
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring2(String s) {
        int n = s.length();
        int ans = 0;
        for (int i = 0; i < n; i++)
            for (int j = i + 1; j <= n; j++)
                if (allUnique(s, i, j)) ans = Math.max(ans, j - i);
        return ans;
    }

    public boolean allUnique(String s, int start, int end) {
        Set<Character> set = new HashSet<>();
        for (int i = start; i < end; i++) {
            Character ch = s.charAt(i);
            if (set.contains(ch)) return false;
            set.add(ch);
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstring("anviaj"));
    }


}


class Stack<T> {

    private Object[] data = null;

    private int size = 0;

    private int top = -1;

    public Stack() {
        this(10);
    }

    public void extend(Object[] object) {
        this.size = size + 10;
        Object[] o = new Object[size];
        for (int i = 0; i < object.length; i++) {
            o[i] = object[i];
        }
        data = o;
    }

    public Stack(int size) {
        this.size = size;
        data = new Object[size];
    }

    public boolean exist(Stack stack, T o) {
        for (int i = 0; i < stack.length(); i++) {
            if (data[i] == o) {
                return true;
            }
        }
        return false;
    }


    public void push(T o) {
        if (top == size - 1) {
            extend(data);
//            throw new RuntimeException("大于容量");
        }
        data[++top] = o;
    }

//    public T pop() {
//        if (top == -1) {
//            throw new RuntimeException("空栈");
//        }
//        return (T) data[top--];
//    }

    public int length() {
        return top + 1;
    }


}





























