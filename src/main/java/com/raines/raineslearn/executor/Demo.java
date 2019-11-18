package com.raines.raineslearn.executor;

public class Demo {
    public static void main(String[] args) {

        MyExecutor myExecutor = new MyExecutor();
        try {
            myExecutor.fun();
            System.out.println("我先走着啦");
        } catch (Exception e) {
            throw new RuntimeException("业务程序报错啦！！");
        }

    }

}