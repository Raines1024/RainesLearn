package com.raines.raineslearn.demo;

public class B {
    private int i = 5;
    private Integer ii = 128;

    public static void main(String[] args) throws InterruptedException {
        B b = new B();
        Thread.sleep(1000 * 1000);
        System.out.println(b);
    }
}
