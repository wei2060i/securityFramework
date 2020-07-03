package com.security.securityframework.sky.jwt.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @author sky
 */
@Data
@Component
@ConfigurationProperties(prefix = "wei.jwt")
//@PropertySource("classpath:application.yml")
public class JwtConfig {
    /**
     * iss (issuer)：签发人
     */
    private String iss;
    /**
     * sub (subject)：主题
     */
    private String sub;
    /**
     * aud (audience)：受众
     */
    private String aud;

    /**
     * 过期时间,秒
     */
    private Long expireDate;
    /**
     * 生效时间，秒
     */
    private Long startDate;
}
