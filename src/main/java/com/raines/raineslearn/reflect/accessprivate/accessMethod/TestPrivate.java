package com.raines.raineslearn.reflect.accessprivate.accessMethod;

import java.lang.reflect.Method;

/**
 * 调用私有方法
 */
public class TestPrivate {
    //利用反射机制在外部访问该方法
    public static void main(String[] args) throws Exception {
        PrivateClass p = new PrivateClass();

        Class<?> classType = p.getClass();

        // 获取Method对象
        Method method = classType.getDeclaredMethod("sayHello", new Class[]{String.class});

        method.setAccessible(true); // 抑制Java的访问控制检查
        // 如果不加上上面这句，将会Error: TestPrivate can not access a member of class PrivateClass with modifiers "private"
        String str = (String) method.invoke(p, new Object[]{"zhangsan"});

        System.out.println(str);
    }
}
