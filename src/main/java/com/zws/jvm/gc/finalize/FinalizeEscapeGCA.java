package com.zws.jvm.gc.finalize;

/**
 * @author zhengws
 * @date 2019-08-22 16:58
 */
public class FinalizeEscapeGCA {
    public static FinalizeEscapeGCA SAVE_HOOK=null;

    public void isAlive(){
        System.out.println("yes,i am still alive");
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("finalize mehtod executed");
        SAVE_HOOK = this;
    }

    public static void main(String[] args) throws InterruptedException {
        //第一次拯救自己
        SAVE_HOOK = new FinalizeEscapeGCA();
        SAVE_HOOK = null;
        System.gc();

        Thread.sleep(1000);
        if (SAVE_HOOK != null){
            SAVE_HOOK.isAlive();
        }else {
            System.out.println("first, no i am dead.");
        }

        //第二次拯救自己
        SAVE_HOOK = null;
        System.gc();

        Thread.sleep(1000);
        if (SAVE_HOOK != null){
            SAVE_HOOK.isAlive();
        }else {
            System.out.println("second, no i am dead.");
        }
    }
}
