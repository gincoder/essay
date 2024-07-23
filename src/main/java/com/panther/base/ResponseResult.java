package com.panther.base;


import java.lang.annotation.*;


/**
 * scheme
 *
 * @author panther
 * @version 1.0: ResponseResult.java, 2024/7/22 11:43 $
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE , ElementType.METHOD })
public @interface ResponseResult {
}
