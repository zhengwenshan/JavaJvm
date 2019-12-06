package com.zws.jvm.bytes.dynamicproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author zhengws
 * @date 2019-10-13 10:53
 */
public class Handler implements InvocationHandler {
    private Object sub;

    public Handler(Object sub) {
        this.sub = sub;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("Before request call ....." + method);
        Object result = method.invoke(sub, args);
        System.out.println("After request call ....." + method);
        return result;
    }
}
