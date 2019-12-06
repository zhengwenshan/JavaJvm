package com.zws.jvm.gc.ref;

public class RefCount {
    public Object instance;
    //占用内存
    private byte[] bigSize= new byte[10*1024*1024];
}