package com.scofen.study.designpattern.proxy.myselfProxy;




import javax.tools.JavaCompiler;
import javax.tools.JavaCompiler.CompilationTask;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

/**
 * Create by  GF  in  16:18 2018/7/23
 * Description:
 * Modified  By:
 */
public class MyselfProxy {

    private static String changeLine = "\r\n";

    public static Object newProxyInstance(MyselfClassLoader loader, Class<?>[] interfaces, MyselfInvocationHandler h) throws Exception{

        //1.生成源代码
        String proxySrc = generateSrc(interfaces[0]);

        //2.将生成的源代码输出到磁盘，保存为.java文件
        File file =printJava(proxySrc);

        //3.编译源代码，生成。class文件
        printClass(file);

        //4.将class文件内容动态加载到jvm中
        Class classProxy = loader.findClass("$MyProxy");

        //5.返回被代理的代理对象
        Constructor constructor = classProxy.getConstructor(MyselfInvocationHandler.class);
        //ThreadUtil.getThreadPool().execute(() -> FileUtil.fileDelete(file));
        return constructor.newInstance(h);
    }

    public static void printClass(File file) throws IOException {
        JavaCompiler javaCompiler = ToolProvider.getSystemJavaCompiler();
        StandardJavaFileManager standardJavaFileManager = javaCompiler.
                getStandardFileManager(null,null,null);
        Iterable iterable = standardJavaFileManager.getJavaFileObjects(file);
        CompilationTask compilationTask = javaCompiler.getTask(null,standardJavaFileManager,
                null,null,null, iterable);
        compilationTask.call();
        standardJavaFileManager.close();
    }

    public static File printJava(String proxySrc) {
        String filePath = MyselfProxy.class .getResource("").getPath();
        File file = new File(filePath + "$MyProxy.java");
        try {
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(proxySrc);
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }

    public static String generateSrc(Class<?> interfaces){

        StringBuffer stringBuffer = new StringBuffer()
                .append("package com.scofen.sourcecode.study.GPstudy.designPattern.proxy.myselfProxy;" + changeLine)
                .append("import java.lang.reflect.Method;" + changeLine)
                .append("public class $MyProxy implements " + interfaces.getName() + "{" + changeLine )
                .append("MyselfInvocationHandler h;" + changeLine)
                .append("public $MyProxy(MyselfInvocationHandler h){ " + changeLine).append("this.h = h;" + changeLine).append("}" + changeLine);

        for(Method method : interfaces.getMethods()){

            stringBuffer.append("public " + method.getReturnType().getName() + " " + method.getName() + "(){" + changeLine)
                    .append("try{" + changeLine)
                    .append("Method m = " + interfaces.getName() + ".class.getMethod(\"" + method.getName() +"\",new Class[]{});" + changeLine)
                    .append("this.h.invoke(this,m,null);" + changeLine)
                    .append("}catch(Throwable e){e.printStackTrace();}" + changeLine)
                    .append("}" + changeLine);
        };
        stringBuffer.append("}");
        System.out.println(stringBuffer.toString());

        return stringBuffer.toString();
    }





}
