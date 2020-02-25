package com.raines.raineslearn.redis.idempotent.cache;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 系统统一缓存
 *
 */
public interface DataCache {

	/**
     * 判断缓存中是否有对应的value
     *
     * @param key
     * @return
     */
	public boolean exists(String key);
	
	/**
     * 读取缓存
     *
     * @param key
     * @return
     */
    public <T> T get(String key);
    
    /**
     * 删除对应的value
     *
     * @param key
     */
    public boolean remove(String key);
    
    /**
     * 批量删除对应的value
     *
     * @param keys
     */
    public void remove(String... keys);
    
    /**
     * 批量删除key
     *
     * @param pattern
     */
    public void removePattern(String pattern);
    
    /**
     * 写入缓存
     *
     * @param key
     * @param value
     * @return
     */
    public boolean set(String key, Object value);

    /**
     * 存储map类型的数据
     * @param key redis   key
     * @param mkey map key
     * @param mvalue  map value
     * @return
     */
    public boolean setForMap(String key, Object mkey, Object mvalue);

    /**
     * 存储map类型的数据
     * @param key
     * @param map
     * @return
     */
    public boolean setForMap(String key, Map map);
    
    /**
     * 写入缓存
     *
     * @param key
     * @param value
     * @param expireTime 过期时间（秒
     * @return
     */
    public boolean set(String key, Object value, Long expireTime);
    
    /**
     * 从缓存的map对象中获取指定的key的值
     * @param redisKey 缓存存储的key
     * @param mapKey map中的key
     * @return
     */
    public <T> T getFormMap(String redisKey, String mapKey);
    
    /**
     * 从缓存的map对象中获取指定的keys的值
     * @param redisKey 缓存存储的key
     * @param mapKey map中的keys
     * @return 值的集合
     */
    public <T> List<T> getMultiFormMap(String redisKey, Collection<String> mapKeys);
    
    /**
     * 从redis的hash中查询整个map
     * @param redisKey 缓存key
     * @return 整个map
     */
    public <T> Map<String, T> getAllFormMap(String redisKey);
}
