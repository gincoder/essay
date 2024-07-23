package com.panther.CreateBeanProcess;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * scheme
 *
 * @author panther
 * @version 1.0: MyBean.java, 2024/6/4 15:33 $
 */
// @Configuration
public class MyBean {

    @Bean(initMethod = "com.panther.CreateBeanProcess.MyBean.initMethod()")
    public void test(){
        System.out.println("test");
    }

    public static void initMethod(){
        System.out.print("init method");
    }
}
