package com.raines.raineslearn.reflect.accessClassAttr.access;

import java.lang.reflect.Field;

/**
 * Java反射得到属性的值
 */
public class RefrectGetAttrValueTest {

    public static void main(String[] args) throws IllegalArgumentException,IllegalAccessException {
        Company c = new Company();
        c.setName("XX科技公司");
        c.setAddress("地球村");

        Field fields[] = c.getClass().getDeclaredFields();// 获得对象所有属性
        for (int i = 0; i < fields.length; i++) {
            Field field = fields[i];
            field.setAccessible(true);// 修改访问实体private属性的权限
            System.out.println(field.getName() + ":" + field.get(c));// 读取属性值
        }
    }

}
