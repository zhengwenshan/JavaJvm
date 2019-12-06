package com.zws.jvm.classloader.loader.delegation;

/**
 * @author zhengws
 * @date 2019-09-27 17:31
 */
public class Sun extends Parent {
    public static String str = "Sun Str";
    static {
        System.out.println("Sun init ...");
    }

    public Sun() {
//        System.out.println(MySample.class);
    }
}
