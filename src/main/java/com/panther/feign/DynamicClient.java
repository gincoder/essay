// package com.panther.feign;
//
// import com.panther.feign.inter.DynamicService;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Component;
//
// @Component
// public class DynamicClient {
//
//     @Autowired
//     private DynamicFeignClientFactory<DynamicService> dynamicFeignClientFactory;
//
//     public Object executePostApi(String feignName, String url, Object params) {
//         DynamicService dynamicService = dynamicFeignClientFactory.getFeignClient(DynamicService.class, feignName);
//         return dynamicService.executePostApi(url, params);
//     }
//
//     public Object executeGetApi(String feignName, String url, Object params) {
//         DynamicService dynamicService = dynamicFeignClientFactory.getFeignClient(DynamicService.class, feignName);
//         return dynamicService.executeGetApi(url, params);
//     }
// }