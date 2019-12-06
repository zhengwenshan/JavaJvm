package com.zws.jvm.gc.oom;


import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author zhengws
 * @date 2019-10-21 15:47
 */
public class MethodAreaOOM {
    public static void main(String[] args) throws InterruptedException {
        while (true){
            Enhancer enhancer = new Enhancer();
            enhancer.setSuperclass(OOMObject.class);
            enhancer.setUseCache(false);
            enhancer.setCallback(new MethodInterceptor() {
                @Override
                public Object intercept(Object o, Method method, Object[] args, MethodProxy proxy) throws Throwable {
                    return proxy.invoke(o, args);
                }
            });
            enhancer.create();
//            Thread.sleep(500);
        }

        /**
         * 指定虚拟机参数: -XX:MetaspaceSize=10M -XX:MaxMetaspaceSize=10M
         * 采用CGLIB方式动态创建Class.
         * 异常信息如下：
         * Exception in thread "main" java.lang.OutOfMemoryError: Metaspace
         * 	at net.sf.cglib.core.AbstractClassGenerator.generate(AbstractClassGenerator.java:348)
         * 	at net.sf.cglib.proxy.Enhancer.generate(Enhancer.java:492)
         * 	at net.sf.cglib.core.AbstractClassGenerator$ClassLoaderData.get(AbstractClassGenerator.java:117)
         * 	at net.sf.cglib.core.AbstractClassGenerator.create(AbstractClassGenerator.java:294)
         * 	at net.sf.cglib.proxy.Enhancer.createHelper(Enhancer.java:480)
         * 	at net.sf.cglib.proxy.Enhancer.create(Enhancer.java:305)
         * 	at com.zws.jvm.gc.oom.MethodAreaOOM.main(MethodAreaOOM.java:26)
         */
    }

    static class OOMObject{

    }
}
