package com.zws.jvm.classloader.passiveaccess;

/**
 * @author zhengws
 * @date 2019-09-22 09:07
 */
public class ClassPassiveInit2 {
    public static void main(String[] args) {
        Parent[] parents = new Parent[10];
        int[] ints = new int[20];
        System.out.println(parents.getClass().getName());
        /**
         * 输出:
         * [Lcom.zws.jvm.classloader.Parent;
         *
         * 说明：
         * 创建数组类，并不会导致元素类的初始化，也就是并不会进行元素类的主动访问
         * 而是，由虚拟机自动生成的、直接继承于java.lang.Object的子类([Lcom.zws.jvm.classloader.Parent)
         * 如果元素类为引用类型，则虚拟机会调用anewarrary指令进行创建
         * 如果元素类型为原始类型，则虚拟机会调用newarrary指令进行创建
         *
         * 反编译如下：
         * public static void main(java.lang.String[]);
         *     Code:
         *        0: bipush        10
         *        2: anewarray     #2                  // class com/zws/jvm/classloader/Parent
         *        5: astore_1
         *        6: bipush        20
         *        8: newarray       int
         *       10: astore_2
         *       11: getstatic     #3                  // Field java/lang/System.out:Ljava/io/PrintStream;
         *       14: aload_1
         *       15: invokevirtual #4                  // Method java/lang/Object.getClass:()Ljava/lang/Class;
         *       18: invokevirtual #5                  // Method java/lang/Class.getName:()Ljava/lang/String;
         *       21: invokevirtual #6                  // Method java/io/PrintStream.println:(Ljava/lang/String;)V
         *       24: return
         */
    }
}
