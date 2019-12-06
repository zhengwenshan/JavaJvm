package com.zws.jvm.classloader.loader.delegation;

/**
 * @author zhengws
 * @date 2019-09-27 18:08
 */
public class Cat {
    static {
        System.out.println("Cat class init ...");
        System.out.println("Cat is loaded by: " + Cat.class.getClassLoader());
    }
}
