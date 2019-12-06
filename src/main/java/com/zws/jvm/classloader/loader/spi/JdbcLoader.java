package com.zws.jvm.classloader.loader.spi;

import com.zws.jvm.classloader.loader.spi.design.CallerTest;

import java.sql.DriverManager;

/**
 * @author zhengws
 * @date 2019-09-30 15:57
 */
public class JdbcLoader {
    public static void main(String[] args) throws Exception {
//        CallerTest.printCaller();
        DriverManager.getConnection("jdbc:mysql://10.8.206.53:63751", "root","root");
        DriverManager.getDriver("jdbc:mysql://10.8.206.53:63751");
        CallerTest.class.getDeclaredMethods();
        System.out.println(System.getProperty("sun.boot.class.path"));
    }
}
