package com.raines.raineslearn.computerStructure.recursion.line;

/**
 * 线性迭代
 */
public class Iteration {

    public static int factiorial(int n){
        //计算过程里并没有任何增长或者收缩，对于任何一个n，在计算过程中的每一步，在我们需要保存轨迹里，所有的东西就是变量product、counter和maxCount的当前值。这种过程为一个迭代计算过程。一般来说，迭代计算过程就是那种状态可以用固定数目的状态变量描述的计算过程；而与此同时，又存在着一套固定的规则，描述了计算过程在从一个状态到下一状态转换时，这些变量的更新方式；还有一个（可能有的）结果检测，它描述这一计算过程应该终止的条件。在计算n!时，所需的计算步骤随着n线性增长，这种过程称为线性迭代过程。

        return factIter(1,1,n);
    }

    /**
     * 计算阶乘： 先乘以1和2，而后一直乘到n
     */
    private static int factIter(int product, int counter, int maxCount){
        if (counter > maxCount){
            return product;
        }else {
            return factIter(counter*product,++counter,maxCount);
        }
    }

    public static void main(String[] args) {
        //计算3!的线性迭代过程
        //factiorial(3)
        //factIter(1,1,3)
        //factIter(1,2,3)
        //factIter(2,3,3)
        //factIter(6,4,3)
        System.out.println(factiorial(3));
    }
}
