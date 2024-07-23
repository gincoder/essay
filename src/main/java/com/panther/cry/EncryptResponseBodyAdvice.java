package com.panther.cry;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.panther.base.BizResult;
import com.panther.exception.CryptoException;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;
import sun.reflect.generics.reflectiveObjects.ParameterizedTypeImpl;

import java.lang.reflect.Type;

/**
 * @author wn
 * @description: responseBody自动加密
 */
@ControllerAdvice
public class EncryptResponseBodyAdvice implements ResponseBodyAdvice<BizResult<?>> {

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        ParameterizedTypeImpl genericParameterType = (ParameterizedTypeImpl)returnType.getGenericParameterType();

        // 如果直接是Result并且有解密注解，则处理
        if (genericParameterType.getRawType() == BizResult.class && returnType.hasMethodAnnotation(EncryptionAnnotation.class)) {
            return true;
        }

        // 如果不是ResponseBody或者是Result，则放行
        if (genericParameterType.getRawType() != ResponseEntity.class) {
            return false;
        }

        // 如果是ResponseEntity<Result>并且有解密注解，则处理
        for (Type type : genericParameterType.getActualTypeArguments()) {
            if (((ParameterizedTypeImpl) type).getRawType() == BizResult.class && returnType.hasMethodAnnotation(EncryptionAnnotation.class)) {
                return true;
            }
        }

        return false;
    }

    @SneakyThrows
    @Override
    public BizResult<?> beforeBodyWrite(BizResult<?> body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {

        // 真实数据
        Object data = body.getData();

        // 如果data为空，直接返回
        if (data == null) {
            return body;
        }

        // 如果是实体，并且继承了Request，则放入时间戳
        if (data instanceof RequestBase) {
            ((RequestBase)data).setCurrentTimeMillis(System.currentTimeMillis());
        }

        String dataText = JSONUtil.toJsonStr(data);

        // 如果data为空，直接返回
        if (StrUtil.isBlank(dataText)) {
            return body;
        }

        // 如果位数小于16，报错
        if (dataText.length() < 16) {
            throw new CryptoException("加密失败，数据小于16位");
        }

        String encryptText = AESUtil.encryptHex(dataText);

        return BizResult.create(encryptText);
    }
}
