package com.panther.seckill;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.MessageDigest;

/**
 * scheme
 *
 * @author panther
 * @version 1.0: DynamicURLUtils.java, 2024/7/12 14:34 $
 */
public class DynamicURLUtils {

    private final static Logger log = LoggerFactory.getLogger(DynamicURLUtils.class);

    public static String getDynamicUrl(String url){

        try{
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(url.getBytes());
            byte[] digest = md5.digest();

            int i ;
            StringBuffer buffer = new StringBuffer();
            for (int offset = 0; offset < digest.length; offset++) {
                i = digest[offset];
                if(i < 0){
                    i += 256;
                }
                if(i < 16){
                    buffer.append("0");
                }
                buffer.append(Integer.toHexString(i));
            }
            url = buffer.toString();
            log.info("url: {}",url);
        }catch (Exception e){
            e.fillInStackTrace();
        }
        return url;
    }
}
