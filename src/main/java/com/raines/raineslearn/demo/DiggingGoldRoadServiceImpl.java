package com.raines.raineslearn.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadPoolExecutor;

@RestController
public class DiggingGoldRoadServiceImpl {
    @Autowired
    ThreadPoolExecutor processThreadPool;
    // 两点间距离的极限，超过则证明经纬度有偏移, 默认以80公里每小时计算，车辆每五秒上报一次数据
    public static Double maxMeterBetweenPoints = 110.0;



    @GetMapping("/DiggingGoldRoadServiceImplDemo")
    public void demo() {
        CountDownLatch latch = new CountDownLatch(100);
        for (int i = 1; i <= 100; i++) {
        	System.out.println(i);
			SaveEveryDayMileage saveEveryDayMileage = new SaveEveryDayMileage(i);
            processThreadPool.execute(saveEveryDayMileage);
        }
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }
}
