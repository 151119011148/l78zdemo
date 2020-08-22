package com.scofen.jvm.classLoader;

import java.io.FileInputStream;

/**
 * Create by  GF  in  16:20 2019/3/1
 * Description:
 * 如果想打破双亲委派模型，那么就重写整个loadClass方法
 * Modified  By:
 */
public class MyBreakParentDelegationModelClassLoader extends ClassLoader {

    private String classPath;

public MyBreakParentDelegationModelClassLoader(String classPath) {
        this.classPath = classPath;
    }

    /*private byte[] loadByte(String path) throws Exception {
        path = path.replaceAll("\\.", "/");
        FileInputStream fis = new FileInputStream(classPath + "/" + path
                + ".class");
        int len = fis.available();
        byte[] data = new byte[len];
        fis.read(data);
        fis.close();
        return data;

    }

    protected Class<?> findClass(String name) throws ClassNotFoundException {
        try {
            byte[] data = loadByte(name);
            return defineClass(name, data, 0, data.length);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ClassNotFoundException();
        }
    }*/

    public static void main(String args[]) throws Exception {
        MyBreakParentDelegationModelClassLoader classLoader = new MyBreakParentDelegationModelClassLoader("C:/test");
        Class clazz = classLoader.loadClass("java.lang.String");
        Object obj = clazz.newInstance();
        System.out.println(obj == null);
        System.out.println(classLoader);
        System.out.println(obj.getClass().getClassLoader());
    }




}
