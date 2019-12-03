package com.raines.raineslearn.assertDemo;

/**
 * 使用assert断言demo
 * 使用断言执行的时候必须以"-ea"的选项来告诉jre必须执行assertion,不使用该选项执行时会忽略assertion语句不予执行。
 */
public class assertDemo {

    public static void main(String[] args) {
        System.out.println("start");
        assert true;
        System.out.println("go on");
        assert false:"stop";
        System.out.println("end");

//        String s = null;
//        assert s!=null?true:false;
//        assert false;
//        System.out.println("end");
    }

}
