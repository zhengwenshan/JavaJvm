package com.zws.jvm.classloader.initactiveaccess;

/**
 * @author zhengws
 * @date 2019-09-25 10:41
 */
public class Sun extends Parent {
    public String str = "Sun str";
    static {
        System.out.println("Sun init ..." );
    }
}
