package com.panther.cry;

import cn.hutool.core.util.StrUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.panther.exception.ParamException;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdvice;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.lang.reflect.Type;

/**
 * @author wn
 * @description: requestBody 自动解密
 */
@ControllerAdvice
public class DecryptRequestBodyAdvice implements RequestBodyAdvice {

    @Autowired
    private ObjectMapper objectMapper;

    /**
     * 方法上有DecryptionAnnotation注解的，进入此拦截器
     * @param methodParameter 方法参数对象
     * @param targetType 参数的类型
     * @param converterType 消息转换器
     * @return true，进入，false，跳过
     */
    @Override
    public boolean supports(MethodParameter methodParameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
        return methodParameter.hasMethodAnnotation(DecryptionAnnotation.class);
    }

    @Override
    public HttpInputMessage beforeBodyRead(HttpInputMessage inputMessage, MethodParameter parameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) throws IOException {
        return inputMessage;
    }

    /**
     * 转换之后，执行此方法，解密，赋值
     * @param body spring解析完的参数
     * @param inputMessage 输入参数
     * @param parameter 参数对象
     * @param targetType 参数类型
     * @param converterType 消息转换类型
     * @return 真实的参数
     */
    @SneakyThrows
    @Override
    public Object afterBodyRead(Object body, HttpInputMessage inputMessage, MethodParameter parameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {

        // 获取request
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) requestAttributes;
        if (servletRequestAttributes == null) {
            throw new ParamException("request错误");
        }

        HttpServletRequest request = servletRequestAttributes.getRequest();

        // 获取数据
        ServletInputStream inputStream = request.getInputStream();
        RequestData requestData = objectMapper.readValue(inputStream, RequestData.class);

        if (requestData == null || StrUtil.isBlank(requestData.getText())) {
            throw new ParamException("参数错误");
        }

        // 获取加密的数据
        String text = requestData.getText();

        // 放入解密之前的数据
        request.setAttribute(CryptoConstant.INPUT_ORIGINAL_DATA, text);

        // 解密
        String decryptText = null;
        try {
            decryptText = AESUtil.decrypt(text);
        } catch (Exception e) {
            throw new ParamException("解密失败");
        }

        if (StrUtil.isBlank(decryptText)) {
            throw new ParamException("解密失败");
        }

        // 放入解密之后的数据
        request.setAttribute(CryptoConstant.INPUT_DECRYPT_DATA, decryptText);

        // 获取结果
        Object result = objectMapper.readValue(decryptText, body.getClass());

        // 强制所有实体类必须继承RequestBase类，设置时间戳
        if (result instanceof RequestBase) {
            // 获取时间戳
            Long currentTimeMillis = ((RequestBase) result).getCurrentTimeMillis();
            // 有效期 60秒
            long effective = 60*1000;

            // 时间差
            long expire = System.currentTimeMillis() - currentTimeMillis;

            // 是否在有效期内
            if (Math.abs(expire) > effective) {
                throw new ParamException("时间戳不合法");
            }

            // 返回解密之后的数据
            return result;
        } else {
            throw new ParamException(String.format("请求参数类型：%s 未继承：%s", result.getClass().getName(), RequestBase.class.getName()));
        }
    }

    /**
     * 如果body为空，转为空对象
     * @param body spring解析完的参数
     * @param inputMessage 输入参数
     * @param parameter 参数对象
     * @param targetType 参数类型
     * @param converterType 消息转换类型
     * @return 真实的参数
     */
    @SneakyThrows
    @Override
    public Object handleEmptyBody(Object body, HttpInputMessage inputMessage, MethodParameter parameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
        String typeName = targetType.getTypeName();
        Class<?> bodyClass = Class.forName(typeName);
        return bodyClass.newInstance();
    }
}
