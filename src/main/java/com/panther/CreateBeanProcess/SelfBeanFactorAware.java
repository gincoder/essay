package com.panther.CreateBeanProcess;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.stereotype.Component;

/**
 * scheme
 *
 * @author panther
 * @version 1.0: SelfBeanFactorAware.java, 2024/6/4 15:28 $
 */
// @Component
public class SelfBeanFactorAware implements BeanFactoryAware {
    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        System.out.println("bean factor aware ...");
    }
}
