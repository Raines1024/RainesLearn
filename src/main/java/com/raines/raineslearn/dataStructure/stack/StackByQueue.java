package com.raines.raineslearn.dataStructure.stack;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 用队列实现栈
 *
 * 在将一个元素 x 插入队列时，为了维护原来的后进先出顺序，需要让 x 插入队列首部。
 * 而队列的默认插入顺序是队列尾部，因此在将 x 插入队列尾部之后，需要让除了 x 之外的所有元素出队列，再入队列。
 */
public class StackByQueue<T> {

    private Queue<T> queue;

    public StackByQueue() {
        queue = new LinkedList<>();
    }

    public void push(T x) {
        queue.add(x);
        int cnt = queue.size();
        while (cnt-- > 1) {
            queue.add(queue.poll());
        }
    }

    public T pop() {
        return queue.remove();
    }

    public T top() {
        return queue.peek();
    }

    public boolean empty() {
        return queue.isEmpty();
    }

}
