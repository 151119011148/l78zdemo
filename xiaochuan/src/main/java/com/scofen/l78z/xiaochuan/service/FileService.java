package com.scofen.l78z.xiaochuan.service;

import com.google.common.collect.Lists;
import com.scofen.l78z.xiaochuan.common.exception.ResultCode;
import com.scofen.l78z.xiaochuan.common.exception.ServiceException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
@Service
public class FileService {

    //拦截的url，虚拟路径
    public String pathPattern = "files";

    //自己设置的目录
    public static final String fileDir = "fileStorage";

    //上传文件存放目录  =  工作目录绝对路径 + 自己设置的目录，也可以直接自己指定服务器目录
    //windows本地测试
    //绝对路径: D:\develop\work\project\myblog\myblog-file-upload\fileStorage\202302021010345680.jpg
    //System.getProperty("user.dir")   D:\develop\work\project\myblog\myblog-file-upload
    //fileDir                          fileStorage
    //fileName                         202302021010345680.jpg
    public String filePath = System.getProperty("user.dir") + File.separator + fileDir + File.separator;

    private static final AtomicInteger SUFFIX = new AtomicInteger(0);

    @Value(value = "${file.upload.suffix:jpg,jpeg,png,bmp,xls,xlsx,pdf}")
    private String fileUploadSuffix;

    public String upload(MultipartFile file)  {

        List<String> suffixList = Lists.newArrayList(fileUploadSuffix.split(","));
        //校验文件后缀
        String originalFilename = file.getOriginalFilename();
        String suffix = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
        if (!suffixList.contains(suffix)) {
            throw new ServiceException(ResultCode.PARAM_CHECK_FAILED.getCode(), "unsupported file format");
        }

        //首次需生成目录
        File folder = new File(filePath);
        if (!folder.exists()) {
            folder.mkdirs();
        }

        String fileName = timeFormat(System.currentTimeMillis()) + SUFFIX.getAndIncrement() + "." + suffix;
        String absolutePath = filePath + fileName;
        log.info("absolutePath is {}", absolutePath);
        try {
            file.transferTo(new File(absolutePath));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return absolutePath;
    }

    public static String timeFormat(Long time) {
        if (Objects.isNull(time)) {
            return null;
        }
        DateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        return sdf.format(time);
    }
}
