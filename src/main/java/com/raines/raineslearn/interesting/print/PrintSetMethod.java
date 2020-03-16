package com.raines.raineslearn.interesting.print;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * 用处：
 * 1.由实体类A向实体类B迁移数据
 * 2.生成实体类A的所有set方法供使用（现在只能生成前缀为private String 的字段），根据需要自行扩展
 */
public class PrintSetMethod {


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StringBuffer stringBuffer = new StringBuffer();
        List<String> resultList = new ArrayList<>();
        while (scanner.hasNext()) {
            String s = scanner.nextLine();
            stringBuffer.append(s);
            if (s.equals("exit")) {
                break;
            }
        }
        Arrays.asList(stringBuffer.toString().split("private String ")).forEach(x -> {
            resultList.add(x.split(";")[0]);
        });
        resultList.forEach(x -> {
            if (!x.trim().equals("")) {
                String str = x.trim().substring(0, 1).toUpperCase() + x.substring(1);
                String get = "importParam.get" + str+"()";
                System.out.println("if("+get+"!=null && !("+get+".equals(\"\")"+")){");
                System.out.println("config.set"+str+"(importParam.get" + str + "(" + "));");
                System.out.println("}");
            }
        });
    }

}
