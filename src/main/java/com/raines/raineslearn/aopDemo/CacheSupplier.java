package com.raines.raineslearn.aopDemo;

/**
 * 缓存提供器，各模块的缓存需要实现此接口
 * @author zhangrb
 *
 */
public interface CacheSupplier {
	
	/**
	 * 缓存逻辑
	 */
	public void provide();
	
	/**
	 * 对应的模块
	 * @return
	 */
	public String getModel();
	
}
