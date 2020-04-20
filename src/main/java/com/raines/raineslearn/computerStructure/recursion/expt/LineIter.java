package com.raines.raineslearn.computerStructure.recursion.expt;

/**
 * 线性迭代实现求幂
 * 空间为O(n),步数为O(1)
 */
public class LineIter {

    /**
     * 求幂
     *
     * @param b 基数
     * @param n 正整数的指数
     * @return
     */
    private static int expt(int b, int n) {
        return exptIter(b, n, 1);
    }

    private static int exptIter(int b, int counter, int product) {
        if (counter == 0) {
            return product;
        } else {
            return exptIter(b, counter - 1, b * product);
        }
    }

    public static void main(String[] args) {
        //expt(3,3)
        //exptIter(3,3,1)
        //exptIter(3,2,3)
        //exptIter(3,1,9)
        //exptIter(3,0,27)
        //27
        System.out.println(expt(3, 3));
    }

}
