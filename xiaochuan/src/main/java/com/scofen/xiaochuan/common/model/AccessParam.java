package com.scofen.xiaochuan.common.model;

import lombok.Data;

import java.io.Serializable;

/**
 * @Description: TODO
 * @Author gaofeng
 * @Date 2023/9/3 8:25 PM
 **/
@Data
public class AccessParam implements Serializable {

    /**
     * 平台key
     */
    private String connectorKey;

    /**
     * 授权key
     */
    private String accessKey;

    /**
     * 调用接口
     */
    private String operation;

}
