package com.raines.raineslearn.aopDemo.demo;

import com.raines.raineslearn.aopDemo.AbstractCacheSupplier;
import org.springframework.stereotype.Component;

/**
 * 行政区域缓存提供类
 *
 */
@Component
public class AreaCacheSupplier extends AbstractCacheSupplier {

    public final static String AREA = "area";

	public void provide() {
		putAreaMap();
	}
	
	/**
	 * 缓存area_map
	 * key 为area_map，value为一个map，map.key为区域id，map.value为区域对象
	 */
	private void putAreaMap() {
	    //放入缓存
	}

	public String getModel() {
		return AREA;
	}

}
