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

    @Value("${file.upload.resource.location:/Users/gaoying/Desktop/work/l78zdemo/xiaochuan/src/main/resources/fileStorage/}")
    private String resourceLocation;

    //自己设置的目录
    public static final String fileDir = "fileStorage";
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
        File folder = new File(resourceLocation);
        if (!folder.exists()) {
            folder.mkdirs();
        }

        String fileName = timeFormat(System.currentTimeMillis()) + SUFFIX.getAndIncrement() + "." + suffix;
        String absolutePath = resourceLocation + fileName;
        log.info("absolutePath is {}", absolutePath);
        try {
            file.transferTo(new File(absolutePath));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String separator = "/";
        return separator + fileDir + separator + fileName;
    }

    public static String timeFormat(Long time) {
        if (Objects.isNull(time)) {
            return null;
        }
        DateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        return sdf.format(time);
    }
}
