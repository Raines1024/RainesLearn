package com.raines.raineslearn.redis;

import org.redisson.Redisson;
import org.redisson.api.RBloomFilter;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

/**
 * https://llimllib.github.io/bloomfilter-tutorial/zh_CN/
 *
 * Bloom filter 是一个数据结构，它可以用来判断某个元素是否在集合内，具有运行快速，内存占用小的特点。
 * 而高效插入和查询的代价就是，Bloom Filter 是一个基于概率的数据结构：它只能告诉我们一个元素绝对不在集合内或可能在集合内
 */
public class BloomExamples {

    public static void main(String[] args) {
        Config config = new Config();
        config.useSingleServer().setAddress("redis://172.16.100.4:6379").setDatabase(5);
        RedissonClient redisson = Redisson.create(config);

        RBloomFilter<String> bloomFilter = redisson.getBloomFilter("sample");
        // initialize Bloom filter with
        // expectedInsertions = 55000000。该expectedInsertions参数定义每个元素的预期插入次数。一个RBloomFilter对象最多可以包含2 ^ 32位。
        // falseProbability = 0.03
        bloomFilter.tryInit(55000000L, 0.03);
        bloomFilter.add("1");
        bloomFilter.add("2");
        System.out.println(bloomFilter.contains("1"));
        System.out.println(bloomFilter.count()+"-------------------");
    }

}
