package com.raines.raineslearn.annotation.custom.project.repeatAnnotation;


import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)//注解会在class中存在，运行时可通过反射获取
@Target(ElementType.METHOD)//目标是方法
@Documented//文档生成时，该注解将被包含在javadoc中，可去掉
@Repeatable(Logs.class)//这行建立了@Log和@Logs的关系
public @interface Log {

    String value() default "";
    /**
     * 模块名字
     */
    String modelName() default "";

    /**
     * 操作类型
     */
    String option() default "";

}
