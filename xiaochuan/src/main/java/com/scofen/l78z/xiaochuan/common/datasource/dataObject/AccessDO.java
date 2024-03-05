package com.scofen.l78z.xiaochuan.common.datasource.dataObject;


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
@Table(name = "access")
public class AccessDO {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "access_id")
    private String accessId;

    @Column(name = "connector_key")
    private String connectorKey;

    @Column(name = "access_key")
    private String accessKey;

    @Column(name = "access_info")
    private String accessInfo;

    @Column(name = "gmt_create")
    private Date createdTime;

    @Column(name = "gmt_modified")
    private Date modifiedTime;

    private Integer isRemoved;

}
