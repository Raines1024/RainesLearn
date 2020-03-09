package com.raines.raineslearn.demo;

public class Demo {

    public static void main(String[] args) {
        String res = Long.valueOf("1111111111111111111111111111111",2).toString();
        System.out.println(res);
        int a=2;
        //0000 0000 0000 0010
        //1111 1111 1111 1101
        //1111 1111 1111 1100
        //1000 0000 0000 0011
        System.out.println("a 非的结果是："+(~a));
        System.out.println((byte) 128);//-128
        System.out.println((byte) -129);//127
        //1010
        //01111111
        //11111111
        //00000000 00000000 00000000 00001010
        //11111111 11111111 11111111 11110101
        //
        System.out.println("~b2: " + ~10);
    }

}
