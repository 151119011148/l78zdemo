package com.scofen.l78z.xiaochuan.dao.dataObject;


import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

import static javax.persistence.TemporalType.TIMESTAMP;


/**
 * @Description: TODO
 * @Author gaofeng
 * @Date 2023/9/3 8:40 PM
 **/
@Data
@Entity
@Table(name = "mail")
@EntityListeners(AuditingEntityListener.class)
public class MailDO {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "email")
    private String email;

    @Column(name = "telephone")
    private String telephone;

    @Column(name = "country")
    private String country;

    @Column(name = "company_name")
    private String companyName;

    @Column(name = "subject")
    private String subject;

    @Column(name = "message")
    private String message;

    @Column(name = "product_id")
    private String productIds;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "verification_code")
    private String verificationCode;

    @CreatedDate
    @Column(name = "gmt_create", nullable = false, updatable = false)
    @Temporal(TIMESTAMP)
    private Date createdTime;

    @LastModifiedDate
    @Column(name = "gmt_modified", nullable = false)
    @Temporal(TIMESTAMP)
    private Date modifiedTime;

    @Column(name = "is_removed")
    private Integer isRemoved;


}
