package com.raines.raineslearn.aopDemo.aop;

import com.raines.raineslearn.aopDemo.CacheSupplier;
import com.raines.raineslearn.aopDemo.annotation.CacheRefresh;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

@Aspect
@Component
public class CacheListener {


	private static Map<String, CacheSupplier> supplierMap = new HashMap<>();
	
	public static void registSupplier(CacheSupplier cs) {
		supplierMap.put(cs.getModel(), cs);
	}

	/**
	 * 定义Pointcut，Pointcut的名称，此方法不能有返回值，该方法只是一个标示
	 */
	@Pointcut("@annotation(com.raines.raineslearn.aopDemo.annotation.CacheRefresh)")
	public void cacheAspect() {
		System.out.println("我是一个切入点");
	}

	/**
	 * 前置通知（Before advice） ：在某连接点（JoinPoint）之前执行的通知，但这个通知不能阻止连接点前的执行。
	 * 
	 * @param joinPoint
	 */
	@Before("cacheAspect()")
	public void doBefore(JoinPoint joinPoint) {
		// donothing
	}

	/**
	 * 后通知（After advice） ：当某连接点退出的时候执行的通知（不论是正常返回还是异常退出）。
	 * 
	 * @param joinPoint
	 */
	@AfterReturning(pointcut = "cacheAspect()")
	public void doAfter(JoinPoint joinPoint) {
		refreshCache(joinPoint);
	}

	/**
	 * 抛出异常后通知（After throwing advice） ： 在方法抛出异常退出时执行的通知。
	 * 
	 * @param joinPoint
	 * @param e
	 */
	@AfterThrowing(value = "cacheAspect()", throwing = "e")
	public void doAfter(JoinPoint joinPoint, Exception e) {
		// donothing
	}
	
	/**
	 * 环绕通知（Around advice）
	 * ：包围一个连接点的通知，类似Web中Servlet规范中的Filter的doFilter方法。可以在方法的调用前后完成自定义的行为，也可以选择不执行。
	 * 
	 * @param joinPoint
	 */
	@Around("cacheAspect()")
	public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
		// donothing
		Object obj = joinPoint.proceed();
		return obj;
	}
	
	/**
	 * 刷新缓存
	 * @param joinPoint
	 */
	private void refreshCache(JoinPoint joinPoint) {
		new Thread() {
			public void run() {
				Signature signature = joinPoint.getSignature();
				MethodSignature methodSignature = (MethodSignature) signature;
				Method method = methodSignature.getMethod();
				CacheRefresh cr = method.getAnnotation(CacheRefresh.class);

				String model = cr.modelName();
				if(!StringUtils.isEmpty(model)) {
					String[] arr = model.split(",");
					for (String a : arr) {
						// 寻找匹配的supplier
						CacheSupplier cs = supplierMap.get(a);
						// 执行缓存逻辑
						cs.provide();
					}
				}
			}
		}.start();
	}

}
