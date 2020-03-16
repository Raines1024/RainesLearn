package com.raines.raineslearn.interesting.getClassName;

/**
 * Java获取当前类的类名
 * 获取到的类名的形式是:包名+类名
 */
public class GetClassName {

    public static void main(String[] args) {
        //一. 在静态方法里面当前类名
        String str = Thread.currentThread().getStackTrace()[1].getClassName();
        System.out.println(str);
        GetClassName getClassName = new GetClassName();
        System.out.println(getClassName.getClassName());
    }

    public String getClassName(){
        //二. 在非静态方法里面获取当前类名
        return this.getClass().getName();
    }

}
