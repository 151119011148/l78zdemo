package com.scofen.l78z.xiaochuan.controller.request;


import com.scofen.l78z.xiaochuan.controller.response.ProductVO;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

import static javax.persistence.TemporalType.TIMESTAMP;


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

    private List<ProductVO> products;

    private String fullName;

    private String verificationCode;



}
