package com.scofen.l78z.xiaochuan.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.File;

import static com.scofen.l78z.xiaochuan.service.FileService.fileDir;

/**
 * @Description: TODO
 * @Author gaofeng
 * @Date 2024/3/9 9:11 PM
 **/
@Configuration
public class UploadConfig implements WebMvcConfigurer {

    @Value("${file.upload.resource.location:/Users/gaoying/Desktop/work/l78zdemo/xiaochuan/fileStorage/}")
    private String resourceLocation;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // 映射名
        registry.addResourceHandler(File.separator + fileDir + File.separator +"**")
                // 映射路径
                .addResourceLocations("file:" + resourceLocation);
    }
}
