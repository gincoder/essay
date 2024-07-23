package com.panther.ip;


import org.springframework.util.StringUtils;


public class AddressUtilTest {

    public static void main(String[] args) {
        // 轮询查询 todo 可以加一个状态码的返回
        for (Addressutilpro item : Addressutilpro.values()) {
            String address = item.getSupplier().getAddressByIp(item.getUrl());
            if (StringUtils.hasText(address)) {
                System.out.println(address);
                break;
            }
        }
    }

}