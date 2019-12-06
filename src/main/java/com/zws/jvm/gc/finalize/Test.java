package com.zws.jvm.gc.finalize;

/**
 * @author zhengws
 * @date 2019-08-22 17:19
 */
public class Test {
    public static void main(String[] args) throws InterruptedException {
        FinalizeEscapeGc ref  = new FinalizeEscapeGc();
        System.out.println(ref);
        ref = null;
        System.gc();
        Thread.sleep(1000);
        if (FinalizeEscapeGc.ref != null) {
            System.out.println(FinalizeEscapeGc.ref);
            FinalizeEscapeGc.ref.isAlive();
        } else {
            System.out.println("first, i am dead.");
        }

        FinalizeEscapeGc.ref = null;
        System.gc();
        Thread.sleep(1000);
        if (FinalizeEscapeGc.ref != null) {
            FinalizeEscapeGc.ref.isAlive();
        } else {
            System.out.println("second, i am dead.");
        }
    }
}
