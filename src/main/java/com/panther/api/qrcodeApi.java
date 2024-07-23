package com.panther.api;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpUtil;

import java.nio.charset.StandardCharsets;

/**
 * scheme
 *
 * @author panther
 * @version 1.0: qrcodeApi.java, 2024/7/22 21:00 $
 */
public class qrcodeApi {

    final static String api = "https://api.chenyande.cn/api/qrcode/?text=https://blog.gincode.icu";

    public static void main(String[] args) {
        byte[] bytes = HttpUtil.get(api).getBytes(StandardCharsets.UTF_8);
        System.out.println(new String(bytes));
    }

}
