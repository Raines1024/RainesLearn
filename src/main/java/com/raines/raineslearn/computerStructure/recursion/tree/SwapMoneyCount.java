package com.raines.raineslearn.computerStructure.recursion.tree;

/**
 * 换零钱方式的统计
 * 硬币种数：50、25、10、5、1美分
 */
public class SwapMoneyCount {

    public static void main(String[] args) {
        SwapMoneyCount s = new SwapMoneyCount();
        //1;5,1;5,5,1;10,1;
        //1;5;1,5;10
        System.out.println(s.countChange(11));
    }

    private int countChange(int amount) {
        return cc(amount, 5);
    }

    /**
     * 计算有多少种换零钱的方式
     *
     * @param amount       钱数
     * @param kindsOfCoins 可用的硬币种数
     * @return 换零钱方式的总数
     */
    private int cc(int amount, int kindsOfCoins) {
        if (amount == 0) {
            return 1;
        } else if (amount < 0 || kindsOfCoins == 0) {
            return 0;
        } else {
            return cc(amount, kindsOfCoins - 1) + cc(amount - firstDenomination(kindsOfCoins), kindsOfCoins);
        }
    }

    /**
     * 以可用的硬币种数作为输入，返回第一种硬币的币值，默认硬币已经从最大到最小排序好了
     * 1美元 = 100美分
     * 硬币种数：50、25、10、5、1美分
     *
     * @param kindsOfCoins 可用的硬币种数
     * @return 每种硬币的面值
     */
    private int firstDenomination(int kindsOfCoins) {
        if (kindsOfCoins == 1) {
            return 1;
        } else if (kindsOfCoins == 2) {
            return 5;
        } else if (kindsOfCoins == 3) {
            return 10;
        } else if (kindsOfCoins == 4) {
            return 25;
        } else if (kindsOfCoins == 5) {
            return 50;
        }
        return 0;
    }

}
