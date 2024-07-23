package com.panther.CreateBeanProcess;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * scheme
 *
 * @author panther
 * @version 1.0: SelfApplicationContextAware.java, 2024/6/4 15:29 $
 */
// @Component
public class SelfApplicationContextAware implements ApplicationContextAware {
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println("Application Context ...");
    }
}
