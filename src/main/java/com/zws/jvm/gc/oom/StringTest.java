package com.zws.jvm.gc.oom;

/**
 * @author zhengws
 * @date 2019-10-21 15:38
 */
public class StringTest {
    public static void main(String[] args) {
        StringBuilder sb1 = new StringBuilder("计算机").append("软件");
        String str1 = sb1.toString();
        System.out.println(str1.intern() == str1);

        StringBuilder sb2 = new StringBuilder("jav").append("a");
        String str2 = sb2.toString();
        System.out.println(str2.intern() == str2);

        /**
         * 输出:
         * true // jdk7 的intern() 实现不会再复制实例，只是在常量池中记录"首次出现"的实例引用
         * false //因为“java”这个字符串在执行StringBuilder.toString（）之前已经出现过，字符串常量池中已经有它的引用了，不符合“首次出现”的原则
         */
    }
}
