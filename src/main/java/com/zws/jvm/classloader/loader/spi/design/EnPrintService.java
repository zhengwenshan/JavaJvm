package com.zws.jvm.classloader.loader.spi.design;

/**
 * @author zhengws
 * @date 2019-09-30 16:14
 */
public class EnPrintService implements IPrintService {
    static {
        System.out.println("EnPrintService init ..., Class loader is: " + EnPrintService.class.getClassLoader());
        PrintServiceManager.registerService(new EnPrintService());
    }

    @Override
    public void printMsg(String msg) {
        System.out.println("Hello: " + msg);
    }

    @Override
    public boolean match(boolean flag) {
        return !flag;
    }
}
