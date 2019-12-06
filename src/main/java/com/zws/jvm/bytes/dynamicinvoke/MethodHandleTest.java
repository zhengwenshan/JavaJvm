package com.zws.jvm.bytes.dynamicinvoke;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;

/**
 * @author zhengws
 * @date 2019-10-15 16:17
 */
public class MethodHandleTest {
    static class ClassA {
        public void say(String msg) {
            System.out.println("message is: " + msg);
        }
    }


    static class ClassB {
        public void sayHello(String msg, String name) {
            System.out.println("message is: " + msg + " name: " + name);
        }
    }

    private static MethodHandle getSayMethodHandle(Object reveiver, String method, Class... args) throws Throwable{
        MethodType methodType = MethodType.methodType(void.class, args);
        return MethodHandles.lookup().findVirtual(reveiver.getClass(), method, methodType).bindTo(reveiver);
    }

    public static void main(String[] args) throws Throwable{
        ClassA classA = new ClassA();
        getSayMethodHandle(classA, "say", String.class).invokeExact("Hello World");

        System.out.println("###################");
        ClassB classB = new ClassB();
        getSayMethodHandle(classB, "sayHello", String.class, String.class).invokeExact("Hello World", "zhengws");

        /**
         * 输出：
         * message is: Hello World
         * ###################
         * message is: Hello World name: zhengws
         *
         * 最后一次调用反编译如下:
         * 61: invokestatic  #13                 // Method getSayMethodHandle:(Ljava/lang/Object;Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/invoke/MethodHandle;
         * 64: ldc           #14                 // String Hello World
         * 66: ldc           #22                 // String zhengws
         * 68: invokevirtual #23                 // Method java/lang/invoke/MethodHandle.invokeExact:(Ljava/lang/String;Ljava/lang/String;)V
         *
         *  MethodHandle 与 Reflection区别：
         *  1、Reflection是在模拟Java代码层次的方法调用，而MethodHandle是在模拟字节码层次的方法调用
         *  2、Reflection中的java.lang.reflect.Method对象远比MethodHandle机制中的java.lang.invoke.MethodHandle对象所包含的信息多。前者是方法在Java一端的全面映像，
         *  包含了方法的签名、描述符以及方法属性表中各种属性的Java端表示方式，还包含执行权限等的运行期信息。而后者仅仅包含与执行该方法相关的信息
         *  3、Reflection API的设计目标是只为Java语言服务的，而MethodHandle则设计成可服务于所有Java虚拟机之上的语言
         */
    }
}
