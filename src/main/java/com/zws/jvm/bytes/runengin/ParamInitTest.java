package com.zws.jvm.bytes.runengin;

/**
 * @author zhengws
 * @date 2019-10-15 13:50
 */
public class ParamInitTest {
    public static void main(String[] args) {
        /**
         * 验证局部变量，是否会进行对变量进行赋予默认初始化值
         *
         * 编译现象：
         * Error:(13, 28) java: 可能尚未初始化变量a
         *
         * 结论：
         * 个局部变量定义了但没有赋初始值是不能使用的。
         */
        int a;
//        System.out.println(a);
    }
}
