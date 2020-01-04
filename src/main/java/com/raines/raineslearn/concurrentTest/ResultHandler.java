package com.raines.raineslearn.concurrentTest;

public interface ResultHandler<T> {
    public void handle(T result);
}