package com.panther.ip;

import cn.hutool.http.HttpRequest;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;

/**
 * scheme
 *
 * @author panther
 * @version 1.0: Addressutilpro.java, 2024/7/22 20:10 $
 */
public enum Addressutilpro {

    URL1("https://webapi-pc.meitu.com/common/ip_location?ip=", (String url) -> {
        String result = HttpRequest.get(url)
                .execute().body();
        JSONObject entries1 = JSONUtil.parseObj(result);
        JSONObject entries2 = JSONUtil.parseObj(entries1.get("data"));
        String s = url.split("=")[1];
        JSONObject entries = JSONUtil.parseObj(entries2.get(s));

        return entries.get("nation") + ":" +
                entries.get("province") + ":" +
                entries.get("city") + ":" + entries.get("isp");
    }),

    URL2("https://www.ip.cn/api/index?type=1&ip=", (String url) -> {
        String result = HttpRequest.get(url)
                .execute().body();
        JSONObject entries = JSONUtil.parseObj(result);

        return entries.get("address").toString();
    }),

    URL3("https://whois.pconline.com.cn/ipJson.jsp?json=true&ip=", (String url) -> {
        String result = HttpRequest.get(url)
                .execute().body();
        JSONObject entries = JSONUtil.parseObj(result);
        return entries.get("addr").toString();
    }),

    ;


    private String url;

    private AddressDefaultAbstract supplier;

    Addressutilpro(String url, AddressDefaultAbstract supplier) {
        this.url = url;
        this.supplier = supplier;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public AddressDefaultAbstract getSupplier() {
        return supplier;
    }

    public void setSupplier(AddressDefaultAbstract supplier) {
        this.supplier = supplier;
    }
}
