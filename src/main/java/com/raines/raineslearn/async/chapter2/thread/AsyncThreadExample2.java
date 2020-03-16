package com.raines.raineslearn.async.chapter2.thread;

/**
 * Java中第二种开启线程进行异步执行的方式是实现Thread类，并重写run方法
 *
 */
public class AsyncThreadExample2 {

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
	 * 创建了Thread的匿名类的实现，并重写了run方法，然后启动了线程执行。
	 */
	public static void main(String[] args) throws InterruptedException {
		long start = System.currentTimeMillis();
		// 1.开启异步单元执行任务A
		Thread thread = new Thread("threadA") {
			@Override
			public void run() {
				try {
					doSomethingA();

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		};
		
		//thread.setDaemon(true);
		thread.start();

		// 2.执行任务B
		doSomethingB();

		// 3.同步等待线程A运行结束
		thread.join();
		System.out.println(System.currentTimeMillis() - start);
	}
}
