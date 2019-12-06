package com.zws.jvm.classloader.passiveaccess;

/**
 * @author zhengws
 * @date 2019-09-22 09:19
 */
public class ClassPassiveInit3 {
    public static void main(String[] args) {
        System.out.println(ConstClass.NAME);
        System.out.println(ConstClass.ONE);
        System.out.println(ConstClass.FIVE);
        System.out.println(ConstClass.SIX);
        System.out.println(ConstClass.A);
        System.out.println(ConstClass.ONETOWEIGHT);
        System.out.println(ConstClass.BIG);
        System.out.println(ConstClass.HEIGHT);
        /**
         * 输出：
         * name
         * 1
         * 5
         * 6
         * a
         * 128
         * 32769
         * 173.5
         *
         * 反编译：
         * public static void main(java.lang.String[]);
         *     Code:
         *        0: getstatic     #2                  // Field java/lang/System.out:Ljava/io/PrintStream;
         *        3: ldc           #4                  // String name
         *        5: invokevirtual #5                  // Method java/io/PrintStream.println:(Ljava/lang/String;)V
         *        8: getstatic     #2                  // Field java/lang/System.out:Ljava/io/PrintStream;
         *       11: iconst_1
         *       12: invokevirtual #6                  // Method java/io/PrintStream.println:(I)V
         *       15: getstatic     #2                  // Field java/lang/System.out:Ljava/io/PrintStream;
         *       18: iconst_5
         *       19: invokevirtual #6                  // Method java/io/PrintStream.println:(I)V
         *       22: getstatic     #2                  // Field java/lang/System.out:Ljava/io/PrintStream;
         *       25: bipush        6
         *       27: invokevirtual #6                  // Method java/io/PrintStream.println:(I)V
         *       30: getstatic     #2                  // Field java/lang/System.out:Ljava/io/PrintStream;
         *       33: bipush        97
         *       35: invokevirtual #7                  // Method java/io/PrintStream.println:(C)V
         *       38: getstatic     #2                  // Field java/lang/System.out:Ljava/io/PrintStream;
         *       41: sipush        128
         *       44: invokevirtual #6                  // Method java/io/PrintStream.println:(I)V
         *       47: getstatic     #2                  // Field java/lang/System.out:Ljava/io/PrintStream;
         *       50: ldc           #8                  // int 32769
         *       52: invokevirtual #6                  // Method java/io/PrintStream.println:(I)V
         *       55: getstatic     #2                  // Field java/lang/System.out:Ljava/io/PrintStream;
         *       58: ldc           #9                  // float 173.5f
         *       60: invokevirtual #10                 // Method java/io/PrintStream.println:(F)V
         *       63: return
         *
         * 说明：
         *    1.常量在编译阶段会存入调用类的常量池中，本质上并没有直接引用到定义常量的类,并不会导致定义常量类的初始化
         *
         * 注记符：(优先顺序，从上往下匹配)
         *    1.iconst_1 表示将int类型1推送至栈顶(iconst_1 ~ iconst_5)
         *    2.bipush 表示将单字节(-128 ~ 127) 推送至栈顶
         *    3.sipush 表示将一个短整型常量(-32768 ~ 32767) 推送至栈顶
         *    4.ldc 标识将int,float 或是string类型的常量值从常量池推送至栈顶
         */
    }
}
