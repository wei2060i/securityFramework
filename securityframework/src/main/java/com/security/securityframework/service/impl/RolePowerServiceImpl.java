package com.security.securityframework.service.impl;

import com.security.securityframework.beans.po.RolePower;
import com.security.securityframework.dao.RolePowerDao;
import com.security.securityframework.service.IRolePowerService;
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
public class RolePowerServiceImpl extends ServiceImpl<RolePowerDao, RolePower> implements IRolePowerService {

    @Resource
    private RolePowerDao rolePowerDao;

    @Override
    public List<String> getUserAllPermission(Long uid) {
        return rolePowerDao.getUserAllPermission(uid);
    }
}
