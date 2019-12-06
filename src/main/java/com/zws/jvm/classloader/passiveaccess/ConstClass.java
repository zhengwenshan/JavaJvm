package com.zws.jvm.classloader.passiveaccess;

/**
 * @author zhengws
 * @date 2019-09-22 09:18
 */
public class ConstClass {
    static {
        System.out.println("ConstClass init ...");
    }
    public static final String NAME = "name";
    public static final int ONE = 1;
    public static final int FIVE = 5;
    public static final int SIX = 6;
    public static final int ONETOWEIGHT = 128;
    public static final int BIG = 32769;
    public static final char A = 'a';
    public static final float HEIGHT = 173.5f;
}
