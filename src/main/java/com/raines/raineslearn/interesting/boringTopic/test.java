package com.raines.raineslearn.interesting.boringTopic;

import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 一个不知名公司的无聊面试题
 */
public class test {

    String a = "  在这篇文章里，我";
    String b = "们发现了如下问题： ";
    String c = "  1. 格式。";
    String d = "  2. 很多语法错误，";
    String e = "例如这些";

    public static void main(String[] argv) {
        // 这是个maven项目, 使用了lombok
        // 请尽量用Java8的stream和lambda表达式语法实现下面的函数
        // 可以在main里测试
        List<TextInfo> list = new ArrayList<>();
        TextInfo t1 = new TextInfo();
        t1.setContent("这");
        t1.setStartX(3);
        t1.setStartY(0);
        list.add(t1);

        TextInfo t2 = new TextInfo();
        t2.setContent("文");
        t2.setStartX(5);
        t2.setStartY(0);
        list.add(t2);

        TextInfo t3 = new TextInfo();
        t3.setContent("现");
        t3.setStartX(2);
        t3.setStartY(1);
        list.add(t3);

        TextInfo t4 = new TextInfo();
        t4.setContent("发");
        t4.setStartX(1);
        t4.setStartY(1);
        list.add(t4);

        TextInfo t5 = new TextInfo();
        t5.setContent("篇");
        t5.setStartX(4);
        t5.setStartY(0);
        list.add(t5);

        TextInfo t6 = new TextInfo();
        t6.setContent("章");
        t6.setStartX(6);
        t6.setStartY(0);
        list.add(t6);



        System.out.println(getSortedContent(list));
        System.out.println(getAreaCoordinate(list));
    }

    // 给定一个乱序的TextInfo List, 返回一个按顺序排列的字符串
    // 例如"这""文""在""发""现"..."在""篇", 返回"在这篇文章里，我们发现了如下问题："
    // 每个字的startX, startY都给定, 且为了简化问题, 我们假设所有字宽高都一样
    public static String getSortedContent(List<TextInfo> textInfos) {
        //先按纵坐标正序排序，再按横坐标正序排序
        Collections.sort(textInfos,  (a1, a2) -> {
            Double y = a1.getStartY() - a2.getStartY();
            Double x = a1.getStartX() - a2.getStartX();
            if (y == 0) {
                return x.intValue();
            }
            return y.intValue();
        });
        return textInfos.stream().map(TextInfo::getContent).collect(Collectors.joining(""));
    }

    // 给定一个乱序的TextInfo List, 返回它们所在的矩形
    // 返回List顺序为startX, startY, endX, endY
    // 以TextInfo类里给的例子为例, "在这篇文章里，我们发现了如下问题："所在的矩形框应为(0, 0)到(10, 2), 返回[0, 0, 10, 2]
    public static List<Double> getAreaCoordinate(List<TextInfo> textInfos) {
        double xMin = textInfos.stream().map(TextInfo::getStartX).min(Double::compareTo).get();
        double yMin = textInfos.stream().map(TextInfo::getStartY).min(Double::compareTo).get();
        double xMax = textInfos.stream().map(TextInfo::getStartX).max(Double::compareTo).get();
        double yMax = textInfos.stream().map(TextInfo::getStartY).max(Double::compareTo).get();
        return Stream.of(xMin,yMin,xMax,yMax).collect(Collectors.toList());
    }

    // 给定一个TextInfo List, 返回一个随机的乱序TextInfo List
    // 要保证每种情况都要等概率的出现，比如给定[1, 2, 3], 可能随机返回[1, 2, 3], [1, 3, 2], [2, 1, 3], [2, 3, 1], [3, 1, 2], 3, 2, 1]
//    public static List<TextInfo> getRandomTextInfos(List<TextInfo> textInfos) {
//
//    }

    @Data
    @ToString
    public static class TextInfo {
        private String content; // 文字内容
        private double startX; // 文字起始x坐标
        private double startY; // 文字起始y坐标
        private double width; // 文字宽度
        private double height; // 文字高度
        private String font; // 文字字体

        /**
         * 拿以下这段文字举个例子:
         *
         *      在这篇文章里，我
         *   们发现了如下问题：
         *      1. 格式。
         *      2. 很多语法错误，
         *   例如这些
         *
         * 其中每个字符都可以表示为一个TextInfo, 假设字的宽高都是1, 则
         * "在"字的坐标可能是(2, 0), "这"字的坐标可能是(3, 0), "们"字坐标可能是(0, 1), "例"字坐标可能是(0, 4),
         * "在这篇文章里，我们发现了如下问题："所在的矩形框应为(0, 0)到(10, 2)。
         */
    }
}
