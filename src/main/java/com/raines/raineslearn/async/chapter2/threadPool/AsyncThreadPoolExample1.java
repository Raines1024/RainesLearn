package com.raines.raineslearn.async.chapter2.threadPool;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Hello world!
 *
 */
public class AsyncThreadPoolExample1 {

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

	// 0自定义线程池
	//获取当前物理机的CPU核数
	private final static int AVALIABLE_PROCESSORS = Runtime.getRuntime().availableProcessors();
	//设置线程池核心线程个数为当前物理机的CPU核数，最大线程个数为当前物理机CPU核数的2倍；设置线程池阻塞队列的大小为5；
	//需要注意的是，我们将线程池的拒绝策略设置为CallerRunsPolicy，即当线程池任务饱和，执行拒绝策略时不会丢弃新的任务，而是会使用调用线程来执行；
	// 另外我们使用了命名的线程创建工厂，以便排查问题时可以方便追溯是哪个相关业务。
	private final static ThreadPoolExecutor POOL_EXECUTOR = new ThreadPoolExecutor(AVALIABLE_PROCESSORS,
			AVALIABLE_PROCESSORS * 2, 1, TimeUnit.MINUTES, new LinkedBlockingQueue<>(5),
			new ThreadPoolExecutor.CallerRunsPolicy());

	public static void main(String[] args) throws InterruptedException, ExecutionException {

		long start = System.currentTimeMillis();

		// 1.开启异步单元执行任务A
		//把异步任务提交到了线程池内运行，而不是直接开启一个新线程来运行；这里使用线程池起到了复用线程的作用，避免了线程的频繁创建与销毁，另外对线程个数也起到了限制作用。
		POOL_EXECUTOR.execute(() -> {
			try {
				doSomethingA();
			} catch (Exception e) {
				e.printStackTrace();
			}
		});

		// 2.执行任务B
		doSomethingB();

		// 3.同步等待线程A运行结束
		System.out.println(System.currentTimeMillis() - start);

		// 4.挂起当前线程
		Thread.currentThread().join();
	}
}
