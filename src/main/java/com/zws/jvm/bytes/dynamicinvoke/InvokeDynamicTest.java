package com.zws.jvm.bytes.dynamicinvoke;

import java.lang.invoke.*;

/**
 * @author zhengws
 * @date 2019-10-15 16:53
 */
public class InvokeDynamicTest {
    public static void testMethod(String arg) {

        System.out.println("testMethod: " + arg);

    }

    public static void main(String[] args) throws Throwable {
        INDY_BootstrapMethod().invokeExact("Hello World");
    }

    public static CallSite BootstrapMethod(MethodHandles.Lookup lookup, String name, MethodType mt) throws Throwable {

        return new ConstantCallSite(lookup.findStatic(InvokeDynamicTest.class, name, mt));

    }

    private static MethodType MT_BootstrapMethod() {
        return MethodType.fromMethodDescriptorString(
                "(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;)Ljava/lang/invoke/CallSite;",
                null);
    }

    private static MethodHandle MH_BootstrapMethod() throws Throwable {
        return MethodHandles.lookup().findStatic(InvokeDynamicTest.class, "BootstrapMethod", MT_BootstrapMethod());
    }

    private static MethodHandle INDY_BootstrapMethod() throws Throwable {
        CallSite callSite = (CallSite) MH_BootstrapMethod().invokeWithArguments(MethodHandles.lookup(), "testMethod",
                MethodType.fromMethodDescriptorString("(Ljava/lang/String;)V", null));
        return callSite.dynamicInvoker();
    }
}
