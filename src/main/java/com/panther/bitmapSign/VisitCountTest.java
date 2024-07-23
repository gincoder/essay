package com.panther.bitmapSign;

import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping(value = "/demo")
public class VisitCountTest {

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    public final static String DAY_VISIT_COUNT = "dayVisitCount:%s";

    @RequestMapping("/count")
    public void count(@RequestBody VisitDTO visitDTO) {
        String visitKey = String.format(DAY_VISIT_COUNT, visitDTO.getDate());
        System.out.println(visitDTO.getDate() + "，访问用户数：" + bitCount(visitKey));
    }

    public long bitCount(String key) {
        Long ans = redisTemplate.execute((RedisCallback<Long>) con -> con.bitCount(key.getBytes()));
        return ans == null ? 0 : ans;
    }

    @RequestMapping("/visit")
    public void visit(@RequestBody VisitDTO visitDTO) {
        String visitKey = String.format(DAY_VISIT_COUNT, visitDTO.getDate());
        // 下标从0开始的
        setBit(visitKey, visitDTO.getUserId() - 1);
    }

    private void setBit(String visitKey, int userId) {
        if (!redisTemplate.opsForValue().setBit(visitKey, userId, true)) {
            System.out.println("新增访问用户ID：" + userId);
        }
    }

}
