package com.panther.attackCount;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * scheme
 *
 * @author panther
 * @version 1.0: ActController.java, 2024/7/10 15:32 $
 */
@RestController
public class ActController {

    private static int c = 0;

    @GetMapping("/act/count")
    public void signCount(){
        System.out.println( ++c);
    }

}
