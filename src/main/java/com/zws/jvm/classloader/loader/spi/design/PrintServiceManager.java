package com.zws.jvm.classloader.loader.spi.design;

import sun.reflect.Reflection;

import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.Iterator;
import java.util.ServiceLoader;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author zhengws
 * @date 2019-09-30 16:16
 */
public class PrintServiceManager {
    private final static CopyOnWriteArrayList<IPrintService> registeredServiced = new CopyOnWriteArrayList<>();

    static {
        loadInitialDrivers();
    }

    /**
     * 加载初始化驱动
     */
    private static void loadInitialDrivers() {
        AccessController.doPrivileged(new PrivilegedAction<Void>() {
            @Override
            public Void run() {
                ServiceLoader<IPrintService> loader = ServiceLoader.load(IPrintService.class);
                Iterator<IPrintService> iterator = loader.iterator();
                try {
                    while (iterator.hasNext()) {
                        iterator.next();
                    }
                } catch (Exception e) {

                }
                return null;
            }
        });
    }

    public static void registerService(IPrintService service) {
        if (service != null) {
            registeredServiced.addIfAbsent(service);
        }
    }

    /**
     * 获取合适的Service.
     * @param flag
     * @return
     */
    public static IPrintService getPrintService(Boolean flag) {
        for (IPrintService service : registeredServiced){
            if (isDriverAllowed(service, Reflection.getCallerClass())){
                try {
                    if (service.match(flag)){
                        return service;
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    /**
     * 判断驱动是否允许，主要针对命名空间进行校验.
     * @param driver
     * @param caller
     * @return
     */
    private static boolean isDriverAllowed(IPrintService driver, Class<?> caller) {
        ClassLoader callerCL = caller != null ? caller.getClassLoader() : null;
        return isDriverAllowed(driver, callerCL);
    }

    private static boolean isDriverAllowed(IPrintService driver, ClassLoader classLoader) {
        boolean result = false;
        if (driver != null) {
            Class<?> aClass = null;
            try {
                aClass = Class.forName(driver.getClass().getName(), true, classLoader);
            } catch (Exception ex) {
                result = false;
            }

            result = (aClass == driver.getClass()) ? true : false;
        }

        return result;
    }
}
