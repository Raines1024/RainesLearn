package com.raines.raineslearn;

import javax.servlet.Filter;

import com.raines.raineslearn.filterDemo.InitFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class RainesLearnApplication {

    @Bean
    public Filter initFilter() {
        return new InitFilter();
    }

    public static void main(String[] args) {
        SpringApplication.run(RainesLearnApplication.class, args);
    }

}
