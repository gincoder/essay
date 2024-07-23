package com.panther.CreateBeanProcess;

import org.springframework.beans.factory.SmartInitializingSingleton;
import org.springframework.stereotype.Component;

/**
 * scheme
 *
 * @author panther
 * @version 1.0: SelfSmartInitializingSingleton.java, 2024/6/4 15:35 $
 */
// @Component
public class SelfSmartInitializingSingleton implements SmartInitializingSingleton {
    @Override
    public void afterSingletonsInstantiated() {
        System.out.println("after Singletons Instantiated");
    }
}
