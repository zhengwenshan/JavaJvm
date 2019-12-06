package com.zws.jvm.classloader.loader.delegation;

/**
 * @author zhengws
 * @date 2019-09-29 20:11
 */
public class ClassLoaderTime {
    public static void main(String[] args) throws Exception{
        CustomClassLoader classLoader = new CustomClassLoader("loader1");
        classLoader.setPath("/Users/zhengws/Desktop/");
        Class<?> clazz = classLoader.loadClass("com.zws.jvm.classloader.loader.delegation.MySample");
        System.out.println(clazz.hashCode());
        System.out.println("#################");
        Object instance = clazz.newInstance();

        /**
         * 说明：HotSpot 并没有预先加载该Cat.class
         *
         * 输出结果：
         * [Loaded com.zws.jvm.classloader.loader.delegation.CustomClassLoader from file:/Users/zhengws/IdeaProjects/JVM/JavaJvm/target/classes/]
         * [Loaded java.io.IOException from /Library/Java/JavaVirtualMachines/jdk1.8.0_212.jdk/Contents/Home/jre/lib/rt.jar]
         * [Loaded java.net.Inet6Address from /Library/Java/JavaVirtualMachines/jdk1.8.0_212.jdk/Contents/Home/jre/lib/rt.jar]
         * [Loaded com.zws.jvm.classloader.loader.delegation.MySample from file:/Users/zhengws/IdeaProjects/JVM/JavaJvm/target/classes/]
         * [Loaded java.net.Inet6Address$Inet6AddressHolder from /Library/Java/JavaVirtualMachines/jdk1.8.0_212.jdk/Contents/Home/jre/lib/rt.jar]
         * 1627674070
         * #################
         * [Loaded java.lang.Shutdown from /Library/Java/JavaVirtualMachines/jdk1.8.0_212.jdk/Contents/Home/jre/lib/rt.jar]
         * [Loaded java.lang.Shutdown$Lock from /Library/Java/JavaVirtualMachines/jdk1.8.0_212.jdk/Contents/Home/jre/lib/rt.jar]
         */
    }
}
