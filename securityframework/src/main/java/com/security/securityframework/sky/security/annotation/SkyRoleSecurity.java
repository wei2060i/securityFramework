package com.security.securityframework.sky.security.annotation;

import java.lang.annotation.*;

/**
 * 三者必须同时满足
 * @author sky
 * @date 2020/6/29 10:33
 */
@Inherited
@Documented
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface SkyRoleSecurity {

    //角色白名单,拥有所有角色的人,可以访问
    String[] roleListNeed() default {};

    //角色白名单,拥有任一角色的人,可以访问
    String[] roleListOnlyOne() default {};

    //角色黑名单,拥有其中一个,就不能访问
    String[] roleListExclude() default {};
}