package com.security.securityframework.dao;

import com.security.securityframework.beans.po.UserRole;
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
public interface UserRoleDao extends BaseMapper<UserRole> {

    @Select("SELECT DISTINCT r.`name`\n" +
            "FROM \n" +
            "user_role ur LEFT JOIN role r ON ur.r_id = r.id\n" +
            "WHERE ur.u_id = #{uid} ")
    List<String> getUserAllRoles(@Param("uid") Long uid);

}
