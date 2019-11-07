package com.raines.raineslearn.aopDemo.init;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * app启动后，系统初始化
 * @author zhangrb
 *
 */
@Component
public class AppInitializer implements CommandLineRunner {
	
	private static List<CacheIniter> cacheInitList = new ArrayList<>();
	
	public static void registCacheIniter(CacheIniter ci) {
		cacheInitList.add(ci);
	}

	public void run(String... arg0) throws Exception {
		// 初始化缓存
		for (CacheIniter ci : cacheInitList) {
			ci.init();
		}
	}
}
