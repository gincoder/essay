package com.panther.feign.provider;

import com.panther.base.BizResult;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 手写一个动态Feign，
 * 实现一个“万能”接口调用
 *
 */
// @FeignClient(name = "product")
public interface ProductClient {
    @GetMapping("/product/test1")
    BizResult<?> test1(String test1);

    @GetMapping("/product/test2")
    BizResult<?> test2(String test2);
}
