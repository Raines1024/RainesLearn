package com.raines.raineslearn.annotation.custom.project.classAnnotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.METHOD, ElementType.TYPE })
public @interface MyAnnotation {
    // 为注解添加属性
    String color();

    String value() default "我是XXX"; // 为属性提供默认值

    int[] array() default { 1, 2, 3 };

    Gender gender() default Gender.MAN; // 添加一个枚举

    // 添加枚举属性
    MetaAnnotation metaAnnotation() default @MetaAnnotation(birthday = "我的出生日期为1995-8-8");
}