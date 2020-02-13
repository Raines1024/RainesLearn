package com.raines.raineslearn.effective.t2;

import java.util.EnumSet;
import java.util.Objects;
import java.util.Set;

public abstract class Pizza {

    public enum Topping{HAM,MUSHROOM,ONION,PEPPER,SAUSAGE}

    final Set<Topping> toppings;

    //一个带有递归类型参数的泛型。这与抽象的self方法一起，允许方法链在子类中正常工作，而不需要强制转换。
    //java缺乏自我类型的这种变通解决方案称为模拟自我类型
    abstract static class Builder<T extends Builder<T>>{
        EnumSet<Topping> toppings = EnumSet.noneOf(Topping.class);
        public T addTopping(Topping topping){
            toppings.add(Objects.requireNonNull(topping));
            return self();
        }

        abstract Pizza build();

        protected abstract T self();
    }

    Pizza(Builder<?> builder){
        toppings = builder.toppings.clone();
    }

}
