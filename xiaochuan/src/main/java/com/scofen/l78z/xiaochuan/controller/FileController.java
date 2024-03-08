package com.scofen.l78z.xiaochuan.controller;

import com.scofen.l78z.xiaochuan.common.exception.ResultCode;
import com.scofen.l78z.xiaochuan.common.exception.ServiceException;
import com.scofen.l78z.xiaochuan.controller.request.UserParam;
import com.scofen.l78z.xiaochuan.controller.response.Response;
import com.scofen.l78z.xiaochuan.controller.response.UserVO;
import com.scofen.l78z.xiaochuan.dao.dataObject.UserDO;
import com.scofen.l78z.xiaochuan.service.FileService;
import com.scofen.l78z.xiaochuan.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.dozer.Mapper;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

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
        String url = "http://" + getIp() + fileService.upload(file);
        return new Response<>()
                .withData(url)
                .withErrorMsg("上传成功");
    }



}
