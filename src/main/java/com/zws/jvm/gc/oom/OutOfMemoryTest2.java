package com.zws.jvm.gc.oom;

/**
 * 虚拟机参数:
 * -verbose:gc -Xms20M -Xmx20M -Xmn10M -Xss160k -XX:+PrintGCDetails -XX:SurvivorRatio=8 -XX:+HeapDumpOnOutOfMemoryError
 * @author zhengws
 * @date 2019-10-21 14:16
 */
public class OutOfMemoryTest2 {
    public static int count = 0;

    public static ThreadLocal<Integer> local = new ThreadLocal<Integer>();

    public static void main(String[] args) {
//        try {
//            createBytes(1);
//        } catch (Throwable e) {
//            System.out.println("stack length: " + count);
//            System.out.println(e);
//        }


        try {
            while (true){
                new Thread(new ThreadTest()).start();
                count++;
                System.out.println("线程数: " + count);
            }
        }catch (Throwable e){
            System.out.println("线程数: " + count);
            e.printStackTrace();
        }


        /**
         * 调整方法局部变量个数，当局部变量表个数增大时，栈深度减少，说明：局部变量表内容越多，栈帧越大，栈深度越小
         */
    }

    private static class ThreadTest implements Runnable{
        @Override
        public void run() {
            try {
                local.set(0);
                createBytes(1);
            } catch (Throwable e) {
//                System.out.println(Thread.currentThread().getName() + " stack length: " + local.get());
//                System.out.println(e);
            }
        }
    }

    private static void createBytes(int n) {
        Integer integer = local.get();
        local.set(integer + 1);
        int a = 0;
        int b = 2;
        int c = 4;
        Inner in = new Inner();
        if (n > 0) {
            createBytes(++n);
            System.out.println(in);
        }
    }

    private static class Inner{

    }
}
