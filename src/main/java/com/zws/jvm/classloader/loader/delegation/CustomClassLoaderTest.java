package com.zws.jvm.classloader.loader.delegation;

/**
 * @author zhengws
 * @date 2019-09-27 17:31
 */
public class CustomClassLoaderTest {
    public static void main(String[] args) throws Exception {
        CustomClassLoader classLoader = new CustomClassLoader("loader1");
        classLoader.setPath("/Users/zhengws/Desktop/");
        Class<?> clazz = classLoader.loadClass("com.zws.jvm.classloader.loader.delegation.Sun");
        System.out.println("####################");
        System.out.println(clazz.getClassLoader());

        Class<?> parent = classLoader.loadClass("com.zws.jvm.classloader.loader.delegation.Parent");
        System.out.println(parent.getClassLoader());

        /**
         * 测试一
         * 条件: 未删除class文件时
         * 输出：
         * ####################
         * sun.misc.Launcher$AppClassLoader@18b4aac2
         * sun.misc.Launcher$AppClassLoader@18b4aac2
         *
         *
         * 测试二
         * 条件：将target底下的 Sun,Parent class文件删除时
         * 输出:
         * loader1 findClass execute, the class is: com.zws.jvm.classloader.loader.delegation.Sun
         * /Users/zhengws/Desktop/com/zws/jvm/classloader/loader/delegation/Sun.class
         * loader1 findClass execute, the class is: com.zws.jvm.classloader.loader.delegation.Parent
         * /Users/zhengws/Desktop/com/zws/jvm/classloader/loader/delegation/Parent.class
         * ####################
         * com.zws.jvm.classloader.loader.delegation.CustomClassLoader@610455d6
         * com.zws.jvm.classloader.loader.delegation.CustomClassLoader@610455d6
         *
         * 说明：
         * 1、自定义类加载器，默认情况下父加载器为应用类加载器，默认情况下掉super(),也就是ClassLoader()
         * Creates a new class loader using the ClassLoader returned by the method getSystemClassLoader() as the parent class loader
         * protected ClassLoader() {
         *         this(checkCreateClassLoader(), getSystemClassLoader());
         *     }
         * 2、基于双亲委托机制，如果一个类加载器收到了类加载的请求，它首先不会自己去尝试加载这个类，而是把这个请求委派给父类加载器去完成，
         *    在未删除class path下class文件时，由于应用类加载器，可以直接找到该class文件，因此直接加载，而不是通过自定义加载器进行加载
         *    在删除class path下的class文件时，由于应用类加载器加载不到，因此依次往下传递，给用户自定义加载器进行加载。
         */
    }
}
