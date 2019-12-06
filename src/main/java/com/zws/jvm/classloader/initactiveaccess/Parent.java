package com.zws.jvm.classloader.initactiveaccess;

import java.util.UUID;

/**
 * @author zhengws
 * @date 2019-09-25 10:40
 */
public class Parent {
    public String str = "parent str";

    public static String staticStr = "parent static str";

    public static final String finalStaticStr = UUID.randomUUID().toString();

    static {
        System.out.println("Parent init ...");
    }
}
