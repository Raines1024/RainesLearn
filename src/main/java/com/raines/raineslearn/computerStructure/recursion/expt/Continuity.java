package com.raines.raineslearn.computerStructure.recursion.expt;

/**
 * 连续求平方（奇偶区分），以更少的步骤完成乘幂运算
 * 若n是偶数，b的n次幂 = (b 的 n/2 次幂) 的 平方
 * 若n是奇数，b的n次幂 = b * b 的n-1次幂
 * 空间和步数为以2为底的n的对数值
 */
public class Continuity {

    private static int fastExpt(int b, int n) {
        if (n == 0) {
            return 1;
        } else if (n % 2 == 0) {
            return fastExpt(b, n / 2) * fastExpt(b, n / 2);
        } else {
            return b * fastExpt(b, n - 1);
        }
    }

    public static void main(String[] args) {
        //fastExpt(3,3)
        //3*fastExpt(3,2)
        //3* fastExpt(3,1)*fastExpt(3,1)
        //3* 3*fastExpt(1,0) * 3*fastExpt(1,0)
        //3* 3*1* 3*1
        //27
        System.out.println(fastExpt(3,3));
    }

}
