package com.raines.raineslearn.leetcode;

/**
 * 给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。
 * <p>
 * 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
 * <p>
 * 您可以假设除了数字 0 之外，这两个数都不会以 0 开头。
 * <p>
 * 示例：
 * <p>
 * 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
 * 输出：7 -> 0 -> 8
 * 原因：342 + 465 = 807
 */

/**
 * 坑太多了：
 * 比如99+1的进位
 * 各种空指针的判断
 */


/**
 * Definition for singly-linked list.
 * public class ListNode {
 * int val;
 * ListNode next;
 * ListNode(int x) { val = x; }
 * }
 */
class Solution {

    /**
     * 哑节点（dummy node）是初始值为NULL的节点，创建在使用到链表的函数中，可以起到避免处理头节点为空的边界问题的作用，减少代码执行异常的可能性。
     * @param l1
     * @param l2
     * @return
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        //定义哑节点。
        ListNode dummyHead = new ListNode(0);
        ListNode p = l1, q = l2, curr = dummyHead;
        int carry = 0;
        //如果结点不都为null则一直遍历
        while (p != null || q != null) {
            //如果结点为null则值默认为0
            int x = (p != null) ? p.val : 0;
            int y = (q != null) ? q.val : 0;
            //把两个链表相同位置结点的数值和进位加起来
            int sum = carry + x + y;
            //给进位重新赋值
            carry = sum / 10;
            //当前结点的下一个结点为本次计算的结点
            curr.next = new ListNode(sum % 10);
            //当前结点改为下一个结点
            curr = curr.next;
            //如果链表的还有结点，还把下一个结点替换当前结点，继续循环
            if (p != null) p = p.next;
            if (q != null) q = q.next;
        }
        //如果循环结束后还有进位，则把当前结点的下一个结点放入进位数值
        if (carry > 0) {
            curr.next = new ListNode(carry);
        }
        return dummyHead.next;
    }

    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public static void main(String[] args) {
        ListNode l1 = new ListNode(8);
        ListNode l2 = new ListNode(9);
        ListNode l3 = new ListNode(9);
        l1.next = l2;
        l2.next = l3;

        ListNode t1 = new ListNode(2);
//        ListNode t2 = new ListNode(9);
//        ListNode t3 = new ListNode(4);
//        t1.next = t2;
//        t2.next = t3;

        Solution solution = new Solution();
        System.out.println(l1.val);
        System.out.println(t1.val);
        ListNode listNode1 = solution.addTwoNumbers(l1, t1);
        System.out.println("=======");

    }

}











