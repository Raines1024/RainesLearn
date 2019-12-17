package com.raines.raineslearn.proxy;

import com.raines.raineslearn.proxy.dynamicProxy.MyInvocationHandler;
import com.raines.raineslearn.proxy.dynamicProxy.UserService;
import com.raines.raineslearn.proxy.dynamicProxy.UserServiceImpl;
import com.raines.raineslearn.proxy.staticProxy.HelloSerivice;
import com.raines.raineslearn.proxy.staticProxy.HelloSeriviceImpl;
import com.raines.raineslearn.proxy.staticProxy.HelloSeriviceProxy;
import org.junit.Test;

public class Main {

    /**
     * 演示静态代理
     */
    @Test
    public void testStaticProxy(){
        //目标对象
        HelloSerivice target = new HelloSeriviceImpl();
        //代理对象
        HelloSeriviceProxy proxy = new HelloSeriviceProxy(target);
        proxy.say();
    }

    /**
     * 演示动态代理
     */
    @Test
    public void testDynamicProxy(){
        UserService service = new UserServiceImpl();
        MyInvocationHandler handler = new MyInvocationHandler(service);
        UserService proxy = (UserService) handler.getProxy();
        proxy.add();
    }

}