package com.panther.bitmapSign;

import com.panther.base.BizResult;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * scheme
 *
 * @author panther
 * @version 1.0: UserSignController.java, 2024/7/10 10:38 $
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserSignController {

    private final SignService signService;

    @GetMapping("/sign")
    public BizResult sign(){
        Long userId = 1001L;
        return signService.sign(userId);
    }


    @GetMapping("/sign/count")
    public BizResult signCount(){
        Long userId = 1001L;
        return signService.signCount(userId);
    }

}
