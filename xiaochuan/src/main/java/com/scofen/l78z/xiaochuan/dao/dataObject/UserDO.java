package com.scofen.l78z.xiaochuan.dao.dataObject;


import lombok.Data;

import javax.persistence.*;
import java.util.Date;


/**
 * @Description: TODO
 * @Author gaofeng
 * @Date 2023/9/3 8:40 PM
 **/
@Data
@Entity
@Table(name = "user")
public class UserDO {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "name")
    private String name;

    @Column(name = "password")
    private String password;

    @Column(name = "gmt_create")
    private Date createdTime;

    @Column(name = "gmt_modified")
    private Date modifiedTime;

    @Column(name = "is_removed")
    private Integer isRemoved;

}
