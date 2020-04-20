package com.raines.raineslearn.async.chapter4.TaskExecutor;

import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;

import java.lang.reflect.Method;

public class MyAsyncUncaughtExceptionHandler implements AsyncUncaughtExceptionHandler {

    @Override
    public void handleUncaughtException(Throwable ex, Method method, Object... params) {
        // handle exception
    	System.out.println(ex.getLocalizedMessage());
    }
}