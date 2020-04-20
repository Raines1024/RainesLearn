package com.raines.raineslearn.async.chapter2.threadPool;

import java.util.concurrent.*;

/**
 * 向线程池投递一个Callable类型的异步任务，并且获取其执行结果
 */
public class AsyncThreadPoolExample3 {

    public static String doSomethingA() {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
        return "A Task Done";
    }

    // 0自定义线程池
    //获取当前物理机的CPU核数
    private final static int AVALIABLE_PROCESSORS = Runtime.getRuntime().availableProcessors();
    //设置线程池核心线程个数为当前物理机的CPU核数，最大线程个数为当前物理机CPU核数的2倍；设置线程池阻塞队列的大小为5；
    //需要注意的是，我们将线程池的拒绝策略设置为CallerRunsPolicy，即当线程池任务饱和，执行拒绝策略时不会丢弃新的任务，而是会使用调用线程来执行；
    // 另外我们使用了命名的线程创建工厂，以便排查问题时可以方便追溯是哪个相关业务。
    private final static ThreadPoolExecutor POOL_EXECUTOR = new ThreadPoolExecutor(AVALIABLE_PROCESSORS,
            AVALIABLE_PROCESSORS * 2, 1, TimeUnit.MINUTES, new LinkedBlockingQueue<>(5),
            new NamedThreadFactory("ASYNC-POOL"), new ThreadPoolExecutor.CallerRunsPolicy());

    public static void main(String[] args) throws InterruptedException, ExecutionException {
		// 1.开启异步单元执行任务A
		//使用lambda表达式将Callable类型的任务提交到线程池，提交后会马上返回一个Future对象
        Future<?> resultA = POOL_EXECUTOR.submit(() -> doSomethingA());
        // 2.同步等待执行结果
		//调用get()方法阻塞等待异步任务的执行结果
        System.out.println(resultA.get());
    }

}