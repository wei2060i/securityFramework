package com.security.securityframework.service;

import com.security.securityframework.beans.po.UserRole;
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
public interface IUserRoleService extends IService<UserRole> {

    List<String> getUserAllRoles(Long uid);
}
