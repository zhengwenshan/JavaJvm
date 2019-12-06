package com.zws.jvm.classloader.loader.delegation;

/**
 * @author zhengws
 * @date 2019-09-29 20:11
 */
public class CustomClassLoaderTest6 {
    public static void main(String[] args) throws Exception{
        CustomClassLoader classLoader = new CustomClassLoader("loader1");
        classLoader.setPath("/Users/zhengws/Desktop/");
        Class<?> clazz = classLoader.loadClass("com.zws.jvm.classloader.loader.delegation.MySample");
        System.out.println(clazz.hashCode());
        System.out.println("#################");
        Object instance = clazz.newInstance();

        /**
         * 测试一、将Cat.class,MySample.class 复制到 桌面，并将Cat.class文件在class path中删除，保留MySample.class
         * 说明：
         *  1. 当自定义classloader 去加载MySample时，是先委托给系统类加载器进行加载，在class path中可以找到MySamlpe.class文件
         *  因此MySample的Class对象由系统类加载器进行加载
         *  2. 当通过newInstance()反射创建对象时，MySample会试图采用加载自己的类加载器进行加载Cat(也就是系统类加载器),而此时在class
         *  path中找不到Cat.class文件，因此会报NoClassDefFoundError错误。
         * 输出：
         * 1627674070
         * #################
         * MySample init ...
         * MySample is loaded by : sun.misc.Launcher$AppClassLoader@18b4aac2
         * Exception in thread "main" java.lang.NoClassDefFoundError: com/zws/jvm/classloader/loader/delegation/Cat
         * 	at com.zws.jvm.classloader.loader.delegation.MySample.<init>(MySample.java:13)
         * 	at sun.reflect.NativeConstructorAccessorImpl.newInstance0(Native Method)
         * 	at sun.reflect.NativeConstructorAccessorImpl.newInstance(NativeConstructorAccessorImpl.java:62)
         * 	at sun.reflect.DelegatingConstructorAccessorImpl.newInstance(DelegatingConstructorAccessorImpl.java:45)
         * 	at java.lang.reflect.Constructor.newInstance(Constructor.java:423)
         * 	at java.lang.Class.newInstance(Class.java:442)
         * 	at com.zws.jvm.classloader.loader.delegation.CustomClassLoaderTest6.main(CustomClassLoaderTest6.java:14)
         * Caused by: java.lang.ClassNotFoundException: com.zws.jvm.classloader.loader.delegation.Cat
         * 	at java.net.URLClassLoader.findClass(URLClassLoader.java:382)
         * 	at java.lang.ClassLoader.loadClass(ClassLoader.java:424)
         * 	at sun.misc.Launcher$AppClassLoader.loadClass(Launcher.java:349)
         * 	at java.lang.ClassLoader.loadClass(ClassLoader.java:357)
         * 	... 7 more
         *
         * 	测试二、将Cat.class,MySample.class 复制到 桌面，并将MySample.class文件在class path中删除，保留Cat.class
         *  说明：
         *  1.当自定义classloader 去加载MySample时，是先委托给系统类加载器进行加载，在class path中找不到MySamlpe.class文件
         *   ,而在自定义加载器指定路劲中找到MySample.class, 因此MySample的Class对象由自定义类加载器进行加载
         *  2. 当通过newInstance()反射创建对象时，MySample会试图采用加载自己的类加载器进行加载Cat(也就是自定义类加载器),
         *  而自定义加载器则委托给父亲进行加载，也就是系统类加载器，而在class path中存在Cat.class文件，因此有系统类加载器进行加载成功。
         *
         *  输出：
         *  loader1 findClass execute, the class is: com.zws.jvm.classloader.loader.delegation.MySample
         * 1625635731
         * #################
         * MySample init ...
         * MySample is loaded by : com.zws.jvm.classloader.loader.delegation.CustomClassLoader@610455d6
         * Cat class init ...
         * Cat is loaded by: sun.misc.Launcher$AppClassLoader@18b4aac2
         */
    }
}
