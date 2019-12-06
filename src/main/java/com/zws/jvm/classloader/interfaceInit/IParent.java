package com.zws.jvm.classloader.interfaceInit;

import java.util.Random;

/**
 * @author zhengws
 * @date 2019-09-25 11:20
 */
public interface IParent {
    int value = 10;
    int parentRandomValue = new Random().nextInt(1);
    Thread parentThread = new Thread(){
        {
            System.out.println("IParent parent thread init ...");
        }
    };
}
