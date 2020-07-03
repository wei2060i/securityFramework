package com.security.securityframework.sky.service;

import com.security.securityframework.sky.jwt.exception.TokenVerificationException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author sky
 * @date 2020/6/29 13:28
 */
public interface ISkySecurity {

    /**
     * 获取 uid
     * @param request
     * @return
     * @throws TokenVerificationException
     */
    Long doSkySecurityCheckUserId(HttpServletRequest request) throws TokenVerificationException;

    /**
     * 获取用户角色
     * @param uid
     * @return
     */
    List<String> doSkySecurityQueryUserRoleList(Long uid);

    /**
     * 角色认证 false
     * @param response
     * @return
     */
    Object doSkySecurityNoRoleProcessed(HttpServletResponse response);


    /**
     * 获取用户权限
     * @param uid
     * @return
     */
    List<String> doSkySecurityQueryUserPermissionList(Long uid);

    /**
     * 权限认证 false
     * @param response
     * @return
     */
    Object doSkySecurityNoPermissionProcessed(HttpServletResponse response);


    /**
     *  返回值-->未登录
     * @param response
     * @return
     */
    Object doSkySecurityNoLoginProcessed(HttpServletResponse response);
    /**
     * 返回值-->系统错误
     * @param e
     * @param response
     * @return
     */
    Object doSkySecuritySystemErrorProcessed(Throwable e, HttpServletResponse response);
}
