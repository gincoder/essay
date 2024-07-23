package com.panther.retrying;

import com.panther.base.BizResult;
import lombok.extern.slf4j.Slf4j;

import java.util.Random;

@Slf4j
public class MockService {
		
  	// 模拟远程服务调用 
    public static BizResult call() {
        Random rand = new Random();
        int result = rand.nextInt(10);

        if(result == 0) {       // 成功
            return BizResult.create("success!");
        } else {
            try {
                Thread.sleep(1 * 1000);
            } catch (Exception e) {
                log.error(e.getMessage(), e);
            }
            throw new RuntimeException("处理超过1s，超时");
        }
    }
}

