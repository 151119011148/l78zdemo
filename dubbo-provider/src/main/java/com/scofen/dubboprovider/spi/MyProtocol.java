package com.scofen.dubboprovider.spi;

import org.apache.dubbo.common.URL;
import org.apache.dubbo.common.compiler.Compiler;
import org.apache.dubbo.common.extension.ExtensionLoader;
import org.apache.dubbo.rpc.*;

import java.util.List;

/**
 * Create by  GF  in  11:26 2019/11/3
 * Description:自定义协议
 * Modified  By:
 */
public class MyProtocol implements Protocol {


    public static void main(String[] args) {

        /**
         * 静态扩展点
         * 1.load指定路径下对应SPI扩展点de 实现，缓存到hashmap。key为文件中自定义的key，value就是class
         * 2.getExtension("myProtocol");  -> EXTENSIONS_INSTANCES.get("name");
         * 能够被扩展的接口，必须要有这样的标记：@SPI，dubbo自定义的注解
         */
        Protocol myProtocol = ExtensionLoader.getExtensionLoader(Protocol.class).getExtension("myProtocol");
        System.out.println(myProtocol.getDefaultPort());
        /**
         * 自适应扩展点
         * adaptive=org.apache.dubbo.common.compiler.support.AdaptiveCompiler
         jdk=org.apache.dubbo.common.compiler.support.JdkCompiler
         javassist=org.apache.dubbo.common.compiler.support.JavassistCompiler
         类级别：
         方法级别：动态生成代理类
         */
        Compiler myCompiler = ExtensionLoader.getExtensionLoader(Compiler.class).getAdaptiveExtension();
        System.out.println(myCompiler.getClass().getName());
        /**
         *  激活扩展点
         *
         */
        URL url = new URL("","",651124);
        //可配置，配置则激活
        url = url.addParameter("bloomfilter","bloomfilter");
        List<Filter> filters = ExtensionLoader.getExtensionLoader(Filter.class).getActivateExtension(url, "bloomfilter");
        System.out.println(filters.size());

    }

    @Override
    public int getDefaultPort() {
        return 651125;
    }

    @Override
    //暴漏服务
    //dubbo默认使用netty
    public <T> Exporter<T> export(Invoker<T> invoker) throws RpcException {
        return null;
    }

    @Override
    public <T> Invoker<T> refer(Class<T> aClass, URL url) throws RpcException {
        return null;
    }

    @Override
    public void destroy() {

    }
}
