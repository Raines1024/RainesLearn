package com.raines.raineslearn.annotation.custom.project.verifyData;

import java.lang.reflect.Field;

/**
 * 测试类
 */
public class Main {
    public static void main(String[] args) {
        try {
            Person person = new Person();
            person.setId(1);
            person.setAge(50);
            person.setName("张三");
            person.setAddress("北京");
//            person.setSex("male");
            validateParam(person);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 验证数据完整性
     *
     * @param person
     * @throws Exception
     */
    public static void validateParam(Person person) throws Exception{
        Class<?> personClass = (Class<?>)person.getClass();
        //获取该类所有属性
        Field[] field = personClass.getDeclaredFields();

        for (int i = 0; i < field.length; i++) {
            Field f= field[i];
            f.setAccessible(true);
//            System.out.println(f.isAnnotationPresent(NotEmpty.class)+";"+f.getName());
            if (f.getAnnotation(NotEmpty.class)!= null) {
                //获取属性值
                Object attrValue=f.get(person);
                if(attrValue==null||attrValue.toString().trim().equals("")){
//                    throw new RuntimeException(f.getName()+"属性值不能为空");
                    System.out.println(f.getName()+"属性值不能为空");
                }
                continue;
            }
        }
    }
}
