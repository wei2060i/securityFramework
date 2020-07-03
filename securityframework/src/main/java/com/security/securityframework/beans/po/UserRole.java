package com.security.securityframework.beans.po;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author WeiWei
 * @since 2020-06-29
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class UserRole extends Model<UserRole> {

    private static final long serialVersionUID=1L;

    private Long id;

    private String uId;

    private String rId;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
