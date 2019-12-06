package com.zws.jvm.bytes.execption;

/**
 * @author zhengws
 * @date 2019-10-14 23:05
 */
public class ExecptionTest {
    public static void main(String[] args) {
        int num = getNum();
        System.out.println(num);
    }

    public static int getNum() {
        int x;
        try {
            x = 10;
            return x;
        } catch (Exception e) {
            x = 20;
        } finally {
            x = 30;
            System.out.println("##########");
        }
        return x;
    }
}
