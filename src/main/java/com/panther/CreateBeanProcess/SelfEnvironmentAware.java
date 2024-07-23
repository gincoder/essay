package com.panther.CreateBeanProcess;

import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

/**
 * scheme
 *
 * @author panther
 * @version 1.0: SelfEnvironmentAware.java, 2024/6/4 15:30 $
 */
// @Component
public class SelfEnvironmentAware implements EnvironmentAware {
    @Override
    public void setEnvironment(Environment environment) {
        System.out.println("Environment Aware ...");
    }
}
