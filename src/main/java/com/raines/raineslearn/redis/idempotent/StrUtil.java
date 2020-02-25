package com.raines.raineslearn.redis.idempotent;


import org.springframework.util.StringUtils;

public class StrUtil {

    public static final String EMPTY = "";

    public static boolean isBlank(String str){
        return StringUtils.isEmpty(str);
    }

    public static boolean isNotBlank(String str){
        return !(StringUtils.isEmpty(str));
    }

}
