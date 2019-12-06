package com.zws.jvm.classloader.loader.delegation;

/**
 * @author zhengws
 * @date 2019-09-28 15:26
 */
public class CustomClassLoaderTest4 {
    public static void main(String[] args) throws Exception{
        CustomClassLoader classLoader = new CustomClassLoader("loader1");
        classLoader.setPath("/Users/zhengws/Desktop/");
        Class<?> clazz1 = classLoader.loadClass("com.zws.jvm.classloader.loader.delegation.MySample");
        System.out.println(clazz1.hashCode());
        System.out.println("################");

        CustomClassLoader classLoader2 = new CustomClassLoader("loader2");
        classLoader2.setPath("/Users/zhengws/Desktop/");
        Class<?> clazz2 = classLoader2.loadClass("com.zws.jvm.classloader.loader.delegation.MySample");
        System.out.println(clazz2.hashCode());

        System.out.println(clazz1 == clazz2);

        /**
         * 说明：
         *  采用两个不同的类加载器，分别加载两个相同全限定名的.class文件，两个都能进行加载。
         *  也就是 不同的命名空间中，可以存在两个全限定名相同的类
         *
         * 输出结果：
         * loader1 findClass execute, the class is: com.zws.jvm.classloader.loader.delegation.MySample
         * 1625635731
         * ################
         * loader2 findClass execute, the class is: com.zws.jvm.classloader.loader.delegation.MySample
         * 644117698
         * false
         */
    }
}
