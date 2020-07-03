package com.security.securityframework.sky.security.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @author sky
 * @date 2020/6/30 14:21
 */
@Data
@Component
@ConfigurationProperties(prefix = "wei.security")
//@PropertySource("classpath:application.yml")
public class SecurityConfig {
   /**
    * 是否需要登录
    */
   private Boolean enable = true;
}
