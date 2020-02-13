package com.raines.raineslearn.effective.t2;

/**
 * 当构造方法参数过多时使用BUILDER模式

 构造方法的编写？
 可伸缩构造方法模式
 可伸缩构造方法模式是有效的，但是当有很有参数时，很难编写客户端代码，而且很难读懂它。
 JavaBeans模式
 JavaBeans模式本身有严重的缺陷，由于构造方法被分割成了多次屌用，所以在构造过程中JavaBean可能处于不一致的状态
 Builder模式
 结合可伸缩构造方法模式的安全性和JavaBean模式的可读性。客户端不直接构造所需的对象，而是调用一个包含所有必需参数的构造方法得到获得一个buildre对象



 */
public class NutritionFacts {

    public static void main(String[] args) {
        NutritionFacts coca = new Builder(240,8).calories(20).calories(100).sodium(44).build();
        System.out.println(coca.calories);
    }

    private final int servingSize;
    private final int servings;
    private final int calories;
    private final int fat;
    private final int sodium;
    private final int carbohydrate;

    public static class Builder{
        // Required parameters
        private final int servingSize;
        private final int servings;

        //Optional parameters -initialized to fefault values
        private int calories = 0;
        private int fat = 0;
        private int sodium = 0;
        private int carbohydrate = 0;

        public Builder(int servingSize,int servings){
            this.servingSize = servingSize;
            this.servings = servings;
        }

        public Builder calories(int val){
            calories = val;
            return this;
        }

        public Builder fat(int val){
            fat = val;
            return this;
        }

        public Builder sodium(int val){
            sodium = val;
            return this;
        }

        public Builder carbohydrate(int val){
            carbohydrate = val;
            return this;
        }

        public NutritionFacts build(){
            return new NutritionFacts(this);
        }

    }

    private NutritionFacts(Builder builder){
        servingSize = builder.servingSize;
        servings = builder.servings;
        calories = builder.calories;
        fat = builder.fat;
        sodium = builder.sodium;
        carbohydrate = builder.carbohydrate;
    }

}


































