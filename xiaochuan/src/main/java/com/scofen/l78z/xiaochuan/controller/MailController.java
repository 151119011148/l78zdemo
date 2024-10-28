package com.scofen.l78z.xiaochuan.controller;

import com.scofen.l78z.xiaochuan.common.exception.ResultCode;
import com.scofen.l78z.xiaochuan.controller.request.MailParam;
import com.scofen.l78z.xiaochuan.controller.response.Response;
import com.scofen.l78z.xiaochuan.service.MailService;
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
@RequestMapping("/mail")
public class MailController extends BaseController {

    @Resource
    MailService mailService;

    @Resource
    Mapper beanMapper;


    /**
     * 发送邮件
     *
     * @param
     * @return
     */
    @PostMapping("/send")
    public Response<Boolean> send(MailParam param) {

        if (StringUtils.isEmpty(param.getEmail())){
            return new Response<>(ResultCode.PARAM_ERROR.getCode(), "Email is not null!");
        }
        if (StringUtils.isEmpty(param.getSubject()) || StringUtils.isEmpty(param.getMessage())){
            return new Response<>(ResultCode.PARAM_ERROR.getCode(), "Subject and Message is not null!");
        }
        if (StringUtils.isEmpty(param.getCountry()) || StringUtils.isEmpty(param.getFullName())){
            return new Response<>(ResultCode.PARAM_ERROR.getCode(), "Country and FullName is not null!");
        }
        mailService.send(param);
        return new Response<>()
                .withData(Boolean.TRUE)
                .withErrorMsg("邮件发送成功");
    }



}
