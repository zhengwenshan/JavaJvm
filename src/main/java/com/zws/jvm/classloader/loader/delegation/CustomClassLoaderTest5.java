package com.zws.jvm.classloader.loader.delegation;

/**
 * @author zhengws
 * @date 2019-09-28 15:26
 */
public class CustomClassLoaderTest5 {
    public static void main(String[] args) throws Exception{
        CustomClassLoader classLoader1 = new CustomClassLoader("loader1");
        classLoader1.setPath("/Users/zhengws/Desktop/");
        Class<?> clazz1 = classLoader1.loadClass("com.zws.jvm.classloader.loader.delegation.MySample");
        System.out.println(clazz1.hashCode());
        System.out.println("################");

        CustomClassLoader classLoader2 = new CustomClassLoader(classLoader1,"loader2");
        classLoader2.setPath("/Users/zhengws/Desktop/");
        Class<?> clazz2 = classLoader2.loadClass("com.zws.jvm.classloader.loader.delegation.MySample");
        System.out.println(clazz2.hashCode());

        System.out.println(clazz1 == clazz2);

        /**
         * 说明：
         * 当loader2加载该类时，发现loader1已经加载过了，则会直接返回该Class对象，而不是重新加载
         * 因此说明，命名空间由该类加载器及所有的父类加载器所加载的类组成
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
