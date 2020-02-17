package com.raines.raineslearn.grokkingAlgorithms.recursion_03;

/**
 * 调用栈
 * 这个栈用于存储多个函数的变量，被称为调用栈。
 *
 *
 */
public class Greet {

    private static void greet2(String name) {
        System.out.println("how are you, " + name + "?");
    }

    private static void bye() {
        System.out.println("ok bye!");
    }

    /**
     * 假设你调用greet("maggie")，计算机将首先为该函数调用分配一块内存。
     * 我们来使用这些内存。变量name被设置为maggie，这需要存储到内存中。
     * 每当你调用函数时，计算机都像这样将函数调用涉及的所有变量的值存储到内存中
     *
     * @param name
     */
    private static void greet(String name) {
        System.out.println("hello, " + name + "!");
        //你打印hello, maggie!，再调用greet2("maggie")。同样，计算机也为这个函数调用分配一块内存。
        //计算机使用一个栈来表示这些内存块，其中第二个内存块位于第一个内存块上面。
        //调用另一个函数时，当前函数暂停并处于未完成状态。该函数的所有变量的值都还在内存中。
        greet2(name);
        //执行完greet2，回到函数greet，并从离开的地方开始接着往下执行
        System.out.println("getting ready to say bye...");
        bye();
    }

    public static void main(String[] args) {
        greet("adit");
    }
}