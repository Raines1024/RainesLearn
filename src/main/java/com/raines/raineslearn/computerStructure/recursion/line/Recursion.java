package com.raines.raineslearn.computerStructure.recursion.line;

/**
 * 线性递归
 */
public class Recursion {

    public static void main(String[] args) {
        // 3!的线性递归过程,利用代换模型观看这一过程在计算3!时表现出的行为（代换模型揭示出一种先逐步展开而后收缩的形状）。
        //在展开阶段里，这一计算过程构造起一个推迟进行的操作所形成的链条（在这里是一个乘法的链条），收缩阶段表现为这些运算的实际执行。这种类型的计算过程由一个推迟执行的运算链条刻画，称为一个递归计算过程。要执行这种计算过程，解释器就需要维护好那些以后将要执行的操作的轨迹。在计算阶乘n!时，推迟执行的乘法链条的长度也就是为保存其轨迹需要保存的信息量，这个长度随着n值而线性增长（正比于n），就像计算中的步骤数目一样。这样的计算过程称为一个线性递归过程。
        //fuctorial(3)
        //3 * fuctorial(2)
        //3 * 2 * fuctorial(1)
        //3 * 2 * 1
        //3 * 2
        //6
        System.out.println(fuctorial(3));
    }

    /**
     * 计算阶乘：对于一个正整数n，n!就等于 n*(n-1)!
     */
    public static int fuctorial(int n){
        if (n == 1){
            return 1;
        }else {
            return n * fuctorial(n-1);
        }
    }


}
