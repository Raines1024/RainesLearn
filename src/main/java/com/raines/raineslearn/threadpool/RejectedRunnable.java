package com.raines.raineslearn.threadpool;

/**
 *拒绝策略集合
 */
public interface  RejectedRunnable extends Runnable {

    void rejected();
}
