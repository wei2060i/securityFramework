package com.security.securityframework.sky.security.aop;

import com.security.securityframework.sky.security.annotation.SkyPermissionSecurity;
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
 * @date 2020/7/3 16:28
 */
@Aspect
@Order(3)
@Component
public class PermissionSecurity {

    @Resource
    private ISkySecurity skySecurity;

    @Around("@annotation(com.security.securityframework.sky.security.annotation.SkyPermissionSecurity)")
    public Object permissionAuth(ProceedingJoinPoint pjp) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();


        try {
            Long uid = skySecurity.doSkySecurityCheckUserId(request);
            if (uid != null) {
                MethodSignature signature = (MethodSignature) pjp.getSignature();
                SkyPermissionSecurity annotation = signature.getMethod().getAnnotation(SkyPermissionSecurity.class);
                //query user roles
                List<String> userPermissionList = skySecurity.doSkySecurityQueryUserPermissionList(uid);

                String[] permissionListExclude = annotation.permissionListExclude();

                if (permissionListExclude.length > 0) {
                    List<String> permissionExcludeList = new ArrayList<>(Arrays.asList(permissionListExclude));
                    // 交集
                    permissionExcludeList.retainAll(userPermissionList);
                    if (permissionExcludeList.size() != 0) {
                        return skySecurity.doSkySecurityNoRoleProcessed(response);
                    }
                }

                String[] permissionListNeed = annotation.permissionListNeed();

                if (permissionListNeed.length > 0) {
                    ArrayList<String> permissionNeedList = new ArrayList<>(Arrays.asList(permissionListNeed));
                    int size = permissionNeedList.size();
                    // 交集
                    permissionNeedList.retainAll(userPermissionList);
                    if (permissionNeedList.size() != size) {
                        return skySecurity.doSkySecurityNoRoleProcessed(response);
                    }
                }

                String[] permissionListOnlyOne = annotation.permissionListOnlyOne();
                if (permissionListOnlyOne.length > 0) {
                    ArrayList<String> permissionOnlyOneList = new ArrayList<>(Arrays.asList(permissionListOnlyOne));
                    // 交集
                    permissionOnlyOneList.retainAll(userPermissionList);
                    if (permissionOnlyOneList.size() == 0) {
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
