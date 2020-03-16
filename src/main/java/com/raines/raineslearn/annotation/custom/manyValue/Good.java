package com.raines.raineslearn.annotation.custom.manyValue;

/**
 * 多值注解
 */
public @interface Good {

    String description();
    //当使用默认值注解的时候，target成员可以不指定，除非想为target设置不同的值。
    String target() default "examine";

}
