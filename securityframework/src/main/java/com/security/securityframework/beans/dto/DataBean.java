package com.security.securityframework.beans.dto;


import com.security.securityframework.consts.CodeConstant;
import lombok.Data;

/**
 * @author sky
 * @date 2020/6/29 11:24
 */
@Data
public class DataBean {
    private int code = CodeConstant.SUCCESS;
    private Object data;
    private Object msg;
}
