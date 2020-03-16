package com.raines.raineslearn.async.chapter2.threadPool;

import java.util.concurrent.*;

/**
 * 向线程池投递一个Callable类型的异步任务，并且获取其执行结果
 */
public class AsyncThreadPoolExample3 {

    public static String doSomethingA() {
        System.out.println("--- doSomethingA---");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
        return "A Task Done";
    }

    // 0自定义线程池
    private final static int AVALIABLE_PROCESSORS = Runtime.getRuntime().availableProcessors();
    private final static ThreadPoolExecutor POOL_EXECUTOR = new ThreadPoolExecutor(AVALIABLE_PROCESSORS,
            AVALIABLE_PROCESSORS * 2, 1, TimeUnit.MINUTES, new LinkedBlockingQueue<>(5),
            new NamedThreadFactory("ASYNC-POOL"), new ThreadPoolExecutor.CallerRunsPolicy());

    public static void main(String[] args) throws InterruptedException, ExecutionException {
		int n = 0x1f;
		int m = n>>1;//m = -18
		int k = n>>1; //? 不符合数学规律
		k = 1<<-1;//
//		k = -1;
		System.out.println(Integer.toBinaryString(n));
		System.out.println(n);
		System.out.println(Integer.toBinaryString(m));
		System.out.println(k);
		// 1.开启异步单元执行任务A
		//使用lambda表达式将Callable类型的任务提交到线程池，提交后会马上返回一个Future对象
        Future<?> resultA = POOL_EXECUTOR.submit(() -> doSomethingA());

        // 2.同步等待执行结果
		//调用get()方法阻塞等待异步任务的执行结果
        System.out.println(resultA.get());
    }
}