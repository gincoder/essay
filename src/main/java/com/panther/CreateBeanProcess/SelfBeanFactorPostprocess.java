package com.panther.CreateBeanProcess;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

/**
 * scheme
 *
 * @author panther
 * @version 1.0: SelfBeanFactorPostprocess.java, 2024/6/4 15:24 $
 */
// @Component
public class SelfBeanFactorPostprocess implements BeanFactoryPostProcessor {
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {
        System.out.println("bean post process ...");
    }
}
