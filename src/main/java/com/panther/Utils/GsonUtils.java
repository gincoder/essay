package com.panther.Utils;

import com.google.gson.Gson;
import java.lang.reflect.Type;
import java.util.Arrays;

/**
 * scheme
 *
 * @author panther
 * @version 1.0: Gsonutils.java, 2024/6/26 14:06 $
 */
public class GsonUtils {
    public static void main(String[] args) {
        String json1 = "[[9,11,8,5],[7,12,16,14]]";
        // 使用Gson来解析嵌套数组
        Integer[][] integers = parseJson(json1, Integer[][].class);
        System.out.println(Arrays.deepToString(integers));
    }

    public static <T> T parseJson(String jsonStr, Class<T> clazz) {
        Gson gson = new Gson();
        return gson.fromJson(jsonStr, clazz);
    }

    // 另一个泛型处理方法，用于处理更复杂的类型，例如List中的List等
    public static <T> T parseJson(String jsonStr, Type typeOfT) {
        Gson gson = new Gson();
        return gson.fromJson(jsonStr, typeOfT);
    }

}
