package com.raines.raineslearn.annotation.custom.project.repeatAnnotation;

import java.lang.reflect.Method;

public class RepeatTest {

    @Log(modelName = "demo", option = "test", value = "ds")
    @Log(modelName = "demo2", option = "test", value = "ds")
    private static boolean demo() {
        System.out.println("demo Method.");
        return true;
    }

    public static void main(String[] args) {
        RepeatTest repeatTest = new RepeatTest();
        Class<?> repeatTestClass = (Class<?>) repeatTest.getClass();
        //获取该类所有属性
        Method[] methods = repeatTestClass.getDeclaredMethods();
        for (int i = 0; i < methods.length; i++) {
            Method method = methods[i];
            Log[] logs = method.getAnnotationsByType(Log.class);
            for (Log log : logs){
                if (log == null) continue;
                System.out.println(log.modelName());
            }
        }
    }

}
