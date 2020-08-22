package com.scofen.study.designpattern.utils;

import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.List;

/**
 * Create by  GF  in  12:55 2018/7/9
 * Description:
 * Modified  By:
 */
public class FileUtil {

    private static final String imagePath = "image" + File.separator;

    private static void init(){
        File imageDirectory = new File(imagePath);
        if (!imageDirectory.exists())
            imageDirectory.mkdir();
    }

    public static Result upload(HttpServletRequest request, String userToken, List<MultipartFile> files) throws Exception {
        init();
        String directoryPath = "image" + File.separator + userToken + File.separator;
        //C:\\Users\\GF\\AppData\\Local\\Temp\\tomcat-docbase.4873981236845502674.8080\\15111901148\\
        //directoryPath = request.getSession().getServletContext().getRealPath("/") + userToken + File.separator;

        //CKEditor提交的很重要的一个参数
        //String callback = request.getParameter("CKEditorFuncNum");
        List<String> data = Lists.newArrayList();
        File directory = new File(directoryPath);
        if (!directory.exists())
        {directory.mkdir();}

        if (CollectionUtils.isNotEmpty(files)) {
            for (MultipartFile file : files) {
                if (null != file) {
                    InputStream is = file.getInputStream();
                    String sourceFileName = GenerateNameUtil.getFileName(file);
                    String targetFileName = directoryPath + sourceFileName;
                    //扩展名
                    String extensionName = targetFileName.substring(targetFileName.lastIndexOf(".") + 1);

                    File targetFile = new File(targetFileName);
                    FileOutputStream fos = new FileOutputStream(targetFile);
                    String fileAbsolutePath = targetFile.getAbsolutePath();
                    String[] filenames = fileAbsolutePath.split("//");
                    data.add(fileAbsolutePath);

                    byte[] bs = new byte[1024];
                    int len = is.read(bs);
                    while (len != -1) {
                        fos.write(bs, 0, len);
                        len = is.read(bs);
                    }
                    is.close();
                    fos.flush();
                    fos.close();
                }
            }
        }
        Result result = ResultUtil.success(data);
        File deleteFile = new File(directoryPath);

        fileDelete(deleteFile);
        return result;

    }

    public static void fileDelete(File file) {
        // 判断是否是一个目录, 不是的话跳过, 直接删除; 如果是一个目录, 先将其内容清空.
        if (file.isDirectory()) {
            // 获取子文件/目录
            File[] subFiles = file.listFiles();
            // 遍历该目录
            for (File subFile : subFiles) {
                // 递归调用删除该文件: 如果这是一个空目录或文件, 一次递归就可删除. 如果这是一个非空目录, 多次
                // 递归清空其内容后再删除
                fileDelete(subFile);
            }
        }
        // 删除空目录或文件
        file.delete();
    }


    public static Result upload(HttpServletRequest request, List<MultipartFile> files)  throws Exception{

        init();

        List<String> data = Lists.newArrayList();

        if (CollectionUtils.isNotEmpty(files)) {
            for (MultipartFile file : files) {
                if (null != file) {
                    String directoryPath =  imagePath + file.getName();
                    File directory = new File(directoryPath);
                    if (!directory.exists())
                        directory.mkdir();
                    InputStream is = file.getInputStream();
                    String sourceFileName = GenerateNameUtil.getFileName(file);
                    String targetFileName = directoryPath +File.separator +  sourceFileName;
                    //扩展名
                    String extensionName = targetFileName.substring(targetFileName.lastIndexOf(".") + 1);

                    File targetFile = new File(targetFileName);
                    FileOutputStream fos = new FileOutputStream(targetFile);
                    data.add(targetFile.getAbsolutePath());

                    byte[] bs = new byte[1024];
                    int len = is.read(bs);
                    while (len != -1) {
                        fos.write(bs, 0, len);
                        len = is.read(bs);
                    }
                    is.close();
                    fos.flush();
                    fos.close();
                }
            }
        }
        Result result =  ResultUtil.success(data);

        File deleteFile = new File(imagePath);
        fileDelete(deleteFile);
        return result;

    }

}
