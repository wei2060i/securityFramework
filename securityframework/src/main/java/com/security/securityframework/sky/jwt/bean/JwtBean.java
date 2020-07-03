package com.security.securityframework.sky.jwt.bean;

import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.security.securityframework.sky.jwt.config.JwtConfig;
import lombok.Data;
import lombok.ToString;

import java.util.Date;

/**
 * @author sky
 */
@Data
@ToString
public class JwtBean {
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
     * jti (JWT ID)：编号
     * JWT 的唯一身份标识，主要用来作为一次性 token，从而回避重放攻击
     */
    private String jti;
    /**
     * nbf (Not Before)：生效时间
     */
    private Date nbf;
    /**
     * iat (Issued At)：签发时间
     */
    private Date iat;
    /**
     * exp (expiration time)：过期时间
     */
    private Date exp;


    public JwtBean(JwtConfig jwtConfig) {
        this.iss = jwtConfig.getIss();
        this.sub = jwtConfig.getSub();
        this.aud = jwtConfig.getAud();
        this.iat = new Date();
        this.nbf = new Date(iat.getTime() + jwtConfig.getStartDate() * 1000);
        this.exp = new Date(iat.getTime() + jwtConfig.getExpireDate() * 1000);
        this.jti = IdWorker.get32UUID();
    }
}