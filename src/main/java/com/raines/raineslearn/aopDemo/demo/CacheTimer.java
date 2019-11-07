package com.raines.raineslearn.aopDemo.demo;

import com.raines.raineslearn.aopDemo.CacheSupplier;
import com.raines.raineslearn.aopDemo.init.AppInitializer;
import com.raines.raineslearn.aopDemo.init.CacheIniter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author zhangrb
 * 缓存更新定时器
 */
@Component
public class CacheTimer implements CacheIniter {

    private static Logger log = LoggerFactory.getLogger(CacheTimer.class);

    public CacheTimer() {
        AppInitializer.registCacheIniter(this);
    }

    @Resource(name = "areaCacheSupplier")
    public CacheSupplier areaCacheSupplier;


    // 行政区域定时刷新缓存的任务，每天更新一次
    @Scheduled(cron = "0 0 0 * * ? ")
    public void areaJob() throws Exception {
        areaCacheSupplier.provide();
    }

    /**
     * 初始化话方法
     */
    public void init() {
        try {
            areaJob();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
