package com.scofen.l78z.xiaochuan.common.exception;


import com.scofen.l78z.xiaochuan.controller.response.Response;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yunmu
 */
public enum ResultCode {

    /**
     * 200 OK
     */
    SUCCESS("__200OK", MsgCode.SUCCESS, "OK"),

    /**
     * 授权验证错误
     */
    AUTH_CHECK_ERROR("AT400CE", MsgCode.AUTH_CHECK_ERROR, "Auth check exception {}"),

    /**
     * 已有授权账号验证错误
     */
    AUTH_VALIDATE_ERROR("AT400VE", MsgCode.AUTH_VALIDATE_ERROR, "Auth validate exception {}"),

    /**
     * 授权验证失败
     */
    AUTH_CHECK_FAILED("AT400CF", MsgCode.AUTH_CHECK_FAILED, "Auth check failed {}"),

    /**
     * 授权刷新token失败
     */
    AUTH_REFRESH_FAILED("AT400RF", MsgCode.AUTH_REFRESH_FAILED, "Auth refresh failed {}"),

    /**
     * 账号已经存在
     */
    AUTH_IS_EXIST("AT400IE", MsgCode.AUTH_IS_EXIST, "Auth is exist {}"),

    /**
     * 账号已经存在
     */
    AUTH_NAME_IS_EXIST("AT400NE", MsgCode.AUTH_NAME_IS_EXIST, "Auth name is exist {}"),

    /**
     * 授权获取预授权失败
     */
    AUTH_PRE_AUTH_FAILED("AT400PF", MsgCode.AUTH_PRE_AUTH_FAILED, "Auth refresh failed {}"),

    /**
     * 授权刷新token失败
     */
    AUTH_GET_AUTHORIZE_URL_FAILED("AT400UF", MsgCode.AUTH_GET_AUTHORIZE_URL_FAILED, "Auth getAuthorizeUrl failed {}"),

    /**
     * 授权获取token失败
     */
    AUTH_GET_ACCESS_TOKEN_FAILED("AT400GF", MsgCode.AUTH_GET_ACCESS_TOKEN_FAILED, "Auth getAccessToken failed {}"),
    /**
     * 授权获取token失败
     */
    AUTH_RELATED_BY_FLOW("AT400RF", MsgCode.AUTH_RELATED_BY_FLOW, "Auth related by flow {}"),



    /**
     * 公共流程定义错误
     */
    DEFINITION_INVALID_FLOW("DF400IF", MsgCode.DEFINITION_INVALID_FLOW, "Invalid flow {}"),

    /**
     * 账号定义不规范
     */

    DEFINITION_INVALID_ASSET("DF400IA", MsgCode.DEFINITION_INVALID_ASSET, "Invalid asset: {}"),

    /**
     * 连接器定义错误
     */
    DEFINITION_INVALID_CONNECTOR("DF400IC", MsgCode.DEFINITION_INVALID_CONNECTOR, "Invalid connector {}"),

    /**
     * Statements错误
     */
    DEFINITION_INVALID_STATEMENTS("DF400IS", MsgCode.DEFINITION_INVALID_STATEMENTS, "Invalid statements {}"),

    /**
     * 流程级联错误
     */
    DEFINITION_INVALID_CASCADE("DF400IC", MsgCode.DEFINITION_INVALID_CASCADE, "Invalid reference `{}` from `{}`: {}"),

    /**
     * 表达式执行错误
     */
    EXPRESSIONS_APPLY_FAILED("EP400AF", MsgCode.EXPRESSIONS_APPLY_FAILED, "Expression processing failed {} {}"),

    /**
     * 无效的用户
     */
    API_INVALID_USER("IN403IU", MsgCode.API_INVALID_USER, "Invalid User {}"),

    /**
     * 无效的接口路径
     */
    API_INVALID_PATH("IN400IP", MsgCode.API_INVALID_PATH, "Invalid Api Path {}"),

    /**
     * 无效的接口参数
     */
    API_INVALID_PARAMETER("IN400PR", MsgCode.API_INVALID_PARAMETER, "Invalid Api Parameter: {}"),

    /**
     * 无效的操作
     */
    API_INVALID_OPERATION("IN400OP", MsgCode.API_INVALID_OPERATION, "Invalid Api Operation: {}"),

    EXECUTION_SAMPLE_EMPTY("EX400SM", MsgCode.EXECUTION_SAMPLE_EMPTY, "Execution sample data empty {}"),

    EXECUTION_SAMPLE_ERROR("EX400SE", MsgCode.EXECUTION_SAMPLE_ERROR, "Execution sample data error {}"),

    EXECUTION_INVALID_PARAMETER("EX400PA", MsgCode.EXECUTION_INVALID_PARAMETER, "Execution failed with invalid Parameter {}"),

    EXECUTION_INVALID_OUTPUT("EX400OU", MsgCode.EXECUTION_INVALID_OUTPUT, "Execution failed with invalid Output {}"),

    EXECUTION_META_NOT_READY("EX504NR", MsgCode.EXECUTION_META_NOT_READY, "Meta not ready {}"),

    EXECUTION_OVER_MAX_CONCURRENT("EX503BZ", MsgCode.EXECUTION_OVER_MAX_CONCURRENT, "Over max concurrent {}"),

    /**
     * 某个Execution超过最大允许步数后报错
     */
    EXECUTE_OVER_MAX_STEP("EX504OS", MsgCode.EXECUTE_OVER_MAX_STEP, "Over max steps: {}"),
    /**
     * 分页查询超过最大允许页数后报错
     */
    EXECUTE_OVER_MAX_PAGE("EX504OP", MsgCode.EXECUTE_OVER_MAX_PAGE, "Over max pages: {}"),
    /**
     * 分页查询超过最大总数据数报错
     */
    EXECUTE_OVER_MAX_DATA_COUNT("EX504OC", MsgCode.EXECUTE_OVER_MAX_DATA_COUNT, "Over max data count: {}"),

    /**
     * 某个flow间隔时间太短，太频繁
     */
    EXECUTE_FREQUENT("EX5041S", MsgCode.DEFINITION_INVALID_FLOW_FREQUENT, "trigger is frequent: {}"),

    /**
     * 流程手动停止
     */
    EXECUTE_FLOW_MANUAL_STOP("EX999FS", MsgCode.EXECUTE_FLOW_MANUAL_STOP, "flow manual stop: {}"),

    /**
     * 某个
     */
    EXECUTE_OVER_MAX_FORKS("EX503OF", MsgCode.EXECUTE_OVER_MAX_FORKS,"Over max forks: {}"),

    EXECUTE_TERMINATED_BY_GATEWAY("EX400TG", MsgCode.EXECUTE_TERMINATED_BY_GATEWAY,"Terminated by gateway: {}"),

    EXECUTE_TRIGGER_NOT_END("EX504TE", MsgCode.EXECUTE_TRIGGER_NOT_END,"Trigger not end"),

    EXECUTE_TRIGGER_FAILED("EX504TF", MsgCode.EXECUTE_TRIGGER_FAILED,"Trigger execute failed:{}"),

    EXECUTE_TRIGGER_ALREADY_EXCHANGE("EX503AE", MsgCode.EXECUTE_TRIGGER_ALREADY_EXCHANGE,"Trigger already responded"),

    EXECUTE_CONNECTOR_FAILED("EX504CF", MsgCode.EXECUTE_CONNECTOR_FAILED,"Connector failed: {}"),

    EXECUTE_FUNCTION_FAILED("EX504FF", MsgCode.EXECUTE_FUNCTION_FAILED,"Function {} execute failed: {}"),

    EXECUTE_SCRIPT_FAILED("EX504SF", MsgCode.EXECUTE_SCRIPT_FAILED,"Script {} execute failed: {}"),

    EXECUTE_REQUEST_FAILED("EX504RF", MsgCode.EXECUTE_REQUEST_FAILED,"Request {} execute failed: {}"),

    EXECUTE_PAGER_REQUEST_FAILED("EX504PF", MsgCode.EXECUTE_PAGER_REQUEST_FAILED,"Pager Request {} execute failed: {}"),

    EXECUTE_LOOP_ALL_REQUEST_FAILED("EX504PF", MsgCode.EXECUTE_LOOP_ALL_REQUEST_FAILED,"LoopAll Request {} execute failed: {}"),

    EXECUTE_DEPENDENCY_FAILED("EX504DF", MsgCode.EXECUTE_DEPENDENCY_FAILED,"Dependency {} execute failed: {}"),

    EXECUTE_INTERNAL_ERROR("EX500XE", MsgCode.EXECUTE_INTERNAL_ERROR,"Execute internal error: {}"),

    EXECUTION_BUSINESS_FAILED("EX400EBF", MsgCode.EXECUTION_BUSINESS_FAILED,"Execution business failed, error:{}"),

    EXECUTION_TIMEOUT("EX504TO", MsgCode.EXECUTION_TIMEOUT,"Execution Timeout {}"),

    EXECUTION_FAILED("EX504F", MsgCode.EXECUTION_FAILED,"Execution failed {}"),

    EXECUTION_MANUAL_END_EVENT("EX505MEE", MsgCode.EXECUTION_MANUAL_END_EVENT,"{}"),

    EXECUTION_NOT_FOUND("EX400NF", MsgCode.EXECUTION_NOT_FOUND,"Execution Not Found {}"),

    TRIGGER_EVENT_PARSE_ERROR("TE400PE", MsgCode.TRIGGER_EVENT_PARSE_ERROR,"Trigger Event Parse Error {}"),

    TRIGGER_EVENT_PUSH_ERROR("TE400PUE", MsgCode.TRIGGER_EVENT_PUSH_ERROR,"Trigger Event Push Error {}"),

    TRIGGER_EVENT_NOT_SUPPORT("TE400NT", MsgCode.TRIGGER_EVENT_NOT_SUPPORT,"Trigger Event Not Support {}"),

    // SSRF unsafe connection
    SECURITY_UNSAFE_CONNECTION("SY400UC", MsgCode.SECURITY_UNSAFE_CONNECTION,"Unsafe connection, url - {}"),



    SERVER_REPLICATING("SY503RP", MsgCode.SERVER_REPLICATING,"Server Meta Data Not Ready!"),

    SYSTEM_ERROR("SY500ER", MsgCode.SYSTEM_ERROR,"Internal Server Error:{}"),

    UNKNOWN_ERROR("SY500UN", MsgCode.UNKNOWN_ERROR,"Unknown Error: {}"),

    CLIENT_ERROR("SY502ER",  MsgCode.CLIENT_ERROR,"Client Error: {}"),

    CLIENT_TIMEOUT("SY504TO",  MsgCode.CLIENT_TIMEOUT,"Client Timeout"),



    /**
     * 数据为空
     */
    DATA_NOT_EXIST("DT400NE",  MsgCode.DATA_NOT_EXIST,"Data Not Exist {}"),

    /**
     * 数据已经存在
     */
    DATA_IS_EXIST("DT400IE", MsgCode.DATA_IS_EXIST,"Data Is Exist {}"),

    /**
     * 数据已修改
     */
    DATA_IS_CHANGED("DT400IC", MsgCode.DATA_IS_CHANGED,"Data Is Changed {}"),

    /**
     * 数据修改失败
     */
    DATA_MODIFY_ERROR("DT400ME", MsgCode.DATA_MODIFY_ERROR, "Data Modify Error {}"),

    /**
     * 用户不存在
     */
    USER_NOT_EXIST("US400NE",  MsgCode.USER_NOT_EXIST,"User Not Exist {}"),
    /**
     * 用户存在
     */
    USER_IS_EXIST("US400IE",  MsgCode.USER_IS_EXIST,"User Is Exist {}"),

    /**
     * 用户未创建团队
     */
    GROUP_NOT_EXIST("GR400NE",  MsgCode.GROUP_NOT_EXIST,"Group Not Exist {}"),

    /**
     * 用户已创建团队
     */
    GROUP_IS_EXIST("GR400IE",  MsgCode.GROUP_IS_EXIST,"Group Is Exist {}"),

    /**
     * 参数错误
     */
    PARAM_ERROR("PA400ER", MsgCode.PARAM_ERROR,"{}:{}"),

    /**
     * 参数校验失败
     */
    PARAM_CHECK_FAILED("PA400CF", MsgCode.PARAM_ERROR,"{}"),

    NO_PERMISSION_TO_INVOKE_FLOW("PM403NP",  MsgCode.NO_PERMISSION_TO_INVOKE_FLOW,"Unauthorized"),

    /**
     * 密码错误
     */
    LOGIN_WRONG_PASSWORD("PM400WP", MsgCode.LOGIN_WRONG_PASSWORD,"Wrong Password"),

    /**
     * 密码错误
     */
    LOGIN_PASSWORD_ALREADY_SET("PM400AS", MsgCode.LOGIN_PASSWORD_ALREADY_SET,"Password already set"),

    /**
     * 密码错误
     */
    LOGIN_PASSWORD_IS_NULL("PM400IN", MsgCode.LOGIN_PASSWORD_IS_NULL,"DB Password Is Null"),

    /**
     * 需要登录
     */
    LOGIN_NEED_LOGIN("PM400NL", MsgCode.LOGIN_NEED_LOGIN, "Need Login"),

    /**
     * 不在内测范围内
     */
    USER_NOT_IN_BLACKLIST("PM400BT", MsgCode.USER_NOT_IN_BLACKLIST, "Need Auth"),

    /**
     * 没有权限
     */
    PERMISSION_DENIED("PM403PD", MsgCode.PERMISSION_DENIED,"Permission Denied {}"),


    /**
     * ADMIN_ACL_NOT_EXIST
     */
    ADMIN_ACL_NOT_EXIST("PM403NA", MsgCode.ADMIN_ACL_NOT_EXIST,"Admin Acl Not Exist {}"),

    /**
     * ACL_NAME_EXIST
     */
    ACL_NAME_EXIST("PM403ANE", MsgCode.ACL_NAME_EXIST,"Acl Name Exist {}"),

    /**
     * ADMIN_NOT_EXIST
     */
    ADMIN_NOT_EXIST("PM403AN", MsgCode.ADMIN_NOT_EXIST,"Admin Not Exist"),

    /**
     * ADMIN_CAN_NOT_BE_DELETED
     */
    ADMIN_CAN_NOT_BE_DELETED("PM403AND", MsgCode.ADMIN_CAN_NOT_BE_DELETED,"Admin Can Not Be Deleted"),

    /**
     * SYSTEM_ACL_CAN_NOT_UPDATE
     */
    SYSTEM_ACL_CAN_NOT_UPDATE("PM403ANU", MsgCode.SYSTEM_ACL_CAN_NOT_UPDATE,"System Acl Can Not Update"),


    //----------登录注册相关-----------
    /**
     * 验证码已发送
     */
    SMS_CODE_ALREADY_SEND("LG400AS",  MsgCode.SMS_CODE_ALREADY_SEND,"Sms Code Already Send"),

    SMS_CODE_ALREADY_EXPIRED("LG400AE",  MsgCode.SMS_CODE_ALREADY_EXPIRED,"Sms Code Already EXPIRED"),

    SMS_CODE_WRAONG("LG400WR",  MsgCode.SMS_CODE_WRAONG,"Sms Code Wrong"),

    SMS_SEND_ERROR("LG400SR",  MsgCode.SMS_SEND_ERROR,"sms send error"),


    //---------smtp邮件发送---------
    MAIL_SMTP_SEND_FAILED("MA400SF", MsgCode.MAIL_SMTP_SEND_FAILED,"send mail failed"),

    MAIL_SMTP_SEND_TEST_FAILED("MA400TF", MsgCode.MAIL_SMTP_SEND_TEST_FAILED,"send test mail failed"),


    //---------imap查询邮件---------
    MAIL_IMAP_QUERY_FAILED("MA400QF", MsgCode.MAIL_IMAP_QUERY_FAILED, "query mail failed"),


    //---------schedule job 相关---------
    SHEDULE_JOB_FAILED("SJ400FA", MsgCode.SHEDULE_JOB_FAILED,"任务操作失败.{}"),


    //------db相关----
    TEST_DB_CONNECTION_FAILED("DB400CF", MsgCode.TEST_DB_CONNECTION_FAILED,"数据库连接失败，请检查参数是否正确！"),


    //---------market 软件订单系统---------
    ORDER_OUTSTANDING_PAYMENT("SY400AP", MsgCode.ORDER_OUTSTANDING_PAYMENT,"该组织有待支付记录，请刷新页面重新确认！"),

    FLOWS_RIGHT_INTERESTS_CHECK("SY400FS", MsgCode.FLOWS_RIGHT_INTERESTS_CHECK,"Linkup数量已达版本上限！"),

    ASSERT_RIGHT_INTERESTS_CHECK("SY400AT", MsgCode.ASSERT_RIGHT_INTERESTS_CHECK,"应用授权数量已达版本上限！"),
    MARKET_SINGLE_TABLE_ROWS_MAX("MK400UM", MsgCode.MARKET_SINGLE_TABLE_ROWS_MAX,"单表数据行数已达版本上限！"),
    MARKET_TABLE_ROWS_MAX("MK400UM", MsgCode.MARKET_TABLE_ROWS_MAX,"数据表总行数已达版本上限！"),
    MARKET_ACTIVE_FLOWS_MAX("MK400UM", MsgCode.MARKET_ACTIVE_FLOWS_MAX,"激活流程数已达版本上限！"),
    MARKET_FLOWS_TRIGGERS_MAX("MK400TM", MsgCode.MARKET_FLOWS_TRIGGERS_MAX,"流程每月执行次数已达版本上限！"),
    MARKET_GROUP_USERS_MAX("MK400UM", MsgCode.MARKET_GROUP_USERS_MAX,"团队成员数量已达版本上限！"),
    MARKET_FILE_SPACE_MAX("MK400UM", MsgCode.MARKET_FILE_SPACE_MAX,"文件上传空间已达版本上限！"),
    MARKET_ACTION_EXECUTIONS_MAX("MK400EM", MsgCode.MARKET_ACTION_EXECUTIONS_MAX,"接口调用次数已达到版本上限！"),
    FEATURE_BALANCE_UPPER_LIMIT("MK400UL", MsgCode.FEATURE_BALANCE_UPPER_LIMIT,"功能已达版本上限！"),
    FEATURE_MAX_UPPER_LIMIT("MK400UL", MsgCode.FEATURE_MAX_UPPER_LIMIT,"功能已达版本上限！"),
    MARKET_INSTANCE_EXPIRATION("MK400IE", MsgCode.MARKET_INSTANCE_EXPIRATION,"当前版本已到期！"),


    //-----app-qianchuan-code-----
    APP_QIANCHUAN_RESULT_EMPTY("QC400RE", MsgCode.APP_QIANCHUAN_RESULT_EMPTY,"千川三方接口返回数据为空！"),

    APP_QIANCHUAN_RESULT_FAILED("QC400RF", MsgCode.APP_QIANCHUAN_RESULT_FAILED,"{}失败,原因:{}"),

    APP_QIANCHUAN_BIZ_FAILED("QC400BF", MsgCode.APP_QIANCHUAN_BIZ_FAILED,"千川业务执行异常！"),

    APP_QIANCHUAN_RIGHT_INTERESTS_CHECK("QC400TF", MsgCode.APP_QIANCHUAN_RIGHT_INTERESTS_CHECK,"{}"),

    //-----请求外部接口失败-----
    OUT_API_RETURN_FAILED("OA400RF", MsgCode.OUT_API_RETURN_FAILED,"{}"),

    FLOW_META_REMOVE_ASSET_ERROR("SY400ER", MsgCode.FLOW_META_REMOVE_ASSET_ERROR,"{}"),

    ;

    private String code;

    private int statusCode;

    private ResultErrorType errorType;

    private MsgCode msgCode;

    private String format;

    ResultCode(String code, MsgCode msgCode, String format) {
        this.code = code;
        this.msgCode = msgCode;
        this.format = format;
        this.statusCode = Integer.parseInt(code.substring(2, 5));
        this.errorType = ResultErrorType.fromCharacter(code.substring(0, 2));
    }

    public String getCode() {
        return this.code;
    }

    public MsgCode getMsgCode() {
        return msgCode;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public ResultErrorType getErrorType() {
        return errorType;
    }

    public void setErrorType(ResultErrorType errorType) {
        this.errorType = errorType;
    }


    public void setMsgCode(MsgCode msgCode) {
        this.msgCode = msgCode;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public Response toIpaasResult() {
        Response response = new Response();
        response.setErrorCode(this.code);
        response.setErrorMsg(this.msgCode.getMsg());
        if (ResultCode.SUCCESS.code.equals(this.code)) {
            response.setSuccess(true);
        } else {
            response.setSuccess(false);
        }
        return response;
    }

    public static ResultCode valueOfCode(String code) {

        for (ResultCode resultCode : ResultCode.values()) {
            if (resultCode.getCode().equals(code)) {
                return resultCode;
            }
        }
        return null;
    }

    public Response toIpaasResult(Object... args) {
        Response response = new Response();
        response.setErrorCode(this.code);
        response.setErrorMsg(formatMessage(args));
        if (ResultCode.SUCCESS.code.equals(this.code)) {
            response.setSuccess(true);
        } else {
            response.setSuccess(false);
        }
        return response;
    }

    public static ResultCode valueOfMsgCode(String msgCode) {

        for (ResultCode resultCode : ResultCode.values()) {
            if (resultCode.getMsgCode().equals(msgCode)) {
                return resultCode;
            }
        }
        return null;
    }


    public int getStatusCode() {
        return statusCode;
    }


    public String formatMessage(Object... args) {
        if (args == null || args.length == 0) {
            return format;
        }
        String s = format;
        for (Object a : args) {
            try {
                s = s.replaceFirst("\\{}", a != null ? a.toString() : "");
            } catch (Exception ex) {
                s = s + (a != null ? a.toString() : "");
            }
        }
        return s;
    }

    private static Map<String, ResultCode> codes;

    private static void add(ResultCode e) {
        if (codes == null) {
            codes = new HashMap<>();
        }
        if (codes.containsKey(e.code)) {
            throw new RuntimeException("Duplicate code: " + e.code);
        }
        codes.put(e.code, e);
    }

    public static ResultCode ofCode(String s) {
        return codes.get(s);
    }


}
