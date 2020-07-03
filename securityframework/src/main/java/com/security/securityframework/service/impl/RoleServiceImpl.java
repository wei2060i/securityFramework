package com.security.securityframework.service.impl;

import com.security.securityframework.beans.po.Role;
import com.security.securityframework.dao.RoleDao;
import com.security.securityframework.service.IRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author WeiWei
 * @since 2020-06-29
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleDao, Role> implements IRoleService {

}
