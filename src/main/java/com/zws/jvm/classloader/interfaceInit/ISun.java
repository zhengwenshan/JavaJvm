package com.zws.jvm.classloader.interfaceInit;

import java.util.Random;

/**
 * @author zhengws
 * @date 2019-09-25 11:22
 */
public interface ISun extends IParent {
    int value = 1;
    int sunRandomValue = new Random().nextInt(3);
    Thread sunThread = new Thread(){
        {
            System.out.println("ISun sun thread init ...");
        }
    };
}
