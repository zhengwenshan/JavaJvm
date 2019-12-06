package com.zws.jvm.classloader.loader.delegation;

/**
 * @author zhengws
 * @date 2019-09-28 10:39
 */
public class ArraysClassLoader {
    public static void main(String[] args) {
        String[] strings = new String[2];
        System.out.println(strings.getClass().getClassLoader());

        System.out.println("###############");
        ArraysClassLoader[] loaders = new ArraysClassLoader[2];
        System.out.println(loaders.getClass().getClassLoader());

        System.out.println("###############");
        int[] ints = new int[2];
        System.out.println(ints.getClass().getClassLoader());

        /**
         * 说明：
         * 第一个null代表String是有启动类加载器加载的， 而最后一个null，则代表原生类型的数组，并没有类加载器。
         *
         * 输出结果：
         * null
         * ###############
         * sun.misc.Launcher$AppClassLoader@18b4aac2
         * ###############
         * null
         */
    }
}
