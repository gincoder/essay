package com.panther.CreateBeanProcess;

import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.stereotype.Component;

/**
 * scheme
 *
 * @author panther
 * @version 1.0: selfprocessBeanFactor.java, 2024/6/4 15:25 $
 */
// @Component
public class selfProcessBeanFactor extends AutowiredAnnotationBeanPostProcessor {
    @Override
    public void postProcessMergedBeanDefinition(RootBeanDefinition beanDefinition, Class<?> beanType, String beanName) {
        System.out.println("Autowired Annotation Bean PostProcessor ...");
    }
}
