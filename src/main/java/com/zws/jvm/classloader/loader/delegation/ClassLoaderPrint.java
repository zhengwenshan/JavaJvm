package com.zws.jvm.classloader.loader.delegation;

/**
 * @author zhengws
 * @date 2019-09-26 17:20
 */
public class ClassLoaderPrint {
    public static void main(String[] args) {
        ClassLoader classLoader = null;
        do {
            if (classLoader == null){
                classLoader = Thread.currentThread().getContextClassLoader();
                System.out.println(classLoader);
            }
            classLoader = classLoader.getParent();
            System.out.println(classLoader);
        } while (classLoader != null);
    }
    /**
     * 输出:
     * sun.misc.Launcher$AppClassLoader@18b4aac2
     * sun.misc.Launcher$ExtClassLoader@61bbe9ba
     * null
     *
     * 说明:
     * 从输出结果看，并没有获取到ExtClassLoader的父Loader，
     * 原因是Bootstrap Loader（引导类加载器）是用C语言实现的，找不到一个确定的返回父Loader的方式，于是就返回null
     */
}
