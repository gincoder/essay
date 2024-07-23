package com.panther.alipay;

import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Base64;


public class logFile {
	 /** 
     * 写日志，方便测试（看网站需求，也可以改成把记录存入数据库）
     * @param sWord 要写入日志里的文本内容
     */
    public static void logResult(String sWord) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(AlipayConfig.log_path + "alipay_log_" + System.currentTimeMillis()+".txt");
            writer.write(sWord);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        String base64String = "5Lit5Y2O5LqG5a\r\nW977yM5LiA\r\n"; // 示例的Base64字符串
        //base64String = base64String.replace("\n","");
        try {
            byte[] decodedBytes = Base64.getDecoder().decode(base64String.replace("\r\n",""));
            String decodedString = new String(decodedBytes, "UTF-8");
            System.out.println("解码后的字符串: " + decodedString);
        } catch (IllegalArgumentException | UnsupportedEncodingException e) {
            System.err.println("解码Base64字符串时发生错误: " + e.getMessage());
        }
    }
}
