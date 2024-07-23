package com.panther.retrying;

import com.github.rholder.retry.Retryer;
import com.github.rholder.retry.RetryerBuilder;
import com.github.rholder.retry.StopStrategies;
import com.github.rholder.retry.WaitStrategies;
import com.panther.base.BizResult;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/**
 * scheme
 *
 * @author panther
 * @version 1.0: guavaRetries.java, 2024/7/11 11:23 $
 */
@Slf4j
public class guavaRetries {

    public BizResult graceRetry() {
        Retryer<BizResult> retryer = RetryerBuilder.<BizResult>newBuilder()
                .retryIfException()		// 当发生异常时重试
                .retryIfResult(response -> response.getCode() != 200)		// 当返回码不为200时重试
                .withWaitStrategy(WaitStrategies.fibonacciWait(1000, 10, TimeUnit.SECONDS))	// 等待策略：使用斐波拉契数列递增等待
                .withStopStrategy(StopStrategies.stopAfterAttempt(10))		// 重试达到10次时退出
                .build();
        try {
            return retryer.call(new Callable<BizResult>() {
                @Override
                public BizResult call() throws Exception {
                    log.info("重试调用");
                    return MockService.call();
                }
            });
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        throw new RuntimeException("重试失败");
    }



}
