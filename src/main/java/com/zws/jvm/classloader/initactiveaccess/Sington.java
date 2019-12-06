package com.zws.jvm.classloader.initactiveaccess;

/**
 * @author zhengws
 * @date 2019-09-25 15:19
 */
public class Sington {
    public static int v1 = 10;
    public static Sington sington = new Sington();

    public Sington() {
        System.out.println("v1 = " + v1);
        System.out.println("v2 = " + v2);
        System.out.println("v3 = " + v3);
    }

    public static final int v2 = 20;
    public static int v3 = 30;

}
