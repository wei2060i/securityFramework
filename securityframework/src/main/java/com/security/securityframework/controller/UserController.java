package com.security.securityframework.controller;


import com.security.securityframework.beans.dto.DataBean;
import com.security.securityframework.consts.CodeConstant;
import com.security.securityframework.exception.ErrorParamException;
import com.security.securityframework.sky.jwt.exception.TokenCreateException;
import com.security.securityframework.sky.jwt.service.JwtUtil;
import com.security.securityframework.sky.security.annotation.SkyPassLogin;
import com.security.securityframework.sky.security.annotation.SkyRoleSecurity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author WeiWei
 * @since 2020-06-29
 */
@RestController
@CrossOrigin(allowCredentials = "true")
@RequestMapping("/user")
public class UserController {

    @Resource
    private JwtUtil jwtUtil;

    @GetMapping("login")
    @SkyPassLogin
    public DataBean login() throws ErrorParamException {
        DataBean bean = new DataBean();
        throw new ErrorParamException("参数错误啊啊啊啊啊啊啊啊啊");
//        try {
//            String jwtToken = jwtUtil.getJwtToken(1L, LocalDateTime.now().plusMinutes(1));
//            bean.setData(jwtToken);
//            return bean;
//        } catch (TokenCreateException e) {
//            e.printStackTrace();
//            bean.setCode(CodeConstant.NO_LOGIN);
//            return bean;
//        }
    }

    @GetMapping("test")
    public DataBean Hello() {
        DataBean bean = new DataBean();
        bean.setData("hello");
        return bean;
    }

}