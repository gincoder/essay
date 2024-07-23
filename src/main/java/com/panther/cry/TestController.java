package com.panther.cry;

import com.panther.base.BizResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * springboot一个注解实现接口加解密！新姿势！
 */
@RestController
public class TestController {

    /**
     * 返回加密后的数据
     * @param teacher Teacher对象
     * @return 返回加密后的数据 ResponseBody<Result>格式
     */
    @PostMapping("/encrypt")
    @EncryptionAnnotation
    public BizResult<?> encrypt(@Validated @RequestBody Teacher teacher) {
        return BizResult.create(teacher);
    }

    /**
     * 返回解密后的数据
     * @param teacher Teacher对象
     * @return 返回解密后的数据
     */
    @PostMapping("/decrypt")
    @DecryptionAnnotation
    public BizResult<?> decrypt(@Validated @RequestBody Teacher teacher) {
        return BizResult.create(teacher);
    }

}
