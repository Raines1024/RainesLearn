package com.raines.raineslearn.aopDemo.annotation;

/**
 * Created by Administrator on 2018/3/12.
 */

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)//注解会在class中存在，运行时可通过反射获取
@Documented//文档生成时，该注解将被包含在javadoc中，可去掉
@Target(ElementType.METHOD)//目标是方法
public @interface CacheRefreshs {
	CacheRefresh[] value();
}
