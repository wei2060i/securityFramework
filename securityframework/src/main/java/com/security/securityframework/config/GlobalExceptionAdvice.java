package com.security.securityframework.config;

import com.security.securityframework.beans.dto.DataBean;
import com.security.securityframework.consts.CodeConstant;
import com.security.securityframework.exception.ErrorParamException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.HashMap;
import java.util.Set;


/**
 * @author sky
 * @date 2020/7/1 11:05
 */
@ControllerAdvice
@ResponseBody
public class GlobalExceptionAdvice {


    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(ErrorParamException.class)
    public DataBean handleErrorParamExceptionException(ErrorParamException e) {
        e.printStackTrace();
        DataBean bean = new DataBean();
        System.out.println("Service layer ErrorParamException");
        bean.setCode(CodeConstant.ERROR_PARAM);
        bean.setData(e.getMessage());
        return bean;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public DataBean handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
        e.printStackTrace();
        DataBean bean = new DataBean();
        System.out.println("The parameter cannot be resolved");
        bean.setCode(CodeConstant.ERROR_PARAM);
        bean.setData(e.getMessage());
        return bean;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public DataBean handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        e.printStackTrace();
        DataBean bean = new DataBean();
        System.out.println("Error in checking parameters such as post or put request");
        BindingResult bindingResult = e.getBindingResult();

        final HashMap<String, String> map = new HashMap<>();
        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            map.put(fieldError.getField(), fieldError.getDefaultMessage());
        }
        bean.setCode(CodeConstant.ERROR_PARAM);
        bean.setData(map);
        return bean;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException.class)
    public DataBean handleConstraintViolationException(ConstraintViolationException e) {
        e.printStackTrace();
        DataBean bean = new DataBean();
        System.out.println("Get request parameter validation error");
        StringBuilder sb = new StringBuilder();
        Set<ConstraintViolation<?>> constraintViolations = e.getConstraintViolations();
        for (ConstraintViolation<?> item : constraintViolations) {
            sb.append(item.getMessage()).append("/");
        }
        bean.setCode(CodeConstant.ERROR_PARAM);
        bean.setData(sb.toString());
        return bean;
    }

    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public DataBean handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
        e.printStackTrace();
        DataBean bean = new DataBean();
        System.out.println("The current request method is not supported");
        bean.setCode(CodeConstant.ERROR_PARAM);
        bean.setData(e.getMessage());
        return bean;
    }


    @ResponseStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public DataBean HandlerHttpMediaTypeNotSupportedException(HttpMediaTypeNotSupportedException e) {
        DataBean bean = new DataBean();
        e.printStackTrace();
        System.out.println("The current media type is not supported");
        bean.setCode(CodeConstant.ERROR_PARAM);
        bean.setData(e.getMessage());
        return bean;
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public DataBean HandlerDefaultException(Exception e) {
        DataBean bean = new DataBean();
        e.printStackTrace();
        System.out.println("System error");
        bean.setCode(CodeConstant.FALSE);
        bean.setData(e.getMessage());
        return bean;
    }
}