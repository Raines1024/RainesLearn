package com.raines.raineslearn.async.chapter3.CompletableFuture;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * 1.基于allOf等待多个并发运行的CompletableFuture任务执行完毕
 * 2.基于anyOf等多个并发运行的CompletableFuture任务中有一个执行完毕就返回
 */
public class TestMoreCompletableFuture {

	// 1.异步任务，返回future
	public static CompletableFuture<String> doSomethingOne(String id) {
		// 1.1创建异步任务
		return CompletableFuture.supplyAsync(new Supplier<String>() {

			@Override
			public String get() {

				// 1.1.1休眠1s，模拟任务计算
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("compute " + id);

				return id+"after";
			}
		});
	}

	// 2.开启异步任务，返回future
	public static CompletableFuture<String> doSomethingTwo(String id) {
		return CompletableFuture.supplyAsync(new Supplier<String>() {

			@Override
			public String get() {

				// 2.1,休眠3s，模拟计算
				try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("compute " + id);

				return id;
			}
		});
	}

	/**
	 * 基于allOf等待多个并发运行的CompletableFuture任务执行完毕
	 */
	public static void allOf() throws InterruptedException, ExecutionException {
		// 1.创建future列表
		List<CompletableFuture<String>> futureList = new ArrayList<>();
		futureList.add(doSomethingOne("1"));
		futureList.add(doSomethingOne("2"));
		futureList.add(doSomethingOne("3"));
		futureList.add(doSomethingOne("4"));

		// 2.转换多个future为一个
		CompletableFuture<Void> result = CompletableFuture
				.allOf(futureList.toArray(new CompletableFuture[futureList.size()]));

		// 3.等待所有future都完成
		System.out.println(result.get());

		// 4.等所有future执行完毕后，获取所有future的计算结果
		CompletableFuture<List<String>> finallyResult = result.thenApply(new Function<Void, List<String>>() {

			@Override
			public List<String> apply(Void t) {
				return futureList.stream().map(future -> future.join()).collect(Collectors.toList());
			}
		});

		// 5.打印所有future的结果
		for (String str : finallyResult.get()) {
			System.out.println(str);
		}
	}

	/**
	 * 基于anyOf等多个并发运行的CompletableFuture任务中有一个执行完毕就返回
	 */
	public static void anyOf() throws InterruptedException, ExecutionException {
		// 1.创建future列表
		List<CompletableFuture<String>> futureList = new ArrayList<>();
		futureList.add(doSomethingOne("1"));
		futureList.add(doSomethingOne("2"));
		futureList.add(doSomethingTwo("3"));

		// 2.转换多个future为一个
		CompletableFuture<Object> result = CompletableFuture
				.anyOf(futureList.toArray(new CompletableFuture[futureList.size()]));

		// 3.等待某一个future完成
		System.out.println(result.get());

	}

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		// 1.allOf
		allOf();

		// 2.anyOf
//		 anyOf();

		Thread.sleep(10000);
	}

}
