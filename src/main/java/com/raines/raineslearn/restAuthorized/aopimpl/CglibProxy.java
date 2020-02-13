package com.raines.raineslearn.restAuthorized.aopimpl;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * 使用cglib动态代理
 * <p>
 * JDK中的动态代理使用时，必须有业务接口，而cglib是针对类的
 *
 * 动态字节码生成技术是指在运行时动态生成指定类的一个子类对象（注意是针对类），并覆盖其中特定方法，覆盖方法时可以添加增强代码，从而实现AOP。
 */
public class CglibProxy implements MethodInterceptor {

    private Object target;

    /**
     * 创建代理对象
     *
     * @param target
     * @return
     */
    public Object getInstance(Object target) {
        this.target = target;
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(this.target.getClass());
        // 回调方法
        enhancer.setCallback(this);
        // 创建代理对象
        return enhancer.create();
    }

    @Override
    public Object intercept(Object proxy, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        Object result = null;
        System.out.println("[cglib]切面之前执行");

        result = methodProxy.invokeSuper(proxy, args);

        System.out.println("[cglib]切面之后执行");

        return result;
    }

}