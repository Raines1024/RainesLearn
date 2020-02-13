package com.raines.raineslearn.dataStructure.offer;

public class Stack<E> {

    private Object[] data = null;
    private int maxSize = 0;
    private int top = -1;

    Stack() {
        this(10);
    }

    Stack(int initialSize) {
        if (initialSize >= 0) {
            this.maxSize = initialSize;
            data = new Object[initialSize];
            top = -1;
        } else {
            throw new RuntimeException("初始化大小不能小于0：" + initialSize);
        }
    }

    public E peek(){
        if (top == -1){
            throw new RuntimeException("栈为空");
        }else {
            return (E)data[top];
        }
    }

    public boolean push(E e) {
        if (top == maxSize - 1) {
            throw new RuntimeException("栈已满，无法将元素入栈");
        }else {
            data[++top] = e;
            return true;
        }
    }

    public E pop(){
        if (top == -1) {
            throw new RuntimeException("栈为空");
        }else {
            return (E)data[top--];
        }
    }


}
