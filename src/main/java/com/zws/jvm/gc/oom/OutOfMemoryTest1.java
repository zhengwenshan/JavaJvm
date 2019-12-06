package com.zws.jvm.gc.oom;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhengws
 * @date 2019-10-21 14:16
 */
public class OutOfMemoryTest1 {
    public static void main(String[] args) {
        List<Inner> inners = new ArrayList<Inner>();
        for (int i = 0; i < 30; i++) {
            inners.add(new Inner());
        }
        System.out.println(inners.size());
    }

    private static class Inner{
        private byte[] bytes = new byte[1024 * 1024]; //1M
    }
}
