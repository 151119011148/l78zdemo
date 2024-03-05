package com.scofen.l78z.xiaochuan.common.constant;

import com.scofen.l78z.xiaochuan.common.exception.ServiceException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

/**
 * @Description: 授权方法枚举
 * @Author gaofeng
 * @Date 4/15/22 6:20 PM
 **/
@AllArgsConstructor
public enum ConnectorServiceEnum {


    /**
     * 旺店通
     */
    WANG_DIAN_TONG("com.wangdiantong.qyb", "wangDianTongQYBManager"),

    /**
     * 快递100
     */
    KUAI_DI_100("com.kuaidi100.Kd100APP.outer", "KuaiDi100Manager"),

   
    ;


    @Getter
    private String connectorId;

    @Getter
    private String serviceName;



    public static ConnectorServiceEnum get(String operation) {
        for (ConnectorServiceEnum operationEnum : ConnectorServiceEnum.values()) {
            if (StringUtils.equals(operationEnum.getConnectorId(), operation)) {
                return operationEnum;
            }
        }
        throw new ServiceException("operation is invalid!");
    }

    public static String getConnectorId(String operation) {
        for (ConnectorServiceEnum operationEnum : ConnectorServiceEnum.values()) {
            if (StringUtils.equals(operationEnum.getConnectorId(), operation)) {
                return operationEnum.getConnectorId();
            }
        }
        throw new ServiceException("operation is invalid!");
    }

    public static String getServiceName(String operation) {
        for (ConnectorServiceEnum operationEnum : ConnectorServiceEnum.values()) {
            if (StringUtils.equals(operationEnum.getConnectorId(), operation)) {
                return operationEnum.getServiceName();
            }
        }
        throw new ServiceException("operation is invalid!");
    }
}
