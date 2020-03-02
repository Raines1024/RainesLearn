package com.raines.raineslearn.interesting.generics;

/**
 * 类型检查不可使用泛型
 * 可以通过下面的代码来解决泛型的类型信息由于擦除无法进行类型检查的问题。
 */
public class Test {

    Class<?> aClass;

    public Test(Class<?> aClass) {
        this.aClass = aClass;
    }

    public boolean isInstance(Object object) {
        return aClass.isInstance(object);
    }



    public static void main(String[] args) {
        Test test = new Test(A.class);
        System.out.println(test.isInstance(new A()));
        System.out.println(test.isInstance(new B()));
    }

    public static class A {
    }

    public static class B {
    }
}
/** print
 true
 false
 **/