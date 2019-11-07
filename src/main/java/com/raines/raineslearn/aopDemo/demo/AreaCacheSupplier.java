package com.raines.raineslearn.aopDemo.demo;

import com.raines.raineslearn.aopDemo.AbstractCacheSupplier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 行政区域缓存提供类
 * @author zhangrb
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
