package com.sww.springcloud.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author Aeiherumuh
 * @date 2020/10/14
 */
@Configuration
public class ApplicationContextConfig {

    /**
     * //applicationContext.xml <bean id="" class="">
     * //使用 @LoadBalanced 注解赋予 RestTemplate负载均衡的能力
     * @return
     */
    @Bean
    @LoadBalanced
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }
}
