package com.raines.raineslearn.async.chapter5.rxjava;

import io.reactivex.Flowable;
import io.reactivex.schedulers.Schedulers;

import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

public class AsyncRpcCall {

	public static String rpcCall(String ip, String param) {

		System.out.println(ip + " rpcCall:" + param);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return param;

	}

	public static void main(String[] args) throws InterruptedException {

		CompletableFuture<String> future = CompletableFuture.supplyAsync(new Supplier<String>() {

			@Override
			public String get() {
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return "test";
			}
		});

		Flowable.fromCallable(()->future.get()).subscribeOn(Schedulers.io()).subscribe(System.out::println);
		System.out.println(111);
		
		Thread.sleep(3000);
	}

}
