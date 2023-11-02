package com.user;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * ClassName: UserApplication
 * Description:
 * {@code @Author} 苏羽晨
 * {@code @Create} 2023/11/2 10:59
 * {@code @Version}  1.0
 */

@SpringBootApplication
@EnableDiscoveryClient
@MapperScan("com.user.mapper")
public class UserApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserApplication.class,args);
    }
}
