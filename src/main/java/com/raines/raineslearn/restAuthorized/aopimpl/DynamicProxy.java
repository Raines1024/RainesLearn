package com.raines.raineslearn.restAuthorized.aopimpl;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 动态代理类
 * 使用动态代理可以为一个或多个接口在运行期动态生成实现对象，生成的对象中实现接口的方法时可以添加增强代码，从而实现AOP
 * 缺点是只能针对接口进行代理，同时由于动态代理是通过反射实现的，有时可能要考虑反射调用的开销，否则很容易引发性能问题。
 */
public class DynamicProxy implements InvocationHandler {

    /**
     * 需要代理的目标类
     */
    private Object target;

    /**
     * 写法固定，aop专用:绑定委托对象并返回一个代理类
     *
     * @param target
     * @return
     */
    public Object bind(Object target) {
        this.target = target;
        return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), this);
    }

    /**
     * 调用 InvocationHandler接口定义方法
     *
     * @param proxy  指被代理的对象。
     * @param method 要调用的方法
     * @param args   方法调用时所需要的参数
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object result = null;
        // 切面之前执行
        System.out.println("[动态代理]切面之前执行");

        // 执行业务
        result = method.invoke(target, args);

        // 切面之后执行
        System.out.println("[动态代理]切面之后执行");

        return result;
    }

}