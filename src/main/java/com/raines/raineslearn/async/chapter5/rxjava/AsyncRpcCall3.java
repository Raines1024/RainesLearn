package com.raines.raineslearn.async.chapter5.rxjava;

import io.reactivex.Flowable;
import io.reactivex.schedulers.Schedulers;

import java.util.ArrayList;
import java.util.List;

public class AsyncRpcCall3 {

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

	public static void main(String[] args) {

		// 1.生成ip列表
		List<String> ipList = new ArrayList<String>();
		for (int i = 1; i <= 10; ++i) {
			ipList.add("192.168.0." + i);
		}

		// 2.并发调用
		long start = System.currentTimeMillis();

		Flowable.fromArray( ipList.toArray(new String[0]))
			.parallel()
			.runOn(Schedulers.io())
			.map(v -> rpcCall(v, v))
			.sequential()
			.blockingSubscribe(System.out::println);//2.5

		//3.打印耗时
		System.out.println("cost:" + (System.currentTimeMillis() - start));

	}

}
