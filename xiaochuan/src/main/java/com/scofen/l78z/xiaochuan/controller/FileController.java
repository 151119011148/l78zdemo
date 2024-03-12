package com.scofen.l78z.xiaochuan.controller;

import com.scofen.l78z.xiaochuan.common.exception.ResultCode;
import com.scofen.l78z.xiaochuan.controller.response.Response;
import com.scofen.l78z.xiaochuan.service.FileService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description: TODO
 * @Author gaofeng
 * @Date 2023/9/3 9:54 AM
 **/
@RestController
@RequestMapping("/file")
public class FileController extends BaseController {

    @Resource
    private FileService fileService;



    /**
     * 上传
     *
     * @param
     * @return
     */
    @PostMapping("/upload")
    public Response<String> upload(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return new Response<>(ResultCode.LOGIN_PASSWORD_IS_NULL.getCode(), "file is null!");
        }
        String url = fileService.uploadImage(file);
        return new Response<>()
                .withData(url)
                .withErrorMsg("上传成功");
    }

    /**
     * 批量上传
     *
     * @param
     * @return
     */
    @PostMapping("/uploadFiles")
    public Response<List<String>> uploadFiles(@RequestParam("files") List<MultipartFile> files) {
        if (CollectionUtils.isEmpty(files)) {
            return new Response<>(ResultCode.LOGIN_PASSWORD_IS_NULL.getCode(), "file is null!");
        }
        List<String> urls = fileService.uploadImages(files);
        return new Response<>()
                .withData(urls)
                .withErrorMsg("上传成功");
    }



}
