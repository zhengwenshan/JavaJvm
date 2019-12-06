package com.zws.jvm.classloader.loader.delegation;

/**
 * @author zhengws
 * @date 2019-09-27 18:07
 */
public class MySample {
    static {
        System.out.println("MySample init ... ");
        System.out.println("MySample is loaded by : " + MySample.class.getClassLoader());
    }
    public MySample() {
        new Cat();
    }
}
