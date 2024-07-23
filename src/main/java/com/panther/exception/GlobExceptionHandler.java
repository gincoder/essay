package com.panther.exception;

import com.panther.base.BizResult;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @description 统一异常处理类
 */
@RestControllerAdvice  // 返回json
public class GlobExceptionHandler {

    @ExceptionHandler(value = BindException.class)
    public BizResult<?> bindExceptionHandler(BindException ex) {
        ex.printStackTrace();
        // 获取所有错误信息，拼接
        List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
        String errorMsg = fieldErrors.stream()
                .map(fieldError -> fieldError.getField() + ":" + fieldError.getDefaultMessage())
                .collect(Collectors.joining(","));
        // 返回统一处理类
        return BizResult.error(500,errorMsg);
    }

    @ExceptionHandler(value = ParamException.class)
    public BizResult<?> paramExceptionHandler(ParamException ex) {
        ex.printStackTrace();
        // 返回统一处理类
        return BizResult.error(500,ex.getMessage());
    }

}