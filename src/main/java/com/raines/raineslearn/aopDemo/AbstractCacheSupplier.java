package com.raines.raineslearn.aopDemo;


import com.raines.raineslearn.aopDemo.aop.CacheListener;

public abstract class AbstractCacheSupplier implements CacheSupplier {

	protected AbstractCacheSupplier() {
		CacheListener.registSupplier(this);
	}
}
