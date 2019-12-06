package com.zws.jvm.bytes;

/**
 * @author zhengws
 * @date 2019-10-09 19:49
 */
public class StructTest {
    private String str = "Hello World";

    public StructTest() {
        System.out.println(this.str);
    }

    public static void main(String[] args) {
        StructTest test = new StructTest();
    }
}
