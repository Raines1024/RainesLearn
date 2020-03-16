package com.raines.raineslearn.annotation.custom.singleValue;

/**
 * 单值注解
 *
 * 但是如果数据成员不使用value定义，新定义如下所示：
 * public @interface Good{
 *    String description();
 * }
 *
 * 现在，需要使用下面的注解方式
 * @Good(description="this good")
 */
public @interface Good {

    String value();

}
