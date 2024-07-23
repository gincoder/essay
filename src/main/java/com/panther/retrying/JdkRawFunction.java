package com.panther.retrying;

import com.panther.base.BizResult;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * scheme
 *       java原生方法实现重试
 * @author panther
 * @version 1.0: JdkRawFunction.java, 2024/7/11 11:20 $
 */
@Slf4j
public class JdkRawFunction {

    public BizResult commonRetry() {
        AtomicInteger retryTimes = new AtomicInteger(0);
        while(retryTimes.getAndAdd(1) <= 10) {
            try {
                log.info("第{}次调用", retryTimes.get());
                BizResult response = MockService.call();
                if(response.getCode() == 200) {
                    return response;
                }
                Thread.sleep(1000);		// 失败，等待下次调用
            } catch (Exception e) {
                log.error(e.getMessage(), e);
                try {
                    Thread.sleep(fibonacciWait(retryTimes.get()));
                } catch (Exception e2) {
                    log.error(e2.getMessage(), e2);
                }
            }
        }
        throw new RuntimeException("重试失败");
    }

    public Integer fibonacciWait(int count){
        int[] dp = new int[count + 3];

        dp[0] = 1;
        dp[1] = 1;

        for (int i = 2; i <= count; i++) {
            dp[i] = dp[i - 2] + dp[i - 1];
        }
        return dp[count];
    }


}
