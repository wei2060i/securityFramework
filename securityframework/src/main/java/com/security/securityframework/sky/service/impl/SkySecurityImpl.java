package com.security.securityframework.sky.service.impl;

import com.security.securityframework.beans.dto.DataBean;
import com.security.securityframework.consts.CodeConstant;
import com.security.securityframework.service.IRolePowerService;
import com.security.securityframework.service.IUserRoleService;
import com.security.securityframework.service.IUserService;
import com.security.securityframework.sky.jwt.exception.TokenVerificationException;
import com.security.securityframework.sky.jwt.service.JwtUtil;
import com.security.securityframework.sky.service.ISkySecurity;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author sky
 * @date 2020/6/29 13:30
 */
@Service
public class SkySecurityImpl implements ISkySecurity {

    @Resource
    private JwtUtil jwtUtil;
    @Resource
    private IUserRoleService userRoleService;
    @Resource
    private IRolePowerService rolePowerService;
    @Resource
    private IUserService userService;

    @Override
    public Long doSkySecurityCheckUserId(HttpServletRequest request) throws TokenVerificationException {
        String token = request.getHeader("token");
        if (token != null) {
            Long uid = jwtUtil.isTrueToken(token);
            if (userService.getById(uid) != null) {
                return uid;
            }
        }
        return null;
    }

    @Override
    public List<String> doSkySecurityQueryUserRoleList(Long uid) {
        return userRoleService.getUserAllRoles(uid);
    }

    @Override
    public Object doSkySecurityNoRoleProcessed(HttpServletResponse response) {
        DataBean bean = new DataBean();
        bean.setCode(CodeConstant.NO_PERMISSION);
        return bean;
    }

    @Override
    public List<String> doSkySecurityQueryUserPermissionList(Long uid) {
        return rolePowerService.getUserAllPermission(uid);
    }

    @Override
    public Object doSkySecurityNoPermissionProcessed(HttpServletResponse response) {
        DataBean bean = new DataBean();
        bean.setCode(CodeConstant.NO_PERMISSION);
        return bean;
    }

    @Override
    public Object doSkySecurityNoLoginProcessed(HttpServletResponse response) {
        DataBean bean = new DataBean();
        bean.setCode(CodeConstant.NO_LOGIN);
        return bean;
    }

    @Override
    public Object doSkySecuritySystemErrorProcessed(Throwable e, HttpServletResponse response) {
        e.printStackTrace();
        DataBean bean = new DataBean();
        bean.setCode(CodeConstant.FALSE);
        bean.setData(e.getMessage());
        return bean;
    }
}
