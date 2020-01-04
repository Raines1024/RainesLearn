package com.raines.raineslearn;

import com.raines.raineslearn.filterDemo.InitFilter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.servlet.Filter;
import java.util.concurrent.ThreadPoolExecutor;

@Slf4j
@SpringBootApplication
public class RainesLearnApplication {

    @Autowired
    private static ThreadPoolExecutor rejectThreadPool;

    @Bean
    public Filter initFilter() {
        return new InitFilter();
    }

    public static void main(String[] args) {
        SpringApplication.run(RainesLearnApplication.class, args);

        //添加钩子函数
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            rejectThreadPool.shutdown();
        }));

        log.info("Application is success!");
    }

}
