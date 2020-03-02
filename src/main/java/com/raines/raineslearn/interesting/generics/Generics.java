package com.raines.raineslearn.interesting.generics;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 泛型擦除
 */
public class Generics {

    /**
     * 数组的类型不可以是类型变量，除非是采用通配符的方式，因为对于通配符的方式，最后取出数据是要做显式的类型转换的。
     */
    private static void genericsParam(){
        List<?>[] lsa = new List<?>[10]; // OK, array of unbounded wildcard type.
        Object o = lsa;
        Object[] oa = (Object[]) o;
        List<Integer> li = new ArrayList<Integer>();
        li.add(new Integer(3));
        oa[1] = li; // Correct.
        Integer i = (Integer) lsa[1].get(0); // OK
    }

    /**
     * 由于 Java 虚拟机泛型的擦除机制，在运行时虚拟机是不知道泛型信息的，所以可以给 oa[1] 赋上一个 ArrayList 而不会出现异常，
     * 但是在取出数据的时候却要做一次类型转换，所以就会出现 ClassCastException，
     * 如果可以进行泛型数组的声明，上面说的这种情况在编译期将不会出现任何的警告和错误，只有在运行时才会出错。
     */
    private static void demo(){
//        List<String>[] lsa = new List<String>[10]; // Not really allowed.
        List<String>[] lsa = new List[10];
        Object o = lsa;
        Object[] oa = (Object[]) o;
        List<Integer> li = new ArrayList<Integer>();
        li.add(new Integer(3));
        oa[1] = li; // Unsound, but passes run time store check
        String s = lsa[1].get(0); // Run-time error: ClassCastException.
    }

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        Map<String,Object> map = new HashMap<>();
        System.out.println(list.getClass());
        System.out.println(map.getClass());
        System.out.println(list.getClass() == map.getClass());
        genericsCompare();
    }

    private static void genericsCompare(){
        List<String> stringList = new ArrayList<String>();
        List<Integer> integerList = new ArrayList<Integer>();


        Class classStringArrayList = stringList.getClass();
        Class classIntegerArrayList = integerList.getClass();
        //获取泛型超类
        System.out.println(classStringArrayList.getGenericSuperclass());
        System.out.println(classIntegerArrayList);

        System.out.println(classStringArrayList.getClass() == classIntegerArrayList.getClass());
    }

}
