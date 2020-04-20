package com.raines.raineslearn.computerStructure.recursion.expt;

/**
 * 线性递归实现求幂
 * 空间为O(n),步数为O(n)
 */
public class LineRecursion {

    /**
     * 求幂
     *
     * @param b 基数
     * @param n 正整数的指数
     * @return
     */
    private static int expt(int b, int n) {
        if (n == 0) {
            return 1;
        } else {
            return b * expt(b, n - 1);
        }
    }

    public static void main(String[] args) {
        //3,3
        //3*expt(3,2)
        //3* 3*expt(3,1)
        //3* 3* expt(3,0)
        //3* 3* 3* 1
        System.out.println(expt(3, 3));
    }

}
