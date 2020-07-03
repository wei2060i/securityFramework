package com.security.securityframework.dao;

import com.security.securityframework.beans.po.RolePower;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author WeiWei
 * @since 2020-06-29
 */
public interface RolePowerDao extends BaseMapper<RolePower> {

    @Select("SELECT DISTINCT p.`name`\n" +
            "FROM\n" +
            "user_role ur LEFT JOIN role_power rp ON ur.r_id = rp.r_id LEFT JOIN power p ON p.id = rp.p_id\n" +
            "WHERE ur.u_id = #{uid} ")
    List<String> getUserAllPermission(@Param("uid") Long uid);

}
