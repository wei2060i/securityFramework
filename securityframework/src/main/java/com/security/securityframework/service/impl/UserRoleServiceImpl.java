package com.security.securityframework.service.impl;

import com.security.securityframework.beans.po.UserRole;
import com.security.securityframework.dao.UserRoleDao;
import com.security.securityframework.service.IUserRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author WeiWei
 * @since 2020-06-29
 */
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleDao, UserRole> implements IUserRoleService {

    @Resource
    private UserRoleDao userRoleDao;

    @Override
    public List<String> getUserAllRoles(Long uid) {
        return userRoleDao.getUserAllRoles(uid);
    }
}
