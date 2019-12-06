package com.zws.jvm.classloader.loader.spi.design;

/**
 * @author zhengws
 * @date 2019-09-30 16:15
 */
public class CnPrintService implements IPrintService {
    static {
        System.out.println("CnPrintService init ..., Class loader is: " + CnPrintService.class.getClassLoader());
        PrintServiceManager.registerService(new CnPrintService());
    }

    @Override
    public void printMsg(String msg) {
        System.out.println("你好: "+ msg);
    }

    @Override
    public boolean match(boolean flag) {
        return flag;
    }
}
