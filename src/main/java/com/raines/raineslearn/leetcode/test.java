package com.raines.raineslearn.leetcode;

import java.util.ArrayList;
import java.util.List;

public class test {

    public static void main(String[] args) {

    }

    /**
     * 多少利率投资翻倍所需年数
     * @param rate 利率(百分比）
     * @return
     */
    public static double fanbei(double rate){
        return 72/rate;
    }

    //每年定投多少钱，坚持几年后
    public static double dingtou(double money,double rate,int year){
        double start = money;
        rate = rate/100;
        for (int i = 1;i <= year;i++){
            money += money*rate+start;
        }
        return money-start;
    }

    /**
     * 计算多少钱在多少利率的情况下几年后的总额
     * @param allMoney 钱数
     * @param rate 利率(百分比）
     * @param year 年数
     * @return
     */
    public static double sumTime(double allMoney,double rate,int year){
        for (int i = 0;i < year;i++){
            allMoney += allMoney * rate/100;
//            System.out.println(allMoney);
        }
        return allMoney;
    }



}