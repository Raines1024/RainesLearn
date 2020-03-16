package com.raines.raineslearn.async.chapter2.thread;

/**
 * 同步编程模型下，在一个线程中要做两件事情的代码
 * 执行过程：
 * 启动一个Java虚拟机进程，进程内会创建一个用户线程来执行main函数（main线程），
 * main线程内首先执行了doSomethingA方法，然后执行了doSomethingB方法，
 * 那么整个过程耗时4s左右，这是因为两个方法是顺序执行的。
 *
 */
public class SyncExample {

	public static void doSomethingA() {

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("--- doSomethingA---");
	}

	public static void doSomethingB() {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("--- doSomethingB---");

	}

	public static void main(String[] args) {

		long start = System.currentTimeMillis();
		// 1.执行任务A
		doSomethingA();

		// 2.执行任务B
		doSomethingB();

		System.out.println(System.currentTimeMillis() - start);

	}
}