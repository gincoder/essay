package com.panther.test;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONUtil;
import com.google.gson.Gson;
import org.joor.Reflect;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * scheme
 *
 * @author panther
 * @version 1.0: TestJoor.java, 2024/6/18 10:20 $
 */
public class TestJoor {

    public static void main(String[] args) {

        Reflect reflect = Reflect.compile(
                "com.example.Solution",
                "package com.example;\n" +
                        "import java.util.*;\n" +
                        "class Solution {\n" +
                        "    public int maxDiff(int[] nums) {\n" +
                        "        \n" +
                        "        if(nums == null || nums.length == 0){\n" +
                        "            return 0;\n" +
                        "        }\n" +
                        "        \n" +
                        "        int min = nums[0];\n" +
                        "        int ans = 0;\n" +
                        "        for (int i = 1 ;  i < nums.length; i++){\n" +
                        "            if(nums[i] < min) {\n" +
                        "                min = nums[i];   \n" +
                        "            }\n" +
                        "            ans = Math.max(ans , nums[i] - min);\n" +
                        "        }\n" +
                        "        return ans;\n" +
                        "    }\n" +
                        "}")
                .create();
        List<Integer> output = new ArrayList<>();
        List<Integer> answer = new ArrayList<>();
        System.out.println();
    }

    @SuppressWarnings("unchecked")
    public static <T, U> U convertToPrimitiveArray(T[] array, Class<U> primitiveType) {
        if (array == null || !primitiveType.isPrimitive()) {
            throw new IllegalArgumentException("The provided target type is not a primitive type.");
        }
        int length = array.length;
        U primitiveArray = (U) Array.newInstance(primitiveType, length);

        for (int i = 0; i < length; i++) {
            Array.set(primitiveArray, i, Array.get(array, i));
        }

        return primitiveArray;
    }

    public static <T> T gsonParseJson(String jsonStr, Class<T> clazz) {
        Gson gson = new Gson();
        return gson.fromJson(jsonStr, clazz);
    }


    public static <T> T[] parseJson(String jsonStr, Class<T> clazz) {
        return clazz.isArray() ? parseArgsArr(jsonStr, clazz) : parseArgsToArr(jsonStr, clazz);
    }

    // 需要单独处理数组情况
    public static <T> T[] parseArgsArr(String jsonStr, Class<T> clazz) {
        JSONArray jsonArr = JSONUtil.parseArray(jsonStr);
        Integer[][] array = (Integer[][]) jsonArr.toArray(Integer[].class);

        System.out.println(Arrays.deepToString(array));

        List<T> list = new ArrayList<>(array.length);
        for (int i = 0; i < array.length; i++) {
            T item = (T) parseArgsToArr(array[i].toString(), clazz.getComponentType());
            list.add(item);
        }
        return (T[]) list.toArray();
    }

    // 核心转换
    public static <U> U[] parseArgsToArr(String jsonStr, Class<U> clazz) {
        JSONArray jsonArr = JSONUtil.parseArray(jsonStr);
        U[] array = (U[]) jsonArr.toArray(clazz);
        return array;
    }
}
