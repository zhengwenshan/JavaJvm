package com.zws.jvm.classloader.loader.spi;

import com.zws.jvm.classloader.loader.spi.design.CallerTest;

/**
 * @author zhengws
 * @date 2019-10-05 16:31
 */
public class LoaderTest {
    public static void main(String[] args) {
        CallerTest.printCaller();
        System.out.println(CallerTest.class.getClassLoader());
    }
}
