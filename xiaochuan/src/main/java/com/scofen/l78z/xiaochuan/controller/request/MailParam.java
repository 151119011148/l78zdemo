package com.scofen.l78z.xiaochuan.controller.request;


import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


/**
 * @Description: TODO
 * @Author gaofeng
 * @Date 2023/9/3 8:40 PM
 **/
@Data
public class MailParam {

    private String email;

    private String telephone;

    private String country;

    private String companyName;

    private String subject;

    private String message;

    private String products;

    private String fullName;

    private String verificationCode;

    private List<MultipartFile> files;

}
