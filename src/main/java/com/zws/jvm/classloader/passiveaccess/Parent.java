package com.zws.jvm.classloader.passiveaccess;

import java.util.Random;

/**
 * @author zhengws
 * @date 2019-09-22 09:03
 */
public class Parent {
    public static int parentVal = 10;
    public static int parentVal2 = new Random().nextInt(10);

    static {
        System.out.println("Parent class init...");
    }
}
