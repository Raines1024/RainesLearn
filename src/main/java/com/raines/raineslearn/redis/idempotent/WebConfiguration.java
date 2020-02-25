package com.raines.raineslearn.redis.idempotent;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

/**
 * web配置类，实现WebMvcConfigurerAdapter（鉴于WebMvcConfigurerAdapter已过时，解决方案如下）
 * 主要作用就是添加autoIdempotentInterceptor到配置类中，这样我们到拦截器才能生效，注意使用@Configuration注解，这样在容器启动是时候就可以添加进入context中。
 */
@Configuration
//public class WebConfiguration extends WebMvcConfigurationSupport {
public class WebConfiguration implements WebMvcConfigurer {

    @Resource
    private AutoIdempotentInterceptor autoIdempotentInterceptor;

    /**
     * 添加拦截器
     *
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(autoIdempotentInterceptor);
//        super.addInterceptors(registry);
    }

    /**
     * Spring 5.0后，WebMvcConfigurerAdapter被废弃，取代的方法有两种：
     * ①implements WebMvcConfigurer（官方推荐）
     * ②extends WebMvcConfigurationSupport
     * 使用第一种方法是实现了一个接口，可以任意实现里面的方法，不会影响到Spring Boot自身的@EnableAutoConfiguration，而使用第二种方法相当于覆盖了@EnableAutoConfiguration里的所有方法，每个方法都需要重写，比如，若不实现方法addResourceHandlers()，则会导致静态资源无法访问，实现的方法如下：
     */
//    @Override
//    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("/**")
//                .addResourceLocations("classpath:/META-INF/resources/")
//                .addResourceLocations("classpath:/resources/")
//                .addResourceLocations("classpath:/static/")
//                .addResourceLocations("classpath:/public/");
//        super.addResourceHandlers(registry);
//    }

}
