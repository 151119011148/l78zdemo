package com.scofen.xiaochuan.common.exception;


/**
 * @author yunmu
 */
public enum MsgCode {
    /**
     * 成功返回
     */
    SUCCESS("result.success", "执行成功"),


    /**
     * 授权验证异常
     */
    AUTH_CHECK_ERROR("auth.authCheckError", "账号授权验证失败，请重新填写账号信息"),

    /**
     * 已有授权验证异常
     */
    AUTH_VALIDATE_ERROR("auth.authValidateError", "账号授权验证失败，请重新填写账号信息"),

    /**
     * 授权验证失败
     */
    AUTH_CHECK_FAILED("auth.authCheckFailed", "账号授权验证失败，请重新填写账号信息"),

    /**
     * 授权刷新token失败
     */
    AUTH_REFRESH_FAILED("auth.authRefreshFailed", "账号刷新token失败"),

    /**
     * 账号已经存在
     */
    AUTH_IS_EXIST("auth.isExist", "账号已被其他组织人员使用"),

    /**
     * 账号名称已经存在
     */
    AUTH_NAME_IS_EXIST("auth.nameIsExist", "账号名称已经存在，请更换账号名称"),

    /**
     * 预授权失败
     */
    AUTH_PRE_AUTH_FAILED("auth.authPreAuthFailed", "获取预授权信息失败"),

    /**
     * 授权获取授权链接失败
     */
    AUTH_GET_AUTHORIZE_URL_FAILED("auth.getAuthorizeUrlFailed", "获取授权链接失败"),

    /**
     * 授权获取Token失败
     */
    AUTH_GET_ACCESS_TOKEN_FAILED("auth.getAccessTokenFailed", "获取授权Token失败"),
    /**
     * 授权已经被其他流程关联
     */
    AUTH_RELATED_BY_FLOW("auth.getAccessTokenFailed", "当前授权账号已经在流程中使用，请先删除流程"),

    /**
     * 流程定义不合法
     */
    DEFINITION_INVALID_FLOW("definition.invalidFlow", "流程定义不符合规范"),

    /**
     * 流程定义不合法，触发太频繁
     */
    DEFINITION_INVALID_FLOW_FREQUENT("definition.invalidFlow.frequent", "触发太频繁"),

    /**
     * 流程手动停止
     */
    EXECUTE_FLOW_MANUAL_STOP("flow.manualStop", "手动停止流程"),

    /**
     * 流程定义不合法，触发太频繁
     */
    FLOW_META_REMOVE_ASSET_ERROR("flow.meta.remove.asset.error", "流程取消关联账号"),
    /**
     * 资产账户定义不合法
     */
    DEFINITION_INVALID_ASSET("definition.invalidFlow", "账号定义不符合规范"),

    /**
     * 连接器定义不合法
     */
    DEFINITION_INVALID_CONNECTOR("definition.invalidConnector", "连接器定义不符合规范"),

    /**
     * 表达式定义不合法
     */
    DEFINITION_INVALID_STATEMENTS("definition.invalidStatements", "表达式定义不符合规范"),

    /**
     * 流程级联定义错误
     */
    DEFINITION_INVALID_CASCADE("definition.invalidCascade", "流程级联定义错误"),
    /**
     * 表达式执行错误
     */
    EXPRESSIONS_APPLY_FAILED("expression.applyFailed", "表达式执行错误"),

    /**
     * 无效的用户
     */
    API_INVALID_USER("api.invalidUser", "无效的用户"),

    /**
     * 无效的接口路径
     */
    API_INVALID_PATH("api.invalidPath", "无效的接口路径"),

    /**
     * 无效的接口参数
     */
    API_INVALID_PARAMETER("api.invalidParameter", "无效的接口参数"),

    /**
     * 无效的操作
     */
    API_INVALID_OPERATION("api.invalidOperation", "无效的操作"),

    /**
     * 获取样本数据为空
     */
    EXECUTION_SAMPLE_EMPTY("execution.sampleEmpty", "获取样本数据失败，没有获取到数据，请完成一个事件的执行"),
    /**
     * 获取样本数据出错
     */
    EXECUTION_SAMPLE_ERROR("execution.sampleError", "获取样本数据出错，请完成一个事件的执行"),

    /**
     * 无效的执行参数
     */
    EXECUTION_INVALID_PARAMETER("execution.invalidParameter", "无效的执行参数"),

    /**
     * 无效的输出
     */
    EXECUTION_INVALID_OUTPUT("execution.invalidOutput", "无效的输出"),

    /**
     * 依赖的执行数据没有完成加载
     */
    EXECUTION_META_NOT_READY("execution.metaNotReady", "依赖的执行数据没有完成加载"),

    /**
     * 执行超过最大并发
     */
    EXECUTION_OVER_MAX_CONCURRENT("execution.overMaxConcurrent", "执行超过最大并发"),

    /**
     * 执行超过最大步数
     */
    EXECUTE_OVER_MAX_STEP("execution.overMaxStep", "执行超过最大步数"),
    /**
     * 执行超过最大页数
     */
    EXECUTE_OVER_MAX_PAGE("execution.overMaxPage", "批量查询数据超过最大页数"),
    /**
     * 执行超过最大页数
     */
    EXECUTE_OVER_MAX_DATA_COUNT("execution.overMaxDataCount", "批量查询数据超过最大数据处理数"),

    /**
     * 执行超过最大分支数
     */
    EXECUTE_OVER_MAX_FORKS("execution.overMaxForks", "执行超过最大分支数"),

    /**
     * 执行被网关终端
     */
    EXECUTE_TERMINATED_BY_GATEWAY("execution.terminatedByGateway", "执行被网关终端"),

    /**
     * 触发器没有正常结束
     */
    EXECUTE_TRIGGER_NOT_END("execution.executeTriggerNotEnd", "触发器没有正常结束"),

    /**
     * 触发器执行失败
     */
    EXECUTE_TRIGGER_FAILED("execution.executeTriggerFailed", "触发器执行失败"),

    /**
     * 触发对象已被触发
     */
    EXECUTE_TRIGGER_ALREADY_EXCHANGE("execution.triggerAlreadyExchange", "触发对象已被触发"),

    /**
     * 执行连接器失败
     */
    EXECUTE_CONNECTOR_FAILED("execution.executeConnectorFailed", "执行连接器失败"),

    /**
     * 执行方法函数失败
     */
    EXECUTE_FUNCTION_FAILED("execution.executeFunctionFailed", "执行方法函数失败"),

    /**
     * 执行脚本执行器失败
     */
    EXECUTE_SCRIPT_FAILED("execution.executeScriptFailed", "执行脚本执行器失败"),

    /**
     * 执行请求失败
     */
    EXECUTE_REQUEST_FAILED("execution.executeRequestFailed", "执行网络请求失败"),

    /**
     * 执行分页查询请求失败
     */
    EXECUTE_PAGER_REQUEST_FAILED("execution.executeRequestFailed", "批量执行网络请求失败"),
    /**
     * 逐条处理查询异常
     */
    EXECUTE_LOOP_ALL_REQUEST_FAILED("execution.executeLoopAllFailed", "执行逐条处理异常"),

    /**
     * 执行依赖失败
     */
    EXECUTE_DEPENDENCY_FAILED("execution.executeDependencyFailed", "执行依赖操作失败"),

    /**
     * 执行内部错误
     */
    EXECUTE_INTERNAL_ERROR("execution.executeInternalError", "执行过程中系统内部发生错误"),

    /**
     * 执行发生业务错误
     */
    EXECUTION_BUSINESS_FAILED("execution.executionBusinessFailed", "执行发生业务错误，请检查流程执行结果"),

    /**
     * 执行超时
     */
    EXECUTION_TIMEOUT("execution.executionTimeout", "执行超时"),
    /**
     * 执行失败
     */
    EXECUTION_FAILED("execution.executionFailed", "执行流程失败"),

    /**
     * 手动结束节点失败
     */
    EXECUTION_MANUAL_END_EVENT("execution.executionManualEndEvent", "手动结束节点失败"),

    /**
     * 执行记录没有找到
     */
    EXECUTION_NOT_FOUND("execution.executionNotFound", "执行记录没有找到"),

    /**
     * 触发器解析失败
     */
    TRIGGER_EVENT_PARSE_ERROR("trigger.eventParseError", "触发器事件解析失败"),

    /**
     * 触发器事件推送失败
     */
    TRIGGER_EVENT_PUSH_ERROR("trigger.eventPushError", "触发器事件推送失败"),

    /**
     * 触发器事件不支持
     */
    TRIGGER_EVENT_NOT_SUPPORT("trigger.eventNotSupport", "触发器事件不支持"),

    /**
     * 不安全的连接
     */
    SECURITY_UNSAFE_CONNECTION("execution.securityUnsafeConnection", "发现不安全的连接"),

    /**
     * 系统数据正在同步加载
     */
    SERVER_REPLICATING("system.serverReplicating", "系统数据正在同步加载"),

    /**
     * 系统内部错误
     */
    SYSTEM_ERROR("system.serverInternalError", "系统内部错误"),

    /**
     * 未知错误
     */
    UNKNOWN_ERROR("system.unknownError", "未知错误"),

    /**
     * 调用远程应用错误
     */
    CLIENT_ERROR("system.clientError", "调用远程应用错误"),

    /**
     * 调用远程应用超时
     */
    CLIENT_TIMEOUT("system.clientTimeout", "调用远程应用超时"),

    /**
     * 密码错误
     */
    LOGIN_WRONG_PASSWORD("login.wrongPassword", "密码错误，请重新输入密码"),
    /**
     * 密码已经设置
     */
    LOGIN_PASSWORD_ALREADY_SET("login.passWordAlreadySet", "密码已经设置"),
    /**
     * 还未设置密码
     */
    LOGIN_PASSWORD_IS_NULL("login.passwordIsNull", "还未设置登录密码，请先设置密码后再登录"),
    /**
     * 需要登录
     */
    LOGIN_NEED_LOGIN("login.needLogin", "当前访问需要登录，请登录后重试"),
    /**
     * 白名单控制，存储在redis中
     */
    USER_NOT_IN_BLACKLIST("login.needInnerTestAuth", "只有白名单中的账户才可访问"),
    /**
     * 没有权限执行流程
     */
    NO_PERMISSION_TO_INVOKE_FLOW("permission.noPermissionToInvokeFlow", "您没有执行当前流程权限"),
    /**
     * 没有权限
     */
    PERMISSION_DENIED("permission.permissionDenied", "您没有当前访问或者操作的权限"),
    /**
     * 参数错误
     */
    PARAM_ERROR("param.paramError", "参数错误，请重新检查参数后重试"),

    /**
     * 数据为空
     */
    DATA_NOT_EXIST("data.dataNotExist", "数据不存在或者为空"),

    /**
     * 用户不存在
     */
    USER_NOT_EXIST("user.userNotExist", "用户不存在"),

    /**
     * 用户未创建团队
     */
    GROUP_NOT_EXIST("user.groupNotExist", "用户未创建团队"),

    /**
     * 用户已有团队
     */
    GROUP_IS_EXIST("user.groupIsExist", "用户已创建团队"),

    /**
     * 用户已经存在
     */
    USER_IS_EXIST("user.userIsExist", "用户已经存在，请更换手机重新注册，或者直接登录"),

    /**
     * 数据已经存在
     */
    DATA_IS_EXIST("data.dataIsExist", "当前数据已经存在"),
    /**
     * 数据已修改
     */
    DATA_IS_CHANGED("data.dataIsChanged", "数据已经被修改，请刷新重试"),
    /**
     * 数据修改失败
     */
    DATA_MODIFY_ERROR( "data.dataModifyError","数据修改失败"),
    /**
     * 无效的证书
     */
    INVALID_LICENCE("license.invalidLicence","许可已经失效"),
    /**
     * ADMIN_ACL_NOT_EXIST
     */
    ADMIN_ACL_NOT_EXIST("permission.adminAclNotExist","管理员权限不存在"),
    /**
     * ACL_NAME_EXIST
     */
    ACL_NAME_EXIST("permission.aclNameExist","权限名称已存在"),
    /**
     * ADMIN_NOT_EXIST
     */
    ADMIN_NOT_EXIST( "permission.adminNotExist","超级管理员不存在"),
    /**
     * ADMIN_CAN_NOT_BE_DELETED
     */
    ADMIN_CAN_NOT_BE_DELETED("permission.adminCanNotBeDeleted","管理员账号不能被删除"),
    /**
     * SYSTEM_ACL_CAN_NOT_UPDATE
     */
    SYSTEM_ACL_CAN_NOT_UPDATE( "permission.systemAclCanNotUpdate","系统权限不能被删除"),

    /**
     * 验证码已发送
     */
    SMS_CODE_ALREADY_SEND( "sms.smsCodeAlreadySend","验证码已发送，请勿频繁操作"),
    /**
     * 验证码已过期
     */
    SMS_CODE_ALREADY_EXPIRED( "sms.smsCodeAlreadyExpired","验证码已失效，请重新发送验证码"),
    /**
     * 验证码错误
     */
    SMS_CODE_WRAONG( "sms.smsCodeWrong","验证码错误，请输入正确的验证码"),
    /**
     * 验证码错误
     */
    SMS_SEND_ERROR( "sms.smsError","短信发送失败"),
    /**
     * smtp邮件发送失败
     */
    MAIL_SMTP_SEND_FAILED( "mail.smtpSendFailed","邮件发送失败"),
    /**
     * 验证smtp账号是否可用
     */
    MAIL_SMTP_SEND_TEST_FAILED( "mail.smtpSendTestFailed","账号验证失败,请检查参数是否正确"),
    /**
     * imap查询邮件失败
     */
    MAIL_IMAP_QUERY_FAILED( "mail.imapQueryFailed","查询邮件失败"),

    /**
     * 任务操作失败
     */
    SHEDULE_JOB_FAILED( "sheduleJobFailed","任务操作失败"),
    /**
     * 任务操作失败
     */
    TEST_DB_CONNECTION_FAILED( "mail.dbConnectionFail","数据库连接失败，请检查参数是否正确"),

    //-----market 软件订购应用
    /**
     * 存在待支付订单
     */
    ORDER_OUTSTANDING_PAYMENT( "ORDER_OUTSTANDING_PAYMENT","该组织待支付记录，请刷新页面重新确认！"),

    /**
     * 流程数量卡控
     */
    FLOWS_RIGHT_INTERESTS_CHECK("flows_right_interests_check", "Linkup数量已达版本上限！"),

    /**
     * 授权数量卡控
     */
    ASSERT_RIGHT_INTERESTS_CHECK("flows_right_interests_check","应用授权数量已达版本上限！"),

    /**
     *每月流程执行次数上线
     * */
    MARKET_FLOWS_TRIGGERS_MAX("MARKET_FLOWS_TRIGGERS_MAX","流程每月执行次数已达版本上限！"),

    MARKET_SINGLE_TABLE_ROWS_MAX("MARKET_SINGLE_TABLE_ROWS_MAX","单表数据行数已达版本上限！"),

    MARKET_TABLE_ROWS_MAX("MARKET_TABLE_ROWS_MAX","数据表总行数已达版本上限！"),

    MARKET_ACTIVE_FLOWS_MAX("MARKET_ACTIVE_FLOWS_MAX","激活流程数已达版本上限！"),

    MARKET_ACTION_EXECUTIONS_MAX("MARKET_ACTION_EXECUTIONS_MAX","接口调用次数已达到版本上限！"),

    /**
     * 团队成员数量已达版本上限
     */
    MARKET_GROUP_USERS_MAX("MARKET_GROUP_USERS_MAX","团队成员数量已达版本上限！"),
    FEATURE_BALANCE_UPPER_LIMIT("FEATURE_BALANCE_UPPER_LIMIT","功能已达版本上限！"),
    FEATURE_MAX_UPPER_LIMIT("FEATURE_MAX_UPPER_LIMIT","功能已达版本上限！"),
    MARKET_INSTANCE_EXPIRATION("MARKET_INSTANCE_EXPIRATION","当前版本已到期！"),

    /**
     * 文件上传空间已达版本上限
     */
    MARKET_FILE_SPACE_MAX("MARKET_FILE_SPACE_MAX","文件上传空间已达版本上限！"),
    //----------app_qianchuan_code-------
    /**
     * 千川数据返回为控
     */
    APP_QIANCHUAN_RESULT_EMPTY( "qianchuan.appQianchuanResultEmpty","千川三方接口返回数据为空!"),

    /**
     * 千川数据返回失败
     */
    APP_QIANCHUAN_RESULT_FAILED( "qianchuan.appQianchuanResultFailed","千川三方接口返回失败！"),

    /**
     * 千川业务执行异常
     */
    APP_QIANCHUAN_BIZ_FAILED( "qianchuan.appQianchuanBizFailed","千川业务执行异常！"),

    APP_QIANCHUAN_RIGHT_INTERESTS_CHECK( "qianchuan.qianchuanRightInterestsCheck","权益卡点"),


    OUT_API_RETURN_FAILED( "outApiReturnFailed","外部接口返回失败"),

    ;

    private String msgCode;

    private String msg;

    MsgCode(String msgCode, String msg) {
        this.msgCode = msgCode;
        this.msg = msg;
    }

    public String getMsgCode() {
        return msgCode;
    }

    public void setMsgCode(String msgCode) {
        this.msgCode = msgCode;
    }

    public String getMsg() {
        return this.msg;
    }

    public static MsgCode valueOfCode(String code) {
        for (MsgCode msgCode : MsgCode.values()) {
            if (msgCode.getMsgCode().equals(code)) {
                return msgCode;
            }
        }
        return null;
    }
}
