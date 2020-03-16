package com.raines.raineslearn.reflect.accessClassAttr.operate;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Java反射得到属性的值和设置属性的值
 */
public class RefrectAttrTest {

    public static void main(String[] args){
        try {
            operateField();
            operateMethod();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void operateField() throws IllegalArgumentException,IllegalAccessException {
        Person person = new Person();
        person.setId(100);
        person.setAge(50);
        person.setName("张三");
        person.setAddress("北京");
        // 得到类对象
        Class<?> personClass = person.getClass();

        /*
         * 得到类中的所有属性集合
         */
        Field[] fs = personClass.getDeclaredFields();
        for (int i = 0; i < fs.length; i++) {
            Field f = fs[i];
            f.setAccessible(true); // 设置这些属性是可以访问的
            Object val = f.get(person);// 得到此属性的值

            System.out.println("name:" + f.getName() + ",value:" + val);
            String type = f.getType().toString();// 得到此属性的类型
            if (type.endsWith("String")) {
                System.out.println(f.getType() + "是String类型");
                f.set(person, "12"); // 给属性设值
            } else if (type.endsWith("int") || type.endsWith("Integer")) {
                System.out.println(f.getType() + "是int类型");
                f.set(person, 12); // 给属性设值
                Object newVal = f.get(person);// 得到此属性的值
                System.out.println("赋值后新的值是："+newVal);
            } else {
                System.out.println(f.getType());
            }
            System.out.println("-------------------------");
        }
    }

    public static void operateMethod() throws IllegalArgumentException, IllegalAccessException, InvocationTargetException {
        Person person = new Person();
        person.setId(100);
        person.setAge(50);
        person.setName("张三");
        person.setAddress("北京");
        // 得到类对象
        Class<?> personClass = person.getClass();
        /*
         * 得到类中的方法
         */
        Method[] methods = personClass.getMethods();
        for (int i = 0; i < methods.length; i++) {
            Method method = methods[i];
            if (method.getName().startsWith("get")) {
                System.out.print("methodName:" + method.getName() + "\t");
                //通过反射的方法调用invoke获取属性值
                System.out.println("value:" + method.invoke(person));// 得到get方法的值
            }
        }
    }

}
