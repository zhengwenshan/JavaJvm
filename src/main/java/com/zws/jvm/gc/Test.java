package com.zws.jvm.gc;

import java.util.ArrayList;

/**
 * @author zhengws
 * @date 2019-10-15 19:50
 */
public class Test {
    byte[] bytes = new byte[100 * 1024];

    public static void main(String[] args) throws Throwable {
        ArrayList<Test> list = new ArrayList<Test>();
        while (true){
            list.add(new Test());
            Thread.sleep(100);
        }
    }
}
