package com.raines.raineslearn.easyDemand;

import java.text.DecimalFormat;

/**
 * 如果为.00去掉小数点
 */
public class CutZeroNumPoint {

    private static DecimalFormat mFormat;
    private static DecimalFormat mFormat1;

    static  {
        mFormat = new DecimalFormat("#.00");
        mFormat1 = new DecimalFormat("#0");
    }

    public static void main(String[] args) {
        double d = 123.00;
        if (isInt(d)){
            System.err.println(mFormat1.format(d));
        }else {
            System.err.println(mFormat.format(d));
        }

    }


    private static boolean isInt(double v) {
        long x;
        x = (long) v;
        if (v - x == 0) {
            return true;
        } else {
            return false;
        }
    }

}
