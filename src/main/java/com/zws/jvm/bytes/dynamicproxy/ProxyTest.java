package com.zws.jvm.bytes.dynamicproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * @author zhengws
 * @date 2019-10-13 10:55
 */
public class ProxyTest {
    public static void main(String[] args) {
        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles","true");
        Subject sub = new RealSubject();
        InvocationHandler handler = new Handler(sub);
        Class<?> clazz = sub.getClass();
        Subject subject = (Subject) Proxy.newProxyInstance(clazz.getClassLoader(), clazz.getInterfaces(), handler);
        subject.request();
        System.out.println("############################");
        subject.toString();
        System.out.println("############################");
        subject.hashCode();
        /**
         * 输出:
         * Before request call .....public abstract void com.zws.jvm.bytes.dynamicproxy.Subject.request()
         * RealSubject request
         * After request call .....public abstract void com.zws.jvm.bytes.dynamicproxy.Subject.request()
         * ############################
         * Before request call .....public java.lang.String java.lang.Object.toString()
         * After request call .....public java.lang.String java.lang.Object.toString()
         * ############################
         * Before request call .....public native int java.lang.Object.hashCode()
         * After request call .....public native int java.lang.Object.hashCode()
         *
         *
         * 生成的代理类：
         * static {
         *         try {
         *             m1 = Class.forName("java.lang.Object").getMethod("equals", Class.forName("java.lang.Object"));
         *             m2 = Class.forName("java.lang.Object").getMethod("toString");
         *             m3 = Class.forName("com.zws.jvm.bytes.dynamicproxy.Subject").getMethod("request");
         *             m0 = Class.forName("java.lang.Object").getMethod("hashCode");
         *         } catch (NoSuchMethodException var2) {
         *             throw new NoSuchMethodError(var2.getMessage());
         *         } catch (ClassNotFoundException var3) {
         *             throw new NoClassDefFoundError(var3.getMessage());
         *         }
         *     }
         */
    }
}
