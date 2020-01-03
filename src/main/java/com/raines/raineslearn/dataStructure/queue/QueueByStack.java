package com.raines.raineslearn.dataStructure.queue;

import com.raines.raineslearn.dataStructure.stack.Stack;

/**
 * 队列
 *
 * 栈的顺序为后进先出，而队列的顺序为先进先出。
 * 使用两个栈实现队列，一个元素需要经过两个栈才能出队列，在经过第一个栈时元素顺序被反转，经过第二个栈时再次被反转，此时就是先进先出顺序。
 */
public class QueueByStack {

    private Stack<Integer> in = new Stack<>();
    private Stack<Integer> out = new Stack<>();

    /**
     * 入队列
     * @param x
     */
    public void push(int x) {
        in.push(x);
    }

    /**
     * 出队列
     * @return
     */
    public int pop() {
        in2out();
        return out.pop();
    }

    /**
     * 查看队列元素
     * @return
     */
    public int peek() {
        in2out();
        return out.peek();
    }

    private void in2out() {
        if (out.isEmpty()) {
            while (!in.isEmpty()) {
                out.push(in.pop());
            }
        }
    }

    /**
     * 是否为空
     * @return
     */
    public boolean empty() {
        return in.isEmpty() && out.isEmpty();
    }

    public static void main(String[] args) {
        QueueByStack queue = new QueueByStack();
        queue.push(1);
        queue.push(2);
        queue.push(3);
        System.out.println(queue.pop());
        System.out.println(queue.pop());
        System.out.println(queue.peek());
        System.out.println(queue.pop());
    }

}
