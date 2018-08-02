package com.processexample.process.controller;

import com.processexample.process.utils.ResultUtil;
import com.processexample.process.utils.FileUtil;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Create by  GF  in  12:49 2018/7/9
 * Description:
 * Modified  By:
 */
@RestController
@RequestMapping(value = "/uploadFile")
public class UploadFileController {


    @PostMapping(value = "/uploadImage")
    public ResultUtil uploadImage(HttpServletRequest request,
                                  MultipartHttpServletRequest filesRequest,
                                  @RequestParam(value = "phoneNumber") String userToken)throws Exception{

        List<MultipartFile> file1 = filesRequest.getFiles("file1");
        List<MultipartFile> file2 = filesRequest.getFiles("file2");
        List<MultipartFile> files = new ArrayList<>();
        files.addAll(file1);
        files.addAll(file2);


        return FileUtil.upload(request, userToken, files);

    }


    @PostMapping(value = "/uploadMoreImage")
    public ResultUtil uploadMoreImage(HttpServletRequest request,
                                  MultipartHttpServletRequest filesRequest)throws Exception{

        //适用于所有文件名称若干个一样的情况
        List<MultipartFile> file1 = filesRequest.getFiles("directory1");
        List<MultipartFile> file2 = filesRequest.getFiles("directory2");

        //适用于所有文件名称不一样的情况，否则会漏掉重名的文件
        Map<String, MultipartFile> fileMap = filesRequest.getFileMap();
        MultiValueMap<String, MultipartFile> fileMultiValueMap = filesRequest.getMultiFileMap();
        List<MultipartFile> list = new ArrayList<>();
                fileMultiValueMap.values().forEach(item ->list.add((MultipartFile) item));

        List<MultipartFile> files = new ArrayList<>();
        files.addAll(file1);
        files.addAll(file2);

        return FileUtil.upload(request, files);


    }


    @PostMapping(value = "/uploadSingleImage")
    public ResultUtil uploadSingleImage(HttpServletRequest request,
                                  MultipartHttpServletRequest filesRequest,
                                  @RequestParam(value = "phoneNumber") String userToken)throws Exception{

        List<MultipartFile> files = filesRequest.getFiles("file");

        return FileUtil.upload(request, userToken, files);

    }

}
