package com.security.securityframework.sky.jwt.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.security.securityframework.sky.jwt.bean.JwtBean;
import com.security.securityframework.sky.jwt.config.JwtConfig;
import com.security.securityframework.sky.jwt.exception.TokenCreateException;
import com.security.securityframework.sky.jwt.exception.TokenVerificationException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

/**
 * @author sky
 */
@Service
public class JwtUtil {
    @Resource
    private JwtConfig jwtConfig;

    /**
     * @param uid
     * @param date
     * @return
     * @throws TokenCreateException
     */
    public String getJwtToken(Long uid, LocalDateTime date) throws TokenCreateException {
        System.out.println("jwtConfig配置" + jwtConfig.toString());
        JwtBean jwtBean = new JwtBean(jwtConfig);

        try {
            Algorithm algorithm = Algorithm.HMAC256("secret");
            return JWT.create()
                    .withIssuer(jwtBean.getIss())
                    .withSubject(jwtBean.getSub())
                    .withAudience(jwtBean.getAud())
                    .withNotBefore(jwtBean.getNbf())
                    .withIssuedAt(jwtBean.getIat())
                    .withExpiresAt(date != null ? Date.from(date.atZone(ZoneId.systemDefault()).toInstant()) :
                            jwtBean.getExp())

                    //.withJWTId(jwtBean.getJti())
                    .withClaim("uid", uid)
                    .sign(algorithm);
        } catch (JWTCreationException e) {
            //Invalid Signing configuration / Couldn't convert Claims.
            e.printStackTrace();
            throw new TokenCreateException("Token create false");
        }
    }

    public Long isTrueToken(String token) throws TokenVerificationException {
        JwtBean jwtBean = new JwtBean(jwtConfig);

        Algorithm algorithm = Algorithm.HMAC256("secret");
        JWTVerifier verifier = JWT.require(algorithm)
                .withIssuer(jwtBean.getIss())
                .withSubject(jwtBean.getSub())
                .withAudience(jwtBean.getAud())
                //.withJWTId(jwtBean.getJti())
                .build(); //Reusable verifier instance
        try {
            DecodedJWT jwt = verifier.verify(token);
            System.out.println("过期时间" + jwt.getExpiresAt());

            Long uid = jwt.getClaim("uid").asLong();
            System.out.println("验证成功uid " + uid);
            return uid;
        } catch (JWTVerificationException jwt) {
            throw new TokenVerificationException("Token check false");
        }
    }

}
