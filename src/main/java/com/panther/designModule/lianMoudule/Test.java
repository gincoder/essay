package com.panther.designModule.lianMoudule;

import java.util.Optional;

/**
 * scheme
 *
 * @author panther
 * @version 1.0: Test.java, 2024/6/25 16:33 $
 */
public class Test {

    public static void main(String[] args) {
    }

    // 迭代法 求 平方根
    public static double sqrt(int x) {

        double t = 1;
        double t1 = x - (t * t - x) / (2 * t);
        while (t - t1 > 0.00000001 || t - t1 < -0.00000001) {
            t = t1;
            t1 = t - (t * t - x) / (2 * t);
        }
        return t1;
    }

}
