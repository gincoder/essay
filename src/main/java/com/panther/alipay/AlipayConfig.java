package com.panther.alipay;

/**
 * scheme
 *
 * @author panther
 * @version 1.0: AlipayConfig.java, 2024/7/9 19:28 $
 */
public class AlipayConfig {

    // 商户appid
    public static String APPID = "";
    // 私钥 pkcs8格式的
    public static String RSA_PRIVATE_KEY = "+3ItUPeCwSe3AIVkB/Hmk4VHRBO1/Kxuerpuv5fIiUsbl+ntlb+Wj5mkc6xJ1raHMTLqF4EmMfiz/XTOGN4crXU7LWm79zWrA90zYZFcEuhVFHUO5gfXnhM4fz8+DTIj0lP0SUbsOHyRgoNHrGtS/gvuE3+JLfm0cmdxKUFLfV4CmfopB7QpUvAq8aZG9CWhLawmQ3XFx16Dat+u6nr7FslqSdBzAeCHz35wxoMrYoqcbEOdBVOCtqKeQRVOy58jmG/p0qaBOUA9M+q1fmsP6rKheTDzXMd5Ef5o+ZNcjS/JlAUxd4iewHAgMBAAECggEBAIp4zuV2DS1UcAj4NcpnepoSs6gDk9Wl46PqSpe8jbjAcg2WCkxsX7GEqvqs2HvhYw5aoLqY3nthO/Jf1cZlYFwV5MkY4Q64cSPpqGSkUhnxbG9eAW1OOLt4RrG5GZdnpqHo+BTygEjw5nxt2zfZqbDqk+7Q46aDNaTMbqUQkA4emq3cXEmSjBb9MKRnaBr+K3jaQbbwx5E8wFlmTPCrrWMybBVsW7kJrAhD9b7/jWKlPMrVCxYJnmfq+54aYb0lJXQCWJBzlcQwqdVbg8FTxxUTYMzGesQ+/TmAONWNjVhcqBRJhv89S+C/NxCfi7Pnr8w4uZ3XOkrGTwApbF9J+gECgYEAygu1BzO28+uChQKtOaDfek8xqcBT17tcrj5nTBxSv/xzJ5Kd2RyaWY4Z8N1bY3JzAQHj0NX+EsAbBvTLhQmZ6dF7iDD5LUOoLwOBYaSIZUQge4Gt/XAAhGFVo8Ys+fCuEzwuAmRz4iyNcBmyzkBoeLsQeL//tZ0bcWF2/cKwfb8CgYEAth8hFLOhX7sRxUzw/aFy+7e331m2PefkmDmAI7XFSDoWR4fvpfSNGaHXChxqR+TCza0OVZFAyGH3pOLr3yUDIUGBpywlhxyDjAz+Iw7KeACPnNm19JCvI5ihX86Z79WCH5Fnjgt/oPqcCeQvRAvJJOYqA9AwqEl4Nj8qjKKOM7kCgYEAsTdBilae0yWv7pChddmYCsSpr9XE9IUfTqnnmseBAWohawVbtFI1Q/Wy5SSTAxAgtTJ+Ku93keS0EA7o8ACy4nZeXt9tv3kHbG0rr4RMKl5uC0Z8YL47heGF50dsrbGgTbjMNkb7qdcgG8I+pk+yEqspUHA/joGFz5YIc52vhisCgYAOOPQeZ8DMdxF2NQIE7JzMJ5bGbH0c7IUfli35m/JiJ5ch0u3WKfjy7X6bnYh3QNgts7wQ1p0WiQAL8mS1MnDCIhl78qijmohzYXhfSIJrSK/ucdoR1VK4Fu1TTBEdd7EHwhUyNJes/KU+6IEAcWMt7IOPdKy4F7S5l8k83lz4kQKBgCpX27oxzvijga9JBMNFqsIVZ6pT3jV7t0dje7xaIAqHAPNJMXvPquGTgjCt/HzfGTuWsnASQEibTp2/QOFM6yAyCP+MQ+b6CQerqwagaITIQijxxhvOJlJBNPi0oWJQgcsk0AGJ94OfyPXqZWWmWOIbyBeQgpbtQQ9UeY0HBAho";
    // 服务器异步通知页面路径 需http://或者https://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String notify_url = "https://1766-101-68-35-12.ngrok-free.app";
    // 页面跳转同步通知页面路径 需http://或者https://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问 商户可以自定义同步跳转地址
    public static String return_url = "https://openapi-sandbox.dl.alipaydev.com/gateway.do/alipay.trade.wap.pay-JAVA-UTF-8/return_url.jsp";
    // 请求网关地址
    public static String URL = "https://openapi-sandbox.dl.alipaydev.com/gateway.do";
    // 编码
    public static String CHARSET = "UTF-8";
    // 返回格式
    public static String FORMAT = "json";
    // 支付宝公钥
    public static String ALIPAY_PUBLIC_KEY = "/tyLVD3gsEntwCFZAfx5pOFR0QTtfysbnq6br+XyIlLG5fp7ZW/lo+ZpHOsSda2hzEy6heBJjH4s/10zhjeHK11Oy1pu/c1qwPdM2GRXBLoVRR1DuYH154TOH8/Pg0yI9JT9ElG7Dh8kYKDR6xrUv4L7hN/iS35tHJncSlBS31eApn6KQe0KVLwKvGmRvQloS2sJkN1xcdeg2rfrup6+xbJaknQcwHgh89+cMaDK2KKnGxDnQVTgrainkEVTsufI5hv6dKmgTlAPTPqtX5rD+qyoXkw81zHeRH+aPmTXI0vyZQFMXeInsBwIDAQAB";
    // 日志记录目录
    public static String log_path = "/log";
    // RSA2
    public static String SIGNTYPE = "RSA2";

}
