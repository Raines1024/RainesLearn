package com.raines.raineslearn.async.chapter3.CompletableFuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.function.Supplier;

/**
 * 1. 基于thenCompose实现当一个CompletableFuture执行完毕后，可以第一个Completable的结果作为参数执行另外一个CompletableFuture
 * 2. 基于thenCombine实现当两个并发运行的CompletableFuture任务都完成后，使用两者的结果作为参数再执行一个异步任务
 *
 */
public class TestTwoCompletableFuture {

    // 1.异步任务，返回future
    public static CompletableFuture<String> doSomethingOne(String encodedCompanyId) {
        // 1.1创建异步任务
        return CompletableFuture.supplyAsync(new Supplier<String>() {

            @Override
            public String get() {
                // 1.1.1休眠1s，模拟任务计算
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

                // 1.1.2 解密，并返回结果
                String id = encodedCompanyId+"";
                return id;
            }
        });
    }

    // 2.开启异步任务，返回future
    public static CompletableFuture<String> doSomethingTwo(String companyId) {
        return CompletableFuture.supplyAsync(new Supplier<String>() {

            @Override
            public String get() {

                // 2.1,休眠3s，模拟计算
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

                // 2.2 查询公司信息，转换为str，并返回
                String str = companyId + ":alibaba";
                return str;
            }
        });
    }

    public static void main(String[] args) throws InterruptedException, ExecutionException {

        // I，等doSomethingOne执行完毕后，接着执行doSomethingTwo
		//首先调用方法doSomethingOne("123")开启了一个异步任务，并返回了对应的CompletableFuture对象，我们取名为future1，
		//然后在future1的基础上调用了thenCompose方法，企图让future1执行完毕后，激活使用其结果作为doSomethingTwo(String companyId)方法的参数的任务。
        CompletableFuture result = doSomethingOne("123").thenCompose(id -> doSomethingTwo(id));
        System.out.println(result.get());

        // II,等doSomethingOne和doSomethingTwo都完成后，使用它们的结果做一件事
        result = doSomethingOne("123").thenCombine(doSomethingTwo("456"), (one, two) -> {
            return one + " " + two;
        });
        System.out.println(result.get());

    }

}
