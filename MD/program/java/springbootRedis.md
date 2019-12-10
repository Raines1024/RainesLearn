一、引入依赖：

        <!-- redis -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-redis</artifactId>
        </dependency>
        
二、yml配置
> spring:
  redis:
    database: 1
    host: 172.29.32.53
    port: 6379
    password:
  
三、代码封装
1.

/**
 * 系统统一缓存接口
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
    public void remove(String key);
    
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
     * @param expireTime 过期时间
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

2.

/**
 * redis 缓存实现类
 * 
 * @author zhangrb
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
	public void remove(String key) {
		if (exists(key)) {
			redisTemplate.delete(key);
		}
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

3.单例工具类

@Component
public class CacheUtils {
    
    private static DataCache dataCache;
    
    @Resource
    public void setDataCache(DataCache dataCache) {
    	CacheUtils.dataCache = dataCache;
    }

    public static DataCache getCacheInstance() {
    	return dataCache;
    }
}

4.缓存配置类


@Configuration
@EnableCaching // 启用缓存，这个注解很重要；
public class RedisCacheConfig extends CachingConfigurerSupport {

    /**
     * RedisTemplate配置
     * @param factory
     * @return
     */
    @Bean
    public RedisTemplate<String, String> redisTemplate(RedisConnectionFactory factory) {
        StringRedisTemplate template = new StringRedisTemplate(factory);
        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jackson2JsonRedisSerializer.setObjectMapper(om);
        template.setValueSerializer(jackson2JsonRedisSerializer);//如果key是String 需要配置一下StringSerializer,不然key会乱码 /XX/XX
        template.setHashValueSerializer(jackson2JsonRedisSerializer);
        template.afterPropertiesSet();
        return template;
    }

}

5.使用例子
存
>CacheUtils.getCacheInstance().set("demo2","passsword",1l);

取
>CacheUtils.getCacheInstance().get("username");


