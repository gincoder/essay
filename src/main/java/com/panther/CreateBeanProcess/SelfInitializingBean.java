package com.panther.CreateBeanProcess;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

/**
 * scheme
 *
 * @author panther
 * @version 1.0: SelfInitializingBean.java, 2024/6/4 15:36 $
 */
// @Component
public class SelfInitializingBean implements InitializingBean {
    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("InitializingBean.afterPropertiesSet");
    }
}
