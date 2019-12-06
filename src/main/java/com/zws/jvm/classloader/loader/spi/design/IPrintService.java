package com.zws.jvm.classloader.loader.spi.design;

/**
 * @author zhengws
 * @date 2019-09-30 16:14
 */
public interface IPrintService {
    /**
     * 根据不同语言打印消息
     * @param msg
     */
    void printMsg(String msg);

    boolean match(boolean flag);
}
