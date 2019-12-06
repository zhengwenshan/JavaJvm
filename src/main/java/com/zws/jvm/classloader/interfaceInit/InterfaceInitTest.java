package com.zws.jvm.classloader.interfaceInit;

/**
 * @author zhengws
 * @date 2019-09-25 11:19
 */
public class InterfaceInitTest {
    public static void main(String[] args) {
//        intFinalSureValue();
        intFinalUnSureValue();
    }

    private static void intFinalSureValue(){
        System.out.println(ISun.value);
        /**
         *  访问接口的final在编译期间已确认的值，常量在编译阶段, 通过常量传播优化，将常量存入调用类的常量池中，
         *  以后对该常量的访问，都转化为对自身常量池的引用，与定义常量的类没有任何关系
         *
         * 输出：
         *  1
         *
         * 反编译结果:
         * public static void main(java.lang.String[]);
         *     Code:
         *        0: getstatic     #2                  // Field java/lang/System.out:Ljava/io/PrintStream;
         *        3: iconst_1
         *        4: invokevirtual #4                  // Method java/io/PrintStream.println:(I)V
         *        7: return
         */
    }

    private static void intFinalUnSureValue(){
        System.out.println(ISun.sunRandomValue);
        System.out.println("###################");
        System.out.println(ISun.parentRandomValue);

        /**
         * 说明：
         * 1. 当一个接口在初始化的时候，并不要求其父接口都完成了初始化
         * 2. 只有在真正使用到父接口的时候（如引用接口中所定义的常量时），才会初始化
         *
         * 输出：
         * ISun sun thread init ...
         * 2
         * ###################
         * IParent parent thread init ...
         * 0
         *
         * ISun 反编译结果:
         * public interface com.zws.jvm.classloader.interfaceInit.ISun extends com.zws.jvm.classloader.interfaceInit.IParent {
         *   public static final int value;
         *   public static final int sunRandomValue;
         *   public static final java.lang.Thread sunThread;
         *
         *   static {};
         *     Code:
         *        0: new           #1                  // class java/util/Random
         *        3: dup
         *        4: invokespecial #2                  // Method java/util/Random."<init>":()V
         *        7: iconst_3
         *        8: invokevirtual #3                  // Method java/util/Random.nextInt:(I)I
         *       11: putstatic     #4                  // Field sunRandomValue:I
         *       14: new           #5                  // class com/zws/jvm/classloader/interfaceInit/ISun$1
         *       17: dup
         *       18: invokespecial #6                  // Method com/zws/jvm/classloader/interfaceInit/ISun$1."<init>":()V
         *       21: putstatic     #7                  // Field sunThread:Ljava/lang/Thread;
         *       24: return
         * }
         */
    }
}
