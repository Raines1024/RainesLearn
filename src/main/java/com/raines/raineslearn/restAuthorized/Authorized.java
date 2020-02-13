package com.raines.raineslearn.restAuthorized;

import java.lang.annotation.*;

/**
 * 1、定义注解
 * 安全认证
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Authorized {

    String value() default "";

}