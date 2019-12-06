package com.zws.jvm.classloader.loader.spi.design;

import sun.reflect.CallerSensitive;
import sun.reflect.Reflection;

/**
 * @author zhengws
 * @date 2019-10-05 16:06
 */
public class CallerTest {
    @CallerSensitive
    public static void printCaller(){
        Class<?> caller = Reflection.getCallerClass();
        System.out.println(caller);
    }
}
