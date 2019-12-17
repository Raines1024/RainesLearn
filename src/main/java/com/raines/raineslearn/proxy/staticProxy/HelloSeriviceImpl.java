package com.raines.raineslearn.proxy.staticProxy;

public class HelloSeriviceImpl implements HelloSerivice{

    @Override
    public void say() {
        System.out.println("hello world");
    }

}