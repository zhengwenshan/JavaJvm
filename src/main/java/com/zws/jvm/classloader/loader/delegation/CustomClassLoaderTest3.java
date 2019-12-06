package com.zws.jvm.classloader.loader.delegation;

/**
 * @author zhengws
 * @date 2019-09-28 15:26
 */
public class CustomClassLoaderTest3 {
    public static void main(String[] args) throws Exception{
        CustomClassLoader classLoader = new CustomClassLoader("loader1");
        classLoader.setPath("/Users/zhengws/Desktop/");
        Class<?> clazz1 = classLoader.loadClass("com.zws.jvm.classloader.loader.delegation.MySample");
        System.out.println(clazz1.hashCode());
        System.out.println("################");
        Class<?> clazz2 = classLoader.loadClass("com.zws.jvm.classloader.loader.delegation.MySample");
        System.out.println(clazz2.hashCode());

        System.out.println(clazz1 == clazz2);

        /**
         * 说明：
         *  采用同一个类加载器，加载两个相同全限定名的.class文件，只会加载一次.
         *  也就是 同一个命名空间中，不会出现两个全限定名相同的类
         *
         * 输出结果：
         * loader1 findClass execute, the class is: com.zws.jvm.classloader.loader.delegation.MySample
         * 1625635731
         * ################
         * 1625635731
         * true
         */
    }
}
