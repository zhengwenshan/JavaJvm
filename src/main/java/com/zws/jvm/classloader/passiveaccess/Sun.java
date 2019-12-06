package com.zws.jvm.classloader.passiveaccess;

/**
 * @author zhengws
 * @date 2019-09-22 09:03
 */
public class Sun extends Parent{
    static {
        System.out.println("Sun init ...");
    }
}
