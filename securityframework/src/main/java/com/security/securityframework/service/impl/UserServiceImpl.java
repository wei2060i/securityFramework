package com.security.securityframework.service.impl;

import com.security.securityframework.beans.po.User;
import com.security.securityframework.dao.UserDao;
import com.security.securityframework.service.IUserService;
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
public class UserServiceImpl extends ServiceImpl<UserDao, User> implements IUserService {

}
