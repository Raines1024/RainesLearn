package com.raines.raineslearn.threadpool.demo;

import com.raines.raineslearn.restAuthorized.Authorized;
import com.raines.raineslearn.threadpool.RejectedRunnable;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.concurrent.ThreadPoolExecutor;

@Slf4j
@RestController
public class Test {

    @Resource
    private ThreadPoolExecutor rejectThreadPool;

    @Authorized
    @RequestMapping("/threadDemo")
    public void demo() throws Exception {
        for (int i = 0;i < 50 ;i++){
            rejectThreadPool.execute(new ReceiveStrTask(i+""));
        }
        System.out.println(rejectThreadPool.getPoolSize());
        System.out.println("=====sdfsdfsd");
    }

    class ReceiveStrTask implements RejectedRunnable {

        private String string;

        public ReceiveStrTask(String string) {
            this.string = string;
        }

        @Override
        public void rejected() {
            log.error("任务队列已满，更新任务无法执行，字符串：{}", string);
        }

        @Override
        public void run() {
            try {
                Thread.sleep(100000l);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("okok-------------"+ string);
        }

    }

}
























































