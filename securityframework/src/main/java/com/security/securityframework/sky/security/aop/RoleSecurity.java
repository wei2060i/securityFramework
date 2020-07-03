package com.security.securityframework.sky.security.aop;

import com.security.securityframework.sky.jwt.exception.TokenVerificationException;
import com.security.securityframework.sky.jwt.service.JwtUtil;
import com.security.securityframework.sky.security.annotation.SkyRoleSecurity;
import com.security.securityframework.sky.service.ISkySecurity;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author sky
 * @date 2020/6/29 11:15
 */
@Aspect
@Order(2)
@Component
public class RoleSecurity {

    @Resource
    private ISkySecurity skySecurity;

    @Around("@annotation(com.security.securityframework.sky.security.annotation.SkyRoleSecurity)")
    public Object roleAuth(ProceedingJoinPoint pjp) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();

        try {
            Long uid = skySecurity.doSkySecurityCheckUserId(request);
            if (uid != null) {
                MethodSignature signature = (MethodSignature) pjp.getSignature();
                SkyRoleSecurity annotation = signature.getMethod().getAnnotation(SkyRoleSecurity.class);
                //query user roles
                List<String> userRoleList = skySecurity.doSkySecurityQueryUserRoleList(uid);

                String[] roleListExclude = annotation.roleListExclude();

                if (roleListExclude.length > 0) {
                    List<String> roleExcludeList = new ArrayList<>(Arrays.asList(roleListExclude));
                    // 交集
                    roleExcludeList.retainAll(userRoleList);
                    if (roleExcludeList.size() != 0) {
                        return skySecurity.doSkySecurityNoRoleProcessed(response);
                    }
                }

                String[] roleListNeed = annotation.roleListNeed();

                if (roleListNeed.length > 0) {
                    ArrayList<String> roleNeedList = new ArrayList<>(Arrays.asList(roleListNeed));
                    int size = roleNeedList.size();
                    // 交集
                    roleNeedList.retainAll(userRoleList);
                    if (roleNeedList.size() != size) {
                        return skySecurity.doSkySecurityNoRoleProcessed(response);
                    }
                }

                String[] roleListOnlyOne = annotation.roleListOnlyOne();
                if (roleListOnlyOne.length > 0) {
                    ArrayList<String> roleOnlyOneList = new ArrayList<>(Arrays.asList(roleListOnlyOne));
                    // 交集
                    roleOnlyOneList.retainAll(userRoleList);
                    if (roleOnlyOneList.size() == 0) {
                        return skySecurity.doSkySecurityNoRoleProcessed(response);
                    }
                }
                pjp.proceed();
            }
        } catch (Throwable e) {
            e.printStackTrace();
            return skySecurity.doSkySecuritySystemErrorProcessed(e, response);
        }
        return skySecurity.doSkySecurityNoLoginProcessed(response);
    }
}
