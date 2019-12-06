package com.zws.jvm.classloader.loader.spi;

import com.zws.jvm.classloader.loader.spi.design.IPrintService;

import java.sql.Driver;
import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * @author zhengws
 * @date 2019-09-30 16:04
 */
public class ServiceLoaderTest {
    public static void main(String[] args) {
//        ServiceLoader<Driver> loader = ServiceLoader.load(Driver.class);
        ServiceLoader<IPrintService> loader = ServiceLoader.load(IPrintService.class);
        Iterator<IPrintService> iterator = loader.iterator();
        while (iterator.hasNext()){
            IPrintService driver = iterator.next();
            driver.printMsg("hello world");
            System.out.println(driver.getClass().getName());
            System.out.println(driver.getClass().getClassLoader());
            System.out.println("###############");
        }
    }
}
