package com.security.securityframework.sky.security.aop;

import com.security.securityframework.sky.jwt.exception.TokenVerificationException;
import com.security.securityframework.sky.security.config.SecurityConfig;
import com.security.securityframework.sky.service.ISkySecurity;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author sky
 * @date 2020/6/29 10:44
 */
@Aspect
@Order(1)
@Component
public class LoginSecurity {

    @Resource
    private ISkySecurity skySecurity;
    @Resource
    private SecurityConfig securityConfig;


    @Around("@within(org.springframework.web.bind.annotation.RestController) && " +
            "!@annotation(com.security.securityframework.sky.security.annotation.SkyPassLogin)")
    public Object loginAuth(ProceedingJoinPoint pjp) {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
        HttpServletResponse response = ((ServletRequestAttributes) requestAttributes).getResponse();

        try {

            if (!securityConfig.getEnable()) {
                return pjp.proceed();
            }

            Long aLong = skySecurity.doSkySecurityCheckUserId(request);
            if (aLong != null) {
                //释放,让目标方法执行
                return pjp.proceed();
            }
        } catch (TokenVerificationException e) {
            e.printStackTrace();
            return skySecurity.doSkySecurityNoLoginProcessed(response);
        } catch (Throwable e) {
            e.printStackTrace();
            return skySecurity.doSkySecuritySystemErrorProcessed(e, response);
        }
        return skySecurity.doSkySecurityNoLoginProcessed(response);
    }
}
