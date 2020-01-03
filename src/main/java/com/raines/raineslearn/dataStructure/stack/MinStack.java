package com.raines.raineslearn.dataStructure.stack;

/**
 * 最小值栈（使用栈获取最小值）
 *
 * 入栈时判断入栈值和当前对象最小值比较，把最小值入栈，出栈时出栈顶元素（当前对象最小值）
 *
 * 对于实现最小值队列问题，可以先将队列使用栈来实现，然后就将问题转换为最小值栈，这个问题出现在 编程之美：3.7。
 */
public class MinStack {

    private Stack<Integer> dataStack;
    private Stack<Integer> minStack;
    private int min;

    public MinStack() {
        dataStack = new Stack<>();
        minStack = new Stack<>();
        min = Integer.MAX_VALUE;
    }

    public void push(int x) {
        dataStack.push(x);
        min = Math.min(min, x);
        minStack.push(min);
    }

    public void pop() {
        dataStack.pop();
        minStack.pop();
        min = minStack.isEmpty() ? Integer.MAX_VALUE : minStack.peek();
    }

    public int getMin() {
        return minStack.peek();
    }

    public static void main(String[] args) {
        MinStack minStack = new MinStack();
        minStack.push(2);
        minStack.push(2);
        minStack.push(4);
        System.out.println(minStack.getMin());
    }

}
