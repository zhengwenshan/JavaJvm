package com.zws.jvm.gc.contspool;

/**
 * @author zhengws
 * @date 2019-10-16 11:11
 */
public class RunContsPool {
    public static void main(String[] args) {
        String s1 = "abc";
        String s2 = "abc";
        System.out.println("s1 == s2 : " + (s1 == s2));

        String s3 = new String("abc");
        System.out.println("new String创建,s1 == s3 : " + (s1 == s3));

        s3 = s3.intern();
        System.out.println("调用String的intern()方法,s1 == s3 : " + (s1 == s3));

        /**
         * 输出：
         * s1 == s2 : true
         * new String创建,s1 == s3 : false
         * 调用String的intern()方法,s1 == s3 : true
         *
         * String的intern()方法是将new出来的string对象存储到方法区中，方法区存储String对象，就类似HashSet，有且只有一个相同的字符串。
         */
    }
}
