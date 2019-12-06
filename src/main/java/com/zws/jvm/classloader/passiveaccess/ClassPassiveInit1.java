package com.zws.jvm.classloader.passiveaccess;

/**
 * @author zhengws
 * @date 2019-09-21 08:13
 */
public class ClassPassiveInit1 {
    public static void main(String[] args) {
        System.out.println(Sun.parentVal);
        System.out.println(Sun.parentVal2);
        /**
         * 通过子类引用父类的静态字段，并不会导致子类的初始化(子类的被动访问)
         * 输出:
         * Parent class init...
         * 10
         * 7
         */
    }
}


