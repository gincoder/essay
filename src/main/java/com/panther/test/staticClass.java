package com.panther.test;

/**
 * scheme
 *
 * @author panther
 * @version 1.0: staticClass.java, 2024/7/17 15:51 $
 */
public class staticClass {

    static {
        System.out.println("outer static");
    }

    staticClass(){
        System.out.println("outer class construct");
    }

    static class innerClass{
        static {
            System.out.println("inner static");
        }

        innerClass(){
            System.out.println("inner class construct");
        }

    }

    public static void main(String[] args) {
        new innerClass();
    }

}
