package com.raines.raineslearn.reflect.accessprivate.accessAttribute;

import java.lang.reflect.Field;

/**
 * 访问私有属性
 */
public class TestPrivate2 {

    //利用反射修改其私有属性的值
    public static void main(String[] args) throws Exception {
        PrivateClass2 p = new PrivateClass2();
        Class<?> classType = p.getClass();

        Field field = classType.getDeclaredField("name");

        field.setAccessible(true); // 抑制Java对修饰符的检查
        field.set(p, "lisi");

        System.out.println(p.getName());
    }


}
