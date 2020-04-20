package com.raines.raineslearn.async.chapter3.FutureTask;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.concurrent.atomic.AtomicReference;

/**
 * 主要用来展示AtomicReference使用方法
 * 模拟一个场景，在高并发的场景中，根据业务的需要，要求同时更新sequence和timestamp
 */
public class AtomicReferenceDemo {

    private Reference reference;

    private AtomicReference<Reference> atomicReference;

    /**
     * 构建器中初始化AtomicReference
     *
     * @param reference
     */
    public AtomicReferenceDemo(Reference reference) {
        this.reference = reference;
        this.atomicReference = new AtomicReference<>(reference);
    }

    /**
     * 获取并缓存原来的变量，这个变量包含原来的序列和时间戳
     * 基于原来的变量来更新新的时间戳和序列
     * 计算后，使用CAS操作更新原来的变量，更新的过程中，需要传递保存原来的变量
     * 如果保存的原来变量被其他线程修改了，就需要在这里重新拿到最新的变量，并再次计算和重试更新
     */
    public void atomic(Reference reference) {
        Reference referenceOld;
        Reference referenceNew;

        long sequence;
        long timestamp;

        while (true) {
            referenceOld = this.atomicReference.get();
            sequence = referenceOld.getSequence();
            sequence++;
            timestamp = System.currentTimeMillis();

            referenceNew = new Reference(sequence, timestamp);
            /**
             * 比较交换，比对的方式不是equals而是==
             */
            if (this.atomicReference.compareAndSet(referenceOld, referenceNew)) {
                reference.setSequence(sequence);
                reference.setTimestamp(timestamp);
                break;
            }
        }
    }
}

/**
 * 业务场景模拟
 * 序列需要自增并且时间需要更新成最新的时间戳
 */
@Data
@AllArgsConstructor
class Reference {
    /**
     * 序列
     */
    private long sequence;
    /**
     * 时间戳
     */
    private long timestamp;
}