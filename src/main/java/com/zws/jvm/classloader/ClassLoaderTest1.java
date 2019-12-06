package com.zws.jvm.classloader;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author zhengws
 * @date 2019-09-22 15:48
 */
public class ClassLoaderTest1 {
    public static void main(String[] args) throws Exception {
        ClassLoader myClassLoader = new MyClassLoader();
        System.out.println(Thread.currentThread().getContextClassLoader());
        Thread.currentThread().setContextClassLoader(myClassLoader);
        System.out.println(Thread.currentThread().getContextClassLoader());
        System.out.println("=================");
        Object obj = myClassLoader.loadClass("com.zws.jvm.classloader.ClassLoaderTest1").newInstance();
        System.out.println(obj instanceof ClassLoaderTest1);
        System.out.println(obj.getClass().getClassLoader());

        System.out.println("#################");
        ClassLoaderTest1 obj2 = ClassLoaderTest1.class.newInstance();
        System.out.println(obj2.getClass().getClassLoader());
        System.out.println(obj2 instanceof ClassLoaderTest1);

        Class<?> clazz = myClassLoader.loadClass("java.lang.String");
        System.out.println(clazz.getClass().getClassLoader());
        System.out.println(clazz.equals(String.class));
        /**
         * 输出：
         * com.zws.jvm.classloader.ClassLoaderTest1
         * false
         * com.zws.jvm.classloader.ClassLoaderTest1$MyClassLoader@511d50c0
         * #################
         * sun.misc.Launcher$AppClassLoader@18b4aac2
         * true
         *
         * 说明：
         * 对于任意一个类，都需要由加载它的类加载器和这个类本身一同确立其在Java虚拟机中的唯一性
         * 比较两个类是否“相等”，只有在这两个类是由同一个类加载器加载的前提下才有意义
         * 否则，即使这两个类来源于同一个Class文件，被同一个虚拟机加载，只要加载它们的类加载器不同，那这两个类就必定不相等
         */
    }

    public static class MyClassLoader extends ClassLoader{
        @Override
        public Class<?> loadClass(String name) throws ClassNotFoundException {
            String fileName = name.substring(name.lastIndexOf(".") + 1) + ".class";
            InputStream inputStream = getClass().getResourceAsStream(fileName);
            if (inputStream == null){
                return super.loadClass(name);
            }
            try {
                byte[] bytes = new byte[inputStream.available()];
                inputStream.read(bytes);
                return defineClass(name, bytes, 0 , bytes.length);
            } catch (IOException e) {
                throw new ClassNotFoundException(name);
            }
        }
    }
}
