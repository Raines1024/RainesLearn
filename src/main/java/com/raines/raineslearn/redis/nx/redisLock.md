jedis的nx生成锁

- 如何删除锁
- 模拟抢单动作(10w个人开抢)
- jedis的nx生成锁

对于java中想操作redis，好的方式是使用jedis，首先pom中引入依赖：

    <dependency>
        <groupId>redis.clients</groupId>
        <artifactId>jedis</artifactId>
    </dependency>
    
对于分布式锁的生成通常需要注意如下几个方面：(com.raines.raineslearn.redis.nx.RedisLockUtils.setnx)  
- 创建锁的策略：  
redis的普通key一般都允许覆盖，A用户set某个key后，B在set相同的key时同样能成功，如果是锁场景，那就无法知道到底是哪个用户set成功的；这里jedis的setnx方式为我们解决了这个问题，简单原理是：当A用户先set成功了，那B用户set的时候就返回失败，满足了某个时间点只允许一个用户拿到锁。
- 锁过期时间：  
某个抢购场景时候，如果没有过期的概念，当A用户生成了锁，但是后面的流程被阻塞了一直无法释放锁，那其他用户此时获取锁就会一直失败，无法完成抢购的活动；当然正常情况一般都不会阻塞，A用户流程会正常释放锁；过期时间只是为了更有保障。

## 如何删除锁:(com.raines.raineslearn.redis.nx.RedisLockUtils.delnx)
   上面是创建锁，同样的具有有效时间，但是我们不能完全依赖这个有效时间，场景如：有效时间设置1分钟，本身用户A获取锁后，没遇到什么特殊情况正常生成了抢购订单后，此时其他用户应该能正常下单了才对，但是由于有个1分钟后锁才能自动释放，那其他用户在这1分钟无法正常下单（因为锁还是A用户的），因此我们需要A用户操作完后，主动去解锁：   
这里也使用了jedis方式，直接执行lua脚本：根据val判断其是否存在，如果存在就del；  
其实个人认为通过jedis的get方式获取val后，然后再比较value是否是当前持有锁的用户，如果是那最后再删除，效果其实相当；只不过直接通过eval执行脚本，这样避免多一次操作了redis而已，缩短了原子操作的间隔。同样这里创建个get方式的api来测试：  
注意的是delnx时，需要传递创建锁时的value，因为通过et的value与delnx的value来判断是否是持有锁的操作请求，只有value一样才允许del.

## 模拟抢单动作(10w个人开抢)
有了上面对分布式锁的粗略基础，我们模拟下10w人抢单的场景，其实就是一个并发操作请求而已，由于环境有限，只能如此测试；如下初始化10w个用户，并初始化库存，商品等信息.  
有了上面10w个不同用户，我们设定商品只有10个库存，然后通过并行流的方式来模拟抢购  
这里实现的逻辑是：  
parallelStream()：并行流模拟多用户抢购  
(startTime + timeout) >= System.currentTimeMillis()：判断未抢成功的用户，timeout秒内继续获取锁  
获取锁前和后都判断库存是否还足够  
jedisCom.setnx(shangpingKey, b)：用户获取抢购锁  
获取锁后并下单成功，最后释放锁：jedisCom.delnx(shangpingKey, b)  
