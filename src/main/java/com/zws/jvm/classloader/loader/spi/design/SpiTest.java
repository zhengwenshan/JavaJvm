package com.zws.jvm.classloader.loader.spi.design;

import sun.reflect.CallerSensitive;
import sun.reflect.Reflection;

/**
 * @author zhengws
 * @date 2019-09-30 16:45
 */
public class SpiTest {
    public static void main(String[] args) {
        System.out.println(SpiTest.class.getClassLoader());

        System.out.println(Thread.currentThread().getContextClassLoader().getClass().getClassLoader());
//        Class<?> clazz = Reflection.getCallerClass();
//        System.out.println(clazz);
//        ClassLoader currentLoader = ClassLoader.getSystemClassLoader();
//        try {
//            Thread.currentThread().setContextClassLoader(currentLoader.getParent());
//            Thread thread = new Thread() {
//                @Override
//                public void run() {
//
//                }
//            };
//            thread.setContextClassLoader(null);
//            System.out.println(thread.getContextClassLoader());
//            thread.start();
//
//        }finally {
//            Thread.currentThread().setContextClassLoader(currentLoader);
//        }
//        IPrintService service = PrintServiceManager.getPrintService(true);
//        if (service != null){
//            service.printMsg("message");
//        }else {
//            System.out.println("service is null..");
//        }
    }
}
