package com.security.securityframework.sky.security.annotation;

import java.lang.annotation.*;

/**
 * @author sky
 * @date 2020/6/29 10:43
 */
@Inherited
@Documented
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface SkyPassLogin {
}
