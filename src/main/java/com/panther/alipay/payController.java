package com.panther.alipay;

import com.alipay.easysdk.factory.Factory;
import com.alipay.easysdk.payment.page.models.AlipayTradePagePayResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URLEncoder;

/**
 * scheme
 *
 * @author panther
 * @version 1.0: payController.java, 2024/7/9 19:21 $
 */
@RestController
@RequestMapping("alipay")
public class payController {

    @GetMapping("/pay")
    public String pay(AliPay aliPay) {
        AlipayTradePagePayResponse response;
        try {
            URLEncoder.encode(aliPay.getSubject(), "UTF-8");
            aliPay.getTraceNo();
            String.valueOf(aliPay.getTotalAmount());
            //  发起API调用（以创建当面付收款二维码为例）
            response = Factory.Payment.Page()
                    .pay(URLEncoder.encode(aliPay.getSubject(), "UTF-8"), aliPay.getTraceNo(), String.valueOf(aliPay.getTotalAmount()), "");
        } catch (Exception e) {
            System.err.println("调用遭遇异常，原因：" + e.getMessage());
            throw new RuntimeException(e.getMessage(), e);
        }
        return response.getBody();
    }
}

