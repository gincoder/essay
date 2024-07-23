// package com.panther.feign;
//
// import org.springframework.context.ApplicationContext;
// import org.springframework.stereotype.Component;
//
// public class DynamicFeignClientFactory<T> {
//
//     private FeignClientBuilder feignClientBuilder;
//
//     public DynamicFeignClientFactory(ApplicationContext appContext) {
//         this.feignClientBuilder = new FeignClientBuilder(appContext);
//     }
//
//     public T getFeignClient(final Class<T> type, String serviceId) {
//         return this.feignClientBuilder.forType(type, serviceId).build();
//     }
// }
