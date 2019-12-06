package com.zws.jvm.gc.method;

/**
 * @author zhengws
 * @date 2019-08-22 18:01
 */
public class MethodAreaStaicProperties {
    public static MethodAreaStaicProperties m;
    public static final MethodAreaStaicProperties p = new MethodAreaStaicProperties();

    public MethodAreaStaicProperties() {
    }

    public static void main(String[] args) {
        MethodAreaStaicProperties s = new MethodAreaStaicProperties();
        s.m = new MethodAreaStaicProperties();
        //虚拟机栈（栈帧中的本地变量表）中引用的对象
        s = null;
        System.out.println(s);
        //方法区中类静态属性引用的对象
        System.out.println(MethodAreaStaicProperties.m);
        //方法区中常量引用的对象
        System.out.println(MethodAreaStaicProperties.p);
    }
}
