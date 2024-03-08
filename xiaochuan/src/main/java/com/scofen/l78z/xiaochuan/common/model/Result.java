package com.scofen.l78z.xiaochuan.common.model;

import com.scofen.l78z.xiaochuan.common.exception.ResultCode;
import lombok.Data;

import java.io.Serializable;
import java.util.UUID;

/**
 * @Description: TODO
 * @Author gaofeng
 * @Date 2023/9/3 10:16 AM
 **/
@Data
public class Result<T> implements Serializable {

    private static final long serialVersionUID = 1978486155074524597L;
    private String requestId;

    private Boolean success;

    private String code;

    private String message;

    private T data;

    private Pager pager;

    public Result() {
        this.code = ResultCode.SUCCESS.getCode();
        this.requestId = UUID.randomUUID().toString();
        this.setSuccess(true);
    }

    public Result(String code, String msg) {
        this.requestId = UUID.randomUUID().toString();
        this.code = code;
        this.message = msg;
        ResultCode resultCode = ResultCode.valueOfCode(code);
        if (ResultCode.SUCCESS.getCode().equals(code)) {
            this.setSuccess(true);
        } else {
            this.setSuccess(false);
        }
    }
    public Result(String code, String msgCode, String msg) {
        this.requestId = UUID.randomUUID().toString();
        this.code = code;
        this.message = msg;
        if (ResultCode.SUCCESS.getCode().equals(code)) {
            this.setSuccess(true);
        } else {
            this.setSuccess(false);
        }
    }

    public Result(ResultCode code, String msg) {
        this.requestId = UUID.randomUUID().toString();
        this.code = code.getCode();
        this.message = msg;
        if (ResultCode.SUCCESS.equals(code)) {
            this.setSuccess(true);
        } else {
            this.setSuccess(false);
        }
    }

    public static Result clone(Result ipaasResult) {
        Result result = new Result();
        result.setRequestId(ipaasResult.getRequestId());
        result.setCode(ipaasResult.getCode());
        result.setMessage(ipaasResult.getMessage());
        result.setPager(ipaasResult.getPager());
        result.setSuccess(ipaasResult.getSuccess());
        return result;
    }

    public Result withData(T data) {
        this.setData(data);
        return this;
    }

    public Result withSuccess(Boolean isSuccess) {
        this.setSuccess(isSuccess);
        return this;
    }

    public Result withMessage(String message) {
        this.setMessage(message);
        return this;
    }

    public Result withCode(String code) {
        this.setCode(code);
        return this;
    }

    public Result withPager(Pager pager) {
        this.setPager(pager);
        return this;
    }

}
