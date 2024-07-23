package com.panther.bitmapSign;

import org.springframework.data.redis.connection.BitFieldSubCommands;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(value = "/signIn")
public class SignTest {

    @Resource
    private RedisTemplate<String, String> redisTemplate;

    public final static String USER_DAY_SIGN_IN = "daySignIn:%s:%d";

    /**
     * 签到，可任意选择一天签到
     * <p>
     * 在此基础上可实现日签、补签功能。
     */
    @RequestMapping("/daySignIn")
    public void daySignIn(@RequestBody DaySignDTO daySignDTO) {
        String signKey = String.format(USER_DAY_SIGN_IN, String.valueOf(daySignDTO.getYear()) + daySignDTO.getMonth(), daySignDTO.getUserId());
        // 下标从0开始的
        signIn(signKey, daySignDTO.getDay() - 1);

    }

    /**
     * 查询指定年、月签到日历表
     */
    @RequestMapping("/querySignCalendar")
    public void querySignCalendar(@RequestBody DaySignDTO daySignDTO) {
        String signKey = String.format(USER_DAY_SIGN_IN, String.valueOf(daySignDTO.getYear()) + daySignDTO.getMonth(), daySignDTO.getUserId());
        LocalDate date = LocalDate.of(daySignDTO.getYear(), daySignDTO.getMonth(), 1);
        int lengthOfMonth = date.lengthOfMonth();
        System.out.println(getSignCalendarFromRedis(signKey, lengthOfMonth));
    }

    /**
     * 查询某个用户连续签到天数
     */
    @RequestMapping("/getContinuousSignInDays")
    public void getContinuousSignInDays(@RequestBody DaySignDTO daySignDTO) {
        String signKey = String.format(USER_DAY_SIGN_IN, String.valueOf(daySignDTO.getYear()) + daySignDTO.getMonth(), daySignDTO.getUserId());
        System.out.println("userId: " + daySignDTO.getUserId() + "，最近连续签到：" + getSignCountFromRedis(signKey, LocalDate.now().getDayOfMonth()) + "天");
    }


    private void signIn(String signKey, int lengthOfMonth) {
        Boolean suc = redisTemplate.opsForValue().setBit(signKey, lengthOfMonth, true);
        if (suc == null) {
            System.out.println("异常处理");
            return;
        }
        if (!suc) {
            System.out.println(signKey + "【" + (lengthOfMonth + 1) + "号，签到成功！】");
        } else {
            System.out.println(signKey + "【" + (lengthOfMonth + 1) + "号，重复签到！】");
        }
    }

    private String getSignCalendarFromRedis(String key, int dayOfMonth) {
        BitFieldSubCommands bitFieldSubCommands = BitFieldSubCommands
                .create()
                .get(BitFieldSubCommands.BitFieldType.unsigned(dayOfMonth))
                .valueAt(0);

        List<Long> list = redisTemplate.opsForValue().bitField(key, bitFieldSubCommands);
        if (list == null || list.isEmpty()) {
            return "";
        }
        long count = list.get(0) == null ? 0 : list.get(0);

        StringBuilder sb = new StringBuilder();
        sb.append(key).append(" 签到日历").append("\n");
        for (int i = dayOfMonth; i > 0; i--) {
            // --- 仅仅为了格式化，无业务含义 【开始】
            if (i < 10) {
                sb.append("0");
            }
            // --- 仅仅为了格式化，无业务含义 【结束】
            sb.append(i);
            if ((count & 1) == 1) {
                sb.append("签了");
            } else {
                sb.append("未签");
            }
            count >>= 1;
            // --- 仅仅为了格式化，无业务含义 【开始】
            sb.append("    ");
            if ((i - 1) % 7 == 0) {
                sb.append("\n");
            }
            // --- 仅仅为了格式化，无业务含义 【结束】
        }

        return sb.toString();

    }

    public int getSignCountFromRedis(String key, int dayOfMonth) {
        // 表示从下标0开始，一次性获取到dayOfMonth范围内的数据，比如当月有31天，那就等于下标的取值范围是【0~30】，因为bitmap的位数不会超过当月天数，所以，实际上就是把所有位上的值都统计出来。
        BitFieldSubCommands bitFieldSubCommands = BitFieldSubCommands
                .create()
                .get(BitFieldSubCommands.BitFieldType.unsigned(dayOfMonth))
                .valueAt(0);

        // redisTemplate提供的通过pipeline的方式，调用redis的BITFIELD key offset get命名，获取对应的值（转换成十进制）
        List<Long> list = redisTemplate.opsForValue().bitField(key, bitFieldSubCommands);
        if (list == null || list.isEmpty()) {
            return 0;
        }
        int signCount = 0;
        long count = list.get(0) == null ? 0 : list.get(0);

        for (int i = 0; i < dayOfMonth; i++) {
            // 如果(count & 1) == 1条件成立，则表示当前二进制串中，最后一位是1，业务上即表示【已签到】，否则就代表断签了
            if ((count & 1) == 1) {
                signCount++;
            } else {
                break;
            }
            // 移除最后一位，再继续检查当前最后一位是不是为1，以此循环遍历。
            count >>= 1;
        }
        return signCount;
    }

}
