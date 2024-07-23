package com.panther.feign.retry;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * scheme
 *
 * @author panther
 * @version 1.0: FeignRetry.java, 2024/7/22 15:21 $
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface FeignRetry {

    Backoff backoff() default @Backoff();

    int maxAttempt() default 3;

    Class<? extends Throwable>[] include() default {} ;
}
