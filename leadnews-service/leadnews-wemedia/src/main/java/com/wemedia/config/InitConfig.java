package com.wemedia.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * ClassName: InitConfig
 * Description: 扫描远程调用降级代码类
 * {@code @Author} 苏羽晨
 * {@code @Create} 2023/11/8 14:51
 * {@code @Version}  1.0
 */
@Configuration
@ComponentScan("com.apis.article.fallback")
public class InitConfig {
}
