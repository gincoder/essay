package com.panther.alipay;

import lombok.Data;

import java.util.UUID;

@Data
public class AliPay {
    private String traceNo = UUID.randomUUID().toString();
    private double totalAmount = 1000;
    private String subject = "sandbox 默认应用:2088721039235392";
    private String alipayTraceNo = traceNo;
}

