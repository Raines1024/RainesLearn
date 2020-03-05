package com.raines.raineslearn.demo;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CountDownLatch;



public class SaveEveryDayMileage implements Runnable {
	CountDownLatch latch;
	int i;
	private static final SaveEveryDayMileage INSTANCE = new SaveEveryDayMileage();

	private SaveEveryDayMileage(){}

	public SaveEveryDayMileage(int i) {
		System.out.println(i+"))))))))))))))");
		INSTANCE.i = i;
	}

	public SaveEveryDayMileage(int i,CountDownLatch latch) {
		INSTANCE.i = i;
		INSTANCE.latch = latch;
	}


	@Override
	public void run() {
		System.out.println(i+"=======");
		try {
			save(i);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
//		latch.countDown();//一个线程完成工作，计数器减一
	}
	public synchronized void save(int i) throws InterruptedException {
		System.out.println("线程"+i+"开始调用");
		Thread.sleep(1000l);
		System.out.println("线程"+i+"调用结束");
	}

}
