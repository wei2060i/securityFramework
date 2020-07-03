package com.security.securityframework.service;

import com.security.securityframework.beans.po.RolePower;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author WeiWei
 * @since 2020-06-29
 */
public interface IRolePowerService extends IService<RolePower> {

    List<String> getUserAllPermission(Long uid);

}
