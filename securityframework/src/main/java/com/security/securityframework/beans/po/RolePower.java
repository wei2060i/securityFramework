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
public class RolePower extends Model<RolePower> {

    private static final long serialVersionUID=1L;

    private Long id;

    private String rId;

    private String pId;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
