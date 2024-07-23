package com.panther.feign.provider;

import com.panther.base.BizResult;
import org.springframework.web.bind.annotation.GetMapping;

// @FeignClient(name = "user")
public interface UserClient {
    @GetMapping("/user/test")
    BizResult<?> test1(String test1);

    @GetMapping("/system/test")
    BizResult<?> test2(String test2);
}
