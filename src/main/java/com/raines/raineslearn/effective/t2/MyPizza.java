package com.raines.raineslearn.effective.t2;

import java.util.Objects;

/**
 * 尺寸参数
 */
public class MyPizza extends Pizza {

    public static void main(String[] args) {
        MyPizza pizza = new MyPizza.Builder(Size.SMALL).build();
    }

    public enum Size {SMALL,MEDIUM,LARGE}

    private final Size size;

    public static class Builder extends Pizza.Builder<Builder>{
        private final Size size;

        public Builder(Size size){
            this.size = Objects.requireNonNull(size);
        }

        /*
         每个子类builder中的build方法被声明为返回正确的子类
         这种技术，其一个子类的方法被声明为返回在超类中声明的返回类型的子类型，称为协变返回类型。
         */
        @Override
        public MyPizza build() {
            return new MyPizza(this);
        }

        @Override
        protected Builder self() {
            return this;
        }
    }

    MyPizza(Builder builder) {
        super(builder);
        size = builder.size;
    }
}




















