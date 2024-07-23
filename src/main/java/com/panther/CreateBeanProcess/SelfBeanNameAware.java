package com.panther.CreateBeanProcess;

import org.springframework.beans.factory.BeanNameAware;
import org.springframework.stereotype.Component;

/**
 * scheme
 *
 * @author panther
 * @version 1.0: SelfBeanNameAware.java, 2024/6/4 15:28 $
 */
// @Component
public class SelfBeanNameAware implements BeanNameAware {
    @Override
    public void setBeanName(String s) {
        System.out.println("bean aware...");
    }
}
