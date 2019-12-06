package com.zws.jvm.bytes.runengin;

/**
 * @author zhengws
 * @date 2019-10-15 14:25
 */
public class OverLoadTest {
//    // 调用顺序8
//    public static void sayHello(Object arg){
//        System.out.println("Hello Object");
//    }

//    // 调用顺序2
//    public static void sayHello(int arg){
//        System.out.println("Hello int");
//    }

//    // 调用顺序3
//    public static void sayHello(long arg){
//        System.out.println("Hello long");
//    }

//    // 调用顺序6
//    public static void sayHello(Character arg){
//        System.out.println("Hello Character");
//    }

//    // 调用顺序1
//    public static void sayHello(char arg){
//        System.out.println("Hello char");
//    }

//    // 调用顺序4
//    public static void sayHello(float arg){
//        System.out.println("Hello float");
//    }

//    // 调用顺序5
//    public static void sayHello(double arg){
//        System.out.println("Hello double");
//    }

    // 调用顺序9
    public static void sayHello(char... arg){
        System.out.println("Hello char...");
    }

//    // 调用顺序7
//    public static void sayHello(Serializable arg){
//        System.out.println("Hello Serializable");
//    }

    public static void main(String[] args) {
        sayHello('a');
        /**
         * 类型自动转换
         * char -> int -> long -> float -> double -> Character -> Serializable -> Object
         */
    }
}
