package com.scofen.designpattern.utils;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.web.multipart.MultipartFile;

/**
 * Create by  GF  in  12:58 2018/7/9
 * Description:
 * Modified  By:
 */
public class GenerateNameUtil {


    public static String getFileName(MultipartFile file){
        return new StringBuffer()
/*                .append(file.getName())
                .append("_")*/
                .append(DateFormatUtils.format(System.currentTimeMillis(),"yyyy-MM-dd_HHmmss"))
                .append("_")
                .append((int)(Math.random()*1000))
                .append("_")
                .append((int)(Math.random()*1000))
                .append("_")
                .append(file.getOriginalFilename())
                .toString();
    }
}
