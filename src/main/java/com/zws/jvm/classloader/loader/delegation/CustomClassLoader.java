package com.zws.jvm.classloader.loader.delegation;

import java.io.*;

/**
 * @author zhengws
 * @date 2019-09-27 17:20
 */
public class CustomClassLoader extends ClassLoader {
    private static final String EXTNAME = ".class";
    private String path;
    private String loaderName;

    public CustomClassLoader(ClassLoader parent, String loaderName) {
        super(parent);
        this.loaderName = loaderName;
    }

    public CustomClassLoader(String loaderName) {
        super();
        this.loaderName = loaderName;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Override
    protected Class<?> findClass(String className) throws ClassNotFoundException {
        if (path == null || "".equals(path.trim())) {
            return super.findClass(className);
        }
        System.out.println(loaderName + " findClass execute, the class is: " + className);
        byte[] bytes = loadClassBytes(className);
        return defineClass(className, bytes, 0, bytes.length);
    }

    private byte[] loadClassBytes(String className) {
        String filePath = this.path + className.replace(".", "/") + EXTNAME;
        InputStream ins = null;
        ByteArrayOutputStream baos = null;
        byte[] bytes = null;
        try {
            ins = new FileInputStream(new File(filePath));
            baos = new ByteArrayOutputStream();
            int ch;
            while (-1 != (ch = ins.read())) {
                baos.write(ch);
            }
            bytes = baos.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (ins != null) {
                try {
                    ins.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (baos != null) {
                try {
                    baos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return bytes;
    }
}
