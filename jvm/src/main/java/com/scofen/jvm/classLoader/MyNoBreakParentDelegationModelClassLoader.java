package com.scofen.jvm.classLoader;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.lang.ClassLoader;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.WritableByteChannel;

/**
 * Create by  GF  in  15:28 2019/3/1
 * Description:
 * 不打破双亲委派模型，那么只需要重写findClass方法即可
 * Modified  By:
 */
public class MyNoBreakParentDelegationModelClassLoader extends ClassLoader {

    public MyNoBreakParentDelegationModelClassLoader() {

    }

    public MyNoBreakParentDelegationModelClassLoader(ClassLoader parent) {
        super(parent);
    }

    //    1、如果不想打破双亲委派模型，那么只需要重写findClass方法即可
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        File file = getClassFile(name);
        try {
            byte[] bytes = getClassBytes(file);
            Class<?> c = this.defineClass(name, bytes, 0, bytes.length);
            return c;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return super.findClass(name);
    }

    private File getClassFile(String name) {
        File file = new File("C:/test/TestMyClassLoader.class");
        return file;
    }

    private byte[] getClassBytes(File file) throws Exception {
        // 这里要读入.class的字节，因此要使用字节流
        FileInputStream fis = new FileInputStream(file);
        FileChannel fc = fis.getChannel();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        WritableByteChannel wbc = Channels.newChannel(baos);
        ByteBuffer by = ByteBuffer.allocate(1024);

        while (true) {
            int i = fc.read(by);
            if (i == 0 || i == -1)
                break;
            by.flip();
            wbc.write(by);
            by.clear();
        }

        fis.close();

        return baos.toByteArray();
    }


    public static void main(String[] args) throws Exception{
        MyNoBreakParentDelegationModelClassLoader myClassLoader = new MyNoBreakParentDelegationModelClassLoader();
        Class<?> c1 = Class.forName("com.scofen.jvm.classLoader.TestMyClassLoader", true, myClassLoader);
        Object obj = c1.newInstance();
        System.out.println(obj);
        System.out.println(obj.getClass().getClassLoader());
    }
}
