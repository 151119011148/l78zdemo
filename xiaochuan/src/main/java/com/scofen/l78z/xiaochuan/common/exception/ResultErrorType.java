package com.scofen.l78z.xiaochuan.common.exception;

import java.util.HashMap;
import java.util.Map;

/**
 * 错误类型
 * @auther yunmu
 */
public enum ResultErrorType {
    /**
     * OK
     */
    OK("__"),
    /**
     * API调用错误
     */
    API("IN"),
    /**
     * 授权错误
     */
    AUTH("AT"),
    /**
     * 数据
     */
    DATA("DT"),
    /**
     * 用户
     */
    USER("US"),
    /**
     * 团队
     */
    GROUP("GR"),
    /**
     * 表达式
     */
    EXPRESS("EP"),
    /**
     * FLOW元数据定义错误
     */
    DEFINITION("DF"),
    /**
     * 执行相关的错误码, 如果一个任务能够被执行, 则可能产生ExecutionError
     */
    EXECUTION("EX"),
    /**
     * 内部错误
     */
    INTERNAL("SY"),
    /**
     * 参数错误
     */
    PA("PA"),
    /**
     * 权限错误
     */
    PERMISSION("PM"),
    /**
     * 登录相关错误
     */
    LOGIN("LG"),
    /**
     * 邮件相关错误
     */
    MAIL("MA"),
    /**
     * 触发器事件错误
     */
    TRIGGER_EVENT("TE"),
    /**
     * 定时任务错误
     */
    SCHEDULE_JOB("SJ"),
    /**
     * 数据库错误
     */
    DATABASE("DB"),


    /**
     *  商业规则错误
     */
    MARKET("MK"),

    /**
     * 千川错误码
     */
    QIANCHUAN("QC"),

    /**
     * 外部接口错误码
     */
    OUT_API("OA"),
    ;
    private String prefix;
    ResultErrorType(String prefix) {
        this.prefix = prefix;
        add(prefix, this);
    }

    public String getPrefix() {
        return prefix;
    }

    private static Map<String, ResultErrorType> types;

    private static void add(String c, ResultErrorType type) {
        if (types == null) {
            types = new HashMap<String, ResultErrorType>();
        }
        if (types.containsKey(c)) {
            throw new IllegalArgumentException("duplicated prefix" + c);
        }
        types.put(c, type);
    }

    public static ResultErrorType fromCharacter(String c) {
        ResultErrorType t = types.get(c);
        if (t == null) {
            throw new IllegalArgumentException("unknown prefix:" + c);
        }
        return t;
    }
}
