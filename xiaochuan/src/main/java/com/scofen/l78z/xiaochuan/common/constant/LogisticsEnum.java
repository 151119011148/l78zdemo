package com.scofen.l78z.xiaochuan.common.constant;

import com.scofen.l78z.xiaochuan.common.exception.ServiceException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

/**
 * @Description: 物流编码枚举
 * @Author gaofeng
 * @Date 4/15/22 6:20 PM
 **/
@AllArgsConstructor
public enum LogisticsEnum {

    /**
     * 快递100
     */
    KUAI_DI_100("com.kuaidi100.Kd100APP.outer", "", "快递100"),

    /**
     * 申通
     */
    WANG_DIAN_TONG("com.wangdiantong.qyb", "", "申通快递"),


   
    ;


    @Getter
    private String connectorId;

    @Getter
    private String logisticsId;

    @Getter
    private String logisticsName;



    public static LogisticsEnum get(String logisticsName) {
        for (LogisticsEnum logisticsEnum : LogisticsEnum.values()) {
            if (StringUtils.equals(logisticsEnum.getLogisticsName(), logisticsName)) {
                return logisticsEnum;
            }
        }
        throw new ServiceException("operation is invalid!");
    }

    public static String getConnectorId(String logisticsName) {
        for (LogisticsEnum logisticsEnum : LogisticsEnum.values()) {
            if (StringUtils.equals(logisticsEnum.getLogisticsName(), logisticsName)) {
                return logisticsEnum.getConnectorId();
            }
        }
        throw new ServiceException("logisticsName is invalid!");
    }

}
