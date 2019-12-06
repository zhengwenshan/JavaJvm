package com.zws.jvm.classloader.loader.spi;

/**
 * @author zhengws
 * @date 2019-09-30 17:41
 */
public class PrintParm {
    public static void main(String[] args) {
        System.out.println(PrintParm.class.getClassLoader());
        System.out.println(Thread.currentThread().getContextClassLoader());
        System.out.println(System.getProperty("sun.boot.class.path"));
    }
}
