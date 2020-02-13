package com.raines.raineslearn.dataStructure.offer;

public class Queue<E> {

    //存储队列数据的数组
    private Object[] data = null;

    //队列的容量
    private int maxSize;
    //队列头标记
    private int front;
    //队列尾标记
    private int rear;

    public E peek(){
        return (E) data[front];
    }

    public E poll(){
        E value = (E) data[front];
        data[front++] = null;
        return value;
    }

    public boolean add(E e){
        if (rear == maxSize){
            throw new RuntimeException("已满");
        }else {
            data[rear++] = e;
            return true;
        }
    }

    public Queue(){
        this(10);
    }

    public Queue(int initialSize){
        if (initialSize >= 0){
            this.maxSize = initialSize;
            data = new Object[initialSize];
            front = rear = 0;
        }else {
            throw new RuntimeException("初始化大小不能小于0:"+initialSize);
        }
    }



}
