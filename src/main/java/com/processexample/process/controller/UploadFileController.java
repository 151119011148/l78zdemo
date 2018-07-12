package com.processexample.process.controller;

import com.processexample.process.utils.ResultUtil;
import com.processexample.process.utils.FileUtil;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

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

        List<MultipartFile> file1 = filesRequest.getFiles("directory1");
        List<MultipartFile> file2 = filesRequest.getFiles("directory2");
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
