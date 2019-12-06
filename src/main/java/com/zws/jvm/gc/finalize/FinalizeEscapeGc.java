package com.zws.jvm.gc.finalize;

/**
 * @author zhengws
 * @date 2019-08-22 17:15
 */
public class FinalizeEscapeGc {
    public static FinalizeEscapeGc ref;

    public void isAlive(){
        System.out.println("yes,i am still alive");
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        ref = this;
        System.out.println("GcA finalize mehtod executed");
    }
}
