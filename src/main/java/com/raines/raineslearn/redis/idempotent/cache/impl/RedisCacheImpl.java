package com.raines.raineslearn.redis.idempotent.cache.impl;

import com.raines.raineslearn.redis.idempotent.cache.DataCache;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * redis 缓存实现类
 * 
 *
 */
@Component
@SuppressWarnings({ "rawtypes", "unchecked" })
public class RedisCacheImpl implements DataCache {

	private static RedisTemplate redisTemplate;

	@Resource
	public void setRedisTemplate(RedisTemplate redisTemplate) {
		RedisCacheImpl.redisTemplate = redisTemplate;
	}

	/**
	 * 批量删除对应的value
	 *
	 * @param keys
	 */
	public void remove(String... keys) {
		for (String key : keys) {
			remove(key);
		}
	}

	/**
	 * 批量删除key
	 *
	 * @param pattern
	 */
	public void removePattern(String pattern) {
		Set<Serializable> keys = redisTemplate.keys(pattern);
		if (keys.size() > 0)
			redisTemplate.delete(keys);
	}

	/**
	 * 删除对应的value
	 *
	 * @param key
	 */
	public boolean remove(String key) {
		if (exists(key)) {
			return redisTemplate.delete(key);
		}
		return false;
	}

	/**
	 * 判断缓存中是否有对应的value
	 *
	 * @param key
	 * @return
	 */
	public boolean exists(String key) {
		return redisTemplate.hasKey(key);
	}

	/**
	 * 读取缓存
	 *
	 * @param key
	 * @return
	 */
	public <T> T get(String key) {
		Object result = null;
		ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
		result = operations.get(key);
		return (T)result;
	}

	/**
	 * 写入缓存
	 *
	 * @param key
	 * @param value
	 * @return
	 */
	public boolean set(String key, Object value) {
		boolean result = false;
		try {
			ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
			operations.set(key, value);
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 存储map类型的数据
	 * @param key
	 * @param map
	 * @return
	 */
	public boolean setForMap(String key, Map map) {
		try {
			redisTemplate.opsForHash().putAll(key, map);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * 存储map类型的数据
	 * @param key redis   key
	 * @param mkey map key
	 * @param mvalue  map value
	 * @return
	 */
	public boolean setForMap(String key, Object mkey, Object mvalue) {
		try {
			redisTemplate.opsForHash().put(key, mkey, mvalue);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * 写入缓存
	 *
	 * @param key
	 * @param value
	 * @return
	 */
	public boolean set(String key, Object value, Long expireTime) {
		boolean result = false;
		try {
			ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
			operations.set(key, value);
			redisTemplate.expire(key, expireTime, TimeUnit.SECONDS);
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public <T> T getFormMap(String redisKey, String mapKey) {
		return (T)redisTemplate.opsForHash().get(redisKey, mapKey);
	}
	
	public <T> List<T> getMultiFormMap(String redisKey, Collection<String> mapKeys) {
		return redisTemplate.opsForHash().multiGet(redisKey, mapKeys);
	}

	public <T> Map<String, T> getAllFormMap(String redisKey) {
		return redisTemplate.opsForHash().entries(redisKey);
	}
}
