package com.panther.base;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * scheme
 *
 * @author panther
 * @version 1.0: ResponseResultIntercetor.java, 2024/7/22 11:45 $
 */
@Component
@Slf4j
public class ResponseResultInterceptor implements HandlerInterceptor {

    protected static final String RESPONSE_SIGN = "RESPONSE_SIGN";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof HandlerMethod) {
            final HandlerMethod handlerMethod = (HandlerMethod) handler;
            final Class<?> clazz = handlerMethod.getBeanType();
            final Method method = handlerMethod.getMethod();
            if (clazz.isAnnotationPresent(ResponseResult.class)) {
                request.setAttribute(RESPONSE_SIGN, clazz.getAnnotation(ResponseResult.class));
            } else if (method.isAnnotationPresent(ResponseResult.class)) {
                request.setAttribute(RESPONSE_SIGN, method.getAnnotation(ResponseResult.class));
            }
        }
        return Boolean.TRUE;
    }
}
