package com.raines.raineslearn.async.chapter2.thread;

/**
 * Java中有两种方式来显式开启一个线程进行异步处理。
 * 第一种方式是实现java.lang.Runnable接口的run方法，然后传递Runnable接口的实现类作为创建Thread时的参数，启动线程。
 *
 */
public class AsyncThreadExample {

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

	/**
	 * 准备：
	 * 在Java中，Java虚拟机允许应用程序同时运行多个执行线程，
	 * 所以我们可在main函数内开启一个线程来异步执行任务doSomethingA，而main函数所在线程执行doSomethingB，即可大大缩短整个任务处理耗时。
	 *
	 * 事实：
	 * 我们在main函数所在线程内首先使用lambda表达式创建了一个java.lang.Runnable接口的匿名实现类，用来异步执行doSomethingA任务，然后将其作为Thread的参数并启动。
	 * 这时候线程A与main线程并发运行，也就是任务doSomethingA与任务doSomethingB并发运行，
	 * 代码3则等main线程运行完doSomethingB任务后同步等待线程A运行完毕。
	 *
	 * @param args
	 * @throws InterruptedException
	 */
	public static void main(String[] args) throws InterruptedException {

		long start = System.currentTimeMillis();

		// 1.开启异步单元执行任务A
		Thread thread = new Thread(() -> {
			try {
				doSomethingA();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}, "threadA");
		thread.start();

		// 2.执行任务B
		doSomethingB();

		// 3.同步等待线程A运行结束
		thread.join();
		System.out.println(System.currentTimeMillis() - start);
	}
}
