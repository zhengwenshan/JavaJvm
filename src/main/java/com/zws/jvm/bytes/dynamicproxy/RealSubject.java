package com.zws.jvm.bytes.dynamicproxy;

/**
 * @author zhengws
 * @date 2019-10-13 10:53
 */
public class RealSubject implements Subject {

    @Override
    public void request() {
        System.out.println("RealSubject request");
    }

    @Override
    public String toString() {
        return "RealSubject toString Call ...";
    }
}
