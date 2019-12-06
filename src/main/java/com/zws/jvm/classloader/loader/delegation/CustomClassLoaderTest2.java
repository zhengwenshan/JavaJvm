package com.zws.jvm.classloader.loader.delegation;

/**
 * @author zhengws
 * @date 2019-09-27 17:31
 */
public class CustomClassLoaderTest2 {
    public static void main(String[] args) throws Exception {
        CustomClassLoader classLoader = new CustomClassLoader("loader1");
        classLoader.setPath("/Users/zhengws/Desktop/");
        Class<?> clazz = classLoader.loadClass("com.zws.jvm.classloader.loader.delegation.MySample");
        Object instance = clazz.newInstance();
        System.out.println(instance.getClass().getClassLoader());
    }
}
