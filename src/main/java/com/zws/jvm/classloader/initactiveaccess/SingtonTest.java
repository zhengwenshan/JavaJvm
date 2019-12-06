package com.zws.jvm.classloader.initactiveaccess;

/**
 * @author zhengws
 * @date 2019-09-25 15:26
 */
public class SingtonTest {
    public static void main(String[] args) {
        System.out.println(Sington.sington);
        System.out.println("##################");
        System.out.println(Sington.sington.v3);

        /**
         *
         * 输出：
         * v1 = 10
         * v2 = 20
         * v3 = 0
         * com.zws.jvm.classloader.initactiveaccess.Sington@61bbe9ba
         * ##################
         * 30
         *
         * 反编译结果:
         * public class com.zws.jvm.classloader.initactiveaccess.Sington {
         *   public static int v1;
         *   public static com.zws.jvm.classloader.initactiveaccess.Sington sington;
         *   public static final int v2;
         *   public static int v3;
         *   public com.zws.jvm.classloader.initactiveaccess.Sington();
         *     Code:
         *        0: aload_0
         *        1: invokespecial #1                  // Method java/lang/Object."<init>":()V
         *        4: getstatic     #2                  // Field java/lang/System.out:Ljava/io/PrintStream;
         *        7: new           #3                  // class java/lang/StringBuilder
         *       10: dup
         *       11: invokespecial #4                  // Method java/lang/StringBuilder."<init>":()V
         *       14: ldc           #5                  // String v1 =
         *       16: invokevirtual #6                  // Method java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
         *       19: getstatic     #7                  // Field v1:I
         *       22: invokevirtual #8                  // Method java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
         *       25: invokevirtual #9                  // Method java/lang/StringBuilder.toString:()Ljava/lang/String;
         *       28: invokevirtual #10                 // Method java/io/PrintStream.println:(Ljava/lang/String;)V
         *       31: getstatic     #2                  // Field java/lang/System.out:Ljava/io/PrintStream;
         *       34: ldc           #12                 // String v2 = 20 //ldc. 标识将int,float 或是string类型的常量值从常量池推送至栈顶
         *       36: invokevirtual #10                 // Method java/io/PrintStream.println:(Ljava/lang/String;)V
         *       39: getstatic     #2                  // Field java/lang/System.out:Ljava/io/PrintStream;
         *       42: new           #3                  // class java/lang/StringBuilder
         *       45: dup
         *       46: invokespecial #4                  // Method java/lang/StringBuilder."<init>":()V
         *       49: ldc           #13                 // String v3 =
         *       51: invokevirtual #6                  // Method java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
         *       54: getstatic     #14                 // Field v3:I
         *       57: invokevirtual #8                  // Method java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
         *       60: invokevirtual #9                  // Method java/lang/StringBuilder.toString:()Ljava/lang/String;
         *       63: invokevirtual #10                 // Method java/io/PrintStream.println:(Ljava/lang/String;)V
         *       66: return
         * }
         */
    }
}
