package com.scofen.study.designpattern.proxy.myselfProxy;





import com.scofen.study.designpattern.utils.FileUtil;
import com.scofen.study.designpattern.utils.ThreadUtil;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Create by  GF  in  16:21 2018/7/23
 * Description:代码生成，编译，重新load到编译器
 * Modified  By:
 */
public class MyselfClassLoader extends ClassLoader {

    private File directory;

    public MyselfClassLoader(){

        String basePath = MyselfClassLoader.class.getResource("").getPath();
        directory = new File(basePath);

    }

    protected  Class<?> findClass(String name) {

        String className = MyselfClassLoader.class.getPackage().getName() + "." + name;
        if(directory != null){
            File classFile = new File(directory, name.replaceAll("\\.","/") + ".class");
            if (classFile.exists()){
                FileInputStream fileInputStream = null;
                ByteArrayOutputStream byteArrayOutputStream = null;
                try {
                    fileInputStream = new FileInputStream(classFile);
                    byteArrayOutputStream = new ByteArrayOutputStream();
                    byte[] bytes = new byte[1024];
                    int length;
                    while ((length = fileInputStream.read(bytes) ) != -1){
                        byteArrayOutputStream.write(bytes,0,length);
                    }
                    ThreadUtil.getThreadPool().execute(() -> FileUtil.fileDelete(classFile));
                    return defineClass(className,byteArrayOutputStream.toByteArray(),0,byteArrayOutputStream.size());

                }catch (Exception e){
                    e.printStackTrace();
                }finally {
                    if (fileInputStream != null){
                        try {
                            fileInputStream.close();
                            byteArrayOutputStream.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    ThreadUtil.getThreadPool().execute(() -> FileUtil.fileDelete(classFile));
                }
            }
        }

        return null;
    }



}
