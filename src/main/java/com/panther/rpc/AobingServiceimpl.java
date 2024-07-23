package com.panther.rpc;

/**
 * scheme
 *
 * @author panther
 * @version 1.0: AobingServiceimpl.java, 2024/7/6 10:09 $
 */
public class AobingServiceimpl implements AobingService {


    @Override
    public String sayHello(String name) {
        return "hello: " + name;
    }
}
