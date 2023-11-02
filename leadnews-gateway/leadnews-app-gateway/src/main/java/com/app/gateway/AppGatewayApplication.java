package com.app.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * ClassName: AppGatewayApplication
 * Description:
 * {@code @Author} 苏羽晨
 * {@code @Create} 2023/11/2 12:34
 * {@code @Version}  1.0
 */
@SpringBootApplication
@EnableDiscoveryClient
public class AppGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(AppGatewayApplication.class,args);
    }
}
