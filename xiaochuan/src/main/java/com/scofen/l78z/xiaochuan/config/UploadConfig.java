package com.scofen.l78z.xiaochuan.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ResourceUtils;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.File;
import java.io.FileNotFoundException;

import static com.scofen.l78z.xiaochuan.service.FileService.fileDir;

/**
 * @Description: TODO
 * @Author gaofeng
 * @Date 2024/3/9 9:11 PM
 **/
@Configuration
public class UploadConfig implements WebMvcConfigurer {

    @Value("${file.upload.resource.location:/Users/gaoying/Desktop/work/l78zdemo/xiaochuan/src/main/resources/fileStorage/}")
    private String resourceLocation;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // 映射名
        registry.addResourceHandler(File.separator + fileDir + File.separator +"**")
                // 映射路径
                .addResourceLocations("file:" + resourceLocation);
        //在windows环境下的图片存放资源路径
        String winPath = System.getProperty("user.dir")+fileDir;
        //在Linux环境下的图片存放资源路径
//        String linuxPath = "/usr/local/my_project/images/";
        String os = System.getProperty("os.name");
        if (os.toLowerCase().startsWith("win")) {  //windows系统
            System.out.println(winPath);
            //第一个方法设置访问路径前缀，第二个方法设置资源路径
            registry.addResourceHandler("/images/**").
                    addResourceLocations("file:"+winPath);
        }else {//linux系统
            File rootPath = null;
            try {
                rootPath = new File(ResourceUtils.getURL("classpath:").getPath());
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            if (!rootPath.exists()) {
                rootPath = new File("");
            }
            System.out.println(rootPath.getAbsolutePath() + fileDir);

            File imagePath = new File(rootPath.getAbsolutePath() + fileDir);
            if (!imagePath.exists()) {
                //不存在，创建
                imagePath.mkdirs();
            }
        }
    }
}
