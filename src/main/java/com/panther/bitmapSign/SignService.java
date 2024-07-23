package com.panther.bitmapSign;

import com.panther.base.BizResult;
import jodd.template.StringTemplateParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.BitFieldSubCommands;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * scheme
 *
 * @author panther
 * @version 1.0: SignService.java, 2024/7/10 10:48 $
 */
@Service
public class SignService {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    public BizResult sign(Long userId) {
        // 2.获取日期
        LocalDateTime now = LocalDateTime.now();
        // 3.拼接key
        String keySuffix =
                now.format(DateTimeFormatter.ofPattern(":yyyyMM")
                );
        String key = "USER_SIGN_KEY:" + userId + keySuffix;
        // 4.获取今天是本月的第几天
        int dayOfMonth = now.getDayOfMonth();
        stringRedisTemplate.opsForValue().setBit(key,
                dayOfMonth - 1, true);
        return BizResult.create(Boolean.TRUE);
    }


    public BizResult signCount(Long userId) {
        LocalDateTime now = LocalDateTime.now();
        String keySuffix =
                now.format(DateTimeFormatter.ofPattern(":yyyyMM")
                );
        String key = "USER_SIGN_KEY:" + userId + keySuffix;
        int dayOfMonth = now.getDayOfMonth();
        List<Long> result = stringRedisTemplate.opsForValue().bitField(key,
                BitFieldSubCommands
                        .create()
                        .get(BitFieldSubCommands.BitFieldType.unsigned(dayOfMonth))
                        .valueAt(0)
        );

        if(result == null || result.isEmpty() ){
            return BizResult.create(0);
        }

        Long num = result.get(0);
        if(num == null || num == 0){
            return BizResult.create(0);
        }
        int count = 0;
        while (true) {
            if ((num & 1) == 0) {
                // 如果为0，说明未签到，结束
                break;
            }else {
                // 如果不为0，说明已签到，计数器+1
                count++;
            }
            num >>>= 1;
        }

        return BizResult.create(count);
    }
}
