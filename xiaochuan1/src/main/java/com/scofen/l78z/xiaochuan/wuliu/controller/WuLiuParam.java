package com.scofen.l78z.xiaochuan.wuliu.controller;

import com.scofen.l78z.xiaochuan.common.model.AccessParam;
import lombok.Data;

/**
 * @Description: TODO
 * @Author gaofeng
 * @Date 2023/9/4 9:13 PM
 **/
@Data
public class WuLiuParam extends AccessParam {

    /**
     * 物流公司编号
     */
    private String logisticsId;

    /**
     * 物流公司名称
     */
    private String logisticsName;

    /**
     * 物流单号
     */
    private String logisticsNo;


}
