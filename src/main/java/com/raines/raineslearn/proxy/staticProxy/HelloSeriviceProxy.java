package com.raines.raineslearn.proxy.staticProxy;

/**
 * 静态代理的用途 控制真实对象的访问权限 通过代理对象控制对真实对象的使用权限。
 */
public class HelloSeriviceProxy implements HelloSerivice{

    private HelloSerivice target;
    public HelloSeriviceProxy(HelloSerivice target) {
        this.target = target;
    }

    @Override
    public void say() {
        System.out.println("记录日志");
        target.say();
        System.out.println("清理数据");
    }
}