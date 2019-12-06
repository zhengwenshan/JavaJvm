package com.zws.jvm.classloader.initactiveaccess;

import java.lang.reflect.Constructor;

/**
 * @author zhengws
 * @date 2019-09-25 10:42
 */
public class InitActiveAccessTest {
    static {
        System.out.println("InitActiveAccessTest main class init ....");
    }
    public static void main(String[] args) {
        System.out.println("main call...");
        /**
         * 当虚拟机启动时，执行的主类(main方法所在的类),虚拟机会先初始化这个类
         * 输出：
         * InitActiveAccessTest main class init ....
         * main call...
         */
        newObj();
//        staticFieldAccess();
//        reflectAccess();
//        sunInitParent();
//        unSureValue();
    }

    private static void newObj(){
        Parent parent = new Parent();
        Parent parent2 = new Parent();
        /**
         * 首次，new创建对象,类会进行初始化。
         * 输出:
         * Parent init ...
         */
    }

    private static void staticFieldAccess(){
        System.out.println(Parent.staticStr);
        Parent.staticStr = "change str";
        /**
         * 首次, 访问类的静态成员(getstatic, putstatic指令),会对类进行初始化 final除外
         *
         * 输出:
         * Parent init ...
         * parent static str
         */
    }

    private static void reflectAccess(){
        System.out.println(Parent.class);

        /**
         * System.out.println("##################");
         *         try {
         *             Class<?> clazz = Class.forName("com.zws.jvm.classloader.initactiveaccess.Parent");
         *             System.out.println(clazz);
         *         } catch (ClassNotFoundException e) {
         *             e.printStackTrace();
         *         }
         */
        System.out.println("##################");
        try {
            Class<?> clazz = Class.forName("com.zws.jvm.classloader.initactiveaccess.Parent", false, ClassLoader.getSystemClassLoader());
            System.out.println(clazz);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        /**
         * Class.forName 进行类加载，并指定不进行初始化及类加载器
         *
         * 输出:
         * class com.zws.jvm.classloader.initactiveaccess.Parent
         * ##################
         * class com.zws.jvm.classloader.initactiveaccess.Parent
         */


        /**
         * 使用java.lang.reflect包的方法对类进行反射调用的时候
         *
         * 输出：
         * class com.zws.jvm.classloader.initactiveaccess.Parent  //直接访问类对象，为被动访问，并不会触发类的初始化
         * ##################
         * Parent init ...   //首次，通过反射的newInstance创建对象时，进行类初始化。
         * ##################   //因为类已经初始化过，因此不会再次初始化.
         * class com.zws.jvm.classloader.initactiveaccess.Parent
         */
    }

    private static void sunInitParent(){
        Sun sun = new Sun();

        /**
         * 当初始化一个类的时候，如果发现其父类还没有进行过初始化，则需要先触发其父类的初始化
         *
         * 输出:
         * Parent init ...
         * Sun init ...
         */
    }

    private static void unSureValue(){
        System.out.println(Sun.finalStaticStr);
        /**
         * 当一个常量的值并非编译期间可以确定的，那么其值就不会被放到调用类的常量池中，
         * 这时在程序运行时，会导致主动使用 这个常量所在的类，会导致这个类被初始化。
         *
         * 输出：
         * Parent init ...
         * c332ed19-572d-4d73-ae9b-54dce590cd53
         */
    }
}
