package com.zws.jvm.classloader.passiveaccess;

/**
 * @author zhengws
 * @date 2019-09-25 13:47
 */
public class ClassPassiveInit4 {
    public static void main(String[] args) throws Exception {
        ClassLoader classLoader = ClassLoader.getSystemClassLoader();
        Class<?> clazz = classLoader.loadClass("com.zws.jvm.classloader.passiveaccess.Sun");
//        System.out.println(clazz);
//        System.out.println("################");
        clazz = Class.forName("com.zws.jvm.classloader.passiveaccess.Sun", false, classLoader);
        System.out.println(clazz);

        /**
         * 说明:
         *  手动对class进行加载并不会导致，类进行初始化，也属于被动访问
         * 输出:
         * class com.zws.jvm.classloader.passiveaccess.Sun
         * ################
         * Parent class init...
         * Sun init ...
         * class com.zws.jvm.classloader.passiveaccess.Sun
         */
    }
}
