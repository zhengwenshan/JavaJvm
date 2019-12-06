package com.zws.jvm.classloader.loader.delegation;

/**
 * @author zhengws
 * @date 2019-09-27 17:31
 */
public class Parent {
    public static String str = "Parent Str";

    static {
        System.out.println("Parent init ...");
    }
}
