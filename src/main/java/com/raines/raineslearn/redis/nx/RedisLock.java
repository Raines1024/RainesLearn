package com.raines.raineslearn.redis.nx;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * Spring Boot+Redis 分布式锁：模拟抢单
 */
@RestController
public class RedisLock {
    public static final Logger logger = LoggerFactory.getLogger(RedisLock.class);
    //总库存
    private long nKuCuen;
    //商品key名字
    private String shangpingKey = "computer_key";
    //获取锁的超时时间 秒
    private int timeout = 30 * 1000;
    static RedisLockUtils jedisCom = new RedisLockUtils();

    @GetMapping("/qiangdan")
    public List<String> qiangdan() {
        System.out.println("======------+++++++++");
        //抢到商品的用户
        List<String> shopUsers = new ArrayList<>();

        //构造很多用户
        List<String> users = new ArrayList<>();
        IntStream.range(0, 100000)/*.parallel()*/.forEach(b -> {
//        IntStream.range(0, 100000).forEach(b -> {
            users.add("神牛-" + b);
        });
        System.out.println("=========--" + users.size());
        //初始化库存
        nKuCuen = 10;

        //模拟开抢(parallelStream()：并行流模拟多用户抢购)
        users.parallelStream().forEach(b -> {
            String shopUser = qiang(b);
            if (!StringUtils.isEmpty(shopUser)) {
                shopUsers.add(shopUser);
            }
        });

        return shopUsers;
    }

    /**
     * 模拟抢单动作
     *
     * @param b
     * @return
     */
    private String qiang(String b) {
        //用户开抢时间
        long startTime = System.currentTimeMillis();

        //未抢到的情况下，30秒内继续获取锁
        while ((startTime + timeout) >= System.currentTimeMillis()) {
            //获取锁前和后都判断库存是否还足够
            //商品是否剩余
            if (nKuCuen <= 0) {
                break;
            }
            if (jedisCom.setnx(shangpingKey, b)) {
                //用户b拿到锁
                logger.info("用户{}拿到锁...", b);
                try {
                    //商品是否剩余
                    if (nKuCuen <= 0) {
                        break;
                    }

                    //模拟生成订单耗时操作，方便查看：神牛-50 多次获取锁记录
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    //抢购成功，商品递减，记录用户
                    nKuCuen -= 1;

                    //抢单成功跳出
                    logger.info("用户{}抢单成功跳出...所剩库存：{}", b, nKuCuen);

                    return b + "抢单成功，所剩库存：" + nKuCuen;
                } finally {
                    logger.info("用户{}释放锁...", b);
                    //释放锁
                    jedisCom.delnx(shangpingKey, b);
                }
            } else {
                //用户b没拿到锁，在超时范围内继续请求锁，不需要处理
                // logger.info("用户{}等待获取锁...", b);
            }
        }
        return "";
    }


    @GetMapping("/setnx/{key}/{val}")
    public boolean setnx(@PathVariable String key, @PathVariable String val) {
        return jedisCom.setnx(key, val);
    }

    @GetMapping("/delnx/{key}/{val}")
    public int delnx(@PathVariable String key, @PathVariable String val) {
        return jedisCom.delnx(key, val);
    }


}
