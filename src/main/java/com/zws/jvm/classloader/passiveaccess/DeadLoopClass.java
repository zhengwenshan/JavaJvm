package com.zws.jvm.classloader.passiveaccess;

/**
 * @author zhengws
 * @date 2019-09-22 11:09
 */
public class DeadLoopClass {
    static {
        if (true){
            System.out.println(Thread.currentThread().getName() + "init DeadLoopClass");
            while (true){
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        Runnable script = new Runnable() {
            public void run() {
                System.out.println(Thread.currentThread().getName() + "start.");
                DeadLoopClass loopClass = new DeadLoopClass();
                System.out.println(Thread.currentThread().getName() + "run over.");
            }
        };

        Thread thread1 = new Thread(script);
        Thread thread2 = new Thread(script);
        thread1.start();
        thread2.start();
    }
}
