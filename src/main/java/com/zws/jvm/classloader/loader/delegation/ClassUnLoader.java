package com.zws.jvm.classloader.loader.delegation;

/**
 * @author zhengws
 * @date 2019-09-29 19:31
 */
public class ClassUnLoader {
    public static void main(String[] args) throws Exception{
        CustomClassLoader classLoader1 = new CustomClassLoader("loader1");
        classLoader1.setPath("/Users/zhengws/Desktop/");
        Class<?> clazz1 = classLoader1.loadClass("com.zws.jvm.classloader.loader.delegation.MySample");
        System.out.println(clazz1.hashCode());
        Object instance = clazz1.newInstance();

        instance = null;
        clazz1 = null;
        classLoader1 = null;

        System.out.println("###############");
        //强制进行垃圾回收
        System.gc();
        System.out.println("###############");

        CustomClassLoader classLoader2 = new CustomClassLoader("loader1");
        classLoader2.setPath("/Users/zhengws/Desktop/");
        Class<?> clazz2 = classLoader2.loadClass("com.zws.jvm.classloader.loader.delegation.MySample");
        System.out.println(clazz2.hashCode());
        Object instance1 = clazz2.newInstance();
        Thread.sleep(30000);
        /**
         * 输出：
         * 1625635731
         * MySample is loaded by : com.zws.jvm.classloader.loader.delegation.CustomClassLoader@610455d6
         * ###############
         * [Unloading class com.zws.jvm.classloader.loader.delegation.MySample 0x00000007c0061028]
         * ###############
         * 644117698
         * MySample is loaded by : com.zws.jvm.classloader.loader.delegation.CustomClassLoader@5e2de80c
         */
    }
}
