package com.panther.CreateBeanProcess;

import org.springframework.context.ResourceLoaderAware;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

/**
 * scheme
 *
 * @author panther
 * @version 1.0: SelfResourceLoaderAwawre.java, 2024/6/4 15:30 $
 */
@Component
public class SelfResourceLoaderAware implements ResourceLoaderAware {
    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        System.out.println("Resource Loader Aware ...");
    }
}
