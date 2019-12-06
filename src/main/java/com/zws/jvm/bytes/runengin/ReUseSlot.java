package com.zws.jvm.bytes.runengin;

/**
 * @author zhengws
 * @date 2019-10-15 11:06
 */
public class ReUseSlot {
    public static void main(String[] args) {
//        test();
//        test2();
        test3();
    }

    private static void test() {
        byte[] bytes = new byte[64 * 1024 * 1024];
        System.gc();

        /**
         * 1、输出： 加虚拟机参数 -verbose:gc
         * [GC (System.gc())  69468K->66104K(251392K), 0.0015888 secs]
         * [Full GC (System.gc())  66104K->65948K(251392K), 0.0053788 secs]
         *
         * 2、反编译如下：
         * private static void test();
         *     descriptor: ()V
         *     flags: ACC_PRIVATE, ACC_STATIC
         *     Code:
         *       stack=1, locals=1, args_size=0
         *          0: ldc           #3                  // int 67108864
         *          2: newarray       byte
         *          4: astore_0
         *          5: invokestatic  #4                  // Method java/lang/System.gc:()V
         *          8: return
         *       LineNumberTable:
         *         line 13: 0
         *         line 14: 5
         *         line 21: 8
         *       LocalVariableTable:
         *         Start  Length  Slot  Name   Signature
         *             5       4     0 bytes   [B
         *
         *  3、剖析为何没进行垃圾收集
         *  从局部变量表中，可知，bytes 变量的作用域访问为 [4-8]，也就是直到方法结束,
         *  因此就算手动执行垃圾收集，也无法进行对bytes数据进行收集。
         */
    }

    private static void test2() {
        {
            byte[] bytes = new byte[64 * 1024 * 1024];
            System.out.println(bytes.length);
        }

        System.gc();

        /**
         * 1、输出：
         * [GC (System.gc())  69468K->66136K(251392K), 0.0009393 secs]
         * [Full GC (System.gc())  66136K->65948K(251392K), 0.0048485 secs]
         *
         * 2、反编译输出：
         * private static void test2();
         *     descriptor: ()V
         *     flags: ACC_PRIVATE, ACC_STATIC
         *     Code:
         *       stack=2, locals=1, args_size=0
         *          0: ldc           #3                  // int 67108864
         *          2: newarray       byte
         *          4: astore_0
         *          5: getstatic     #5                  // Field java/lang/System.out:Ljava/io/PrintStream;
         *          8: aload_0
         *          9: arraylength
         *         10: invokevirtual #6                  // Method java/io/PrintStream.println:(I)V
         *         13: invokestatic  #4                  // Method java/lang/System.gc:()V
         *         16: return
         *       LineNumberTable:
         *         line 50: 0
         *         line 51: 5
         *         line 54: 13
         *         line 82: 16
         *       LocalVariableTable:
         *         Start  Length  Slot  Name   Signature
         *             5       8     0 bytes   [B
         *  3、剖析为何没进行垃圾收集
         *  此时局部变量表作用域范围为[4-12],为何还没被进行回收？
         *  因为此时堆栈中，栈帧的局部变量表Slot持有bytes对象的引用,根据可达性分析，bytes对象不处于游离状态，因此依然不能对bytes进行回收.
         */
    }

    private static void test3() {
        {
            byte[] bytes = new byte[64 * 1024 * 1024];
            System.out.println(bytes.length);
        }
        int a = 0;
        System.gc();
        /**
         * 1、输出：
         * [GC (System.gc())  69468K->66136K(251392K), 0.0013260 secs]
         * [Full GC (System.gc())  66136K->412K(251392K), 0.0046542 secs]
         *
         * 2、反编译
         * private static void test3();
         *     descriptor: ()V
         *     flags: ACC_PRIVATE, ACC_STATIC
         *     Code:
         *       stack=2, locals=1, args_size=0
         *          0: ldc           #3                  // int 67108864
         *          2: newarray       byte
         *          4: astore_0
         *          5: getstatic     #5                  // Field java/lang/System.out:Ljava/io/PrintStream;
         *          8: aload_0
         *          9: arraylength
         *         10: invokevirtual #6                  // Method java/io/PrintStream.println:(I)V
         *         13: iconst_0
         *         14: istore_0
         *         15: invokestatic  #4                  // Method java/lang/System.gc:()V
         *         18: return
         *       LineNumberTable:
         *         line 85: 0
         *         line 86: 5
         *         line 88: 13
         *         line 89: 15
         *         line 120: 18
         *       LocalVariableTable:
         *         Start  Length  Slot  Name   Signature
         *             5       8     0 bytes   [B
         *            15       4     0     a   I
         *
         *  3、剖析为何进行垃圾收集？
         *  加了int a = 0;从反编译结果来看，a 局部变量所存储的Slot序号为0，也就是复用了bytes刚刚使用的Slot,因此之前bytes是被移除Slot，
         *  此时没有任何引用指向bytes对象，因此可被系统垃圾收集器进行回收操作。
         */
    }
}
