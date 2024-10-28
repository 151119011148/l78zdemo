package com.scofen.l78z.xiaochuan.controller.response;

import com.scofen.l78z.xiaochuan.common.exception.ResultCode;
import lombok.Data;


@Data
public class Response<T> {

    private static final long serialVersionUID = 1978486155074524597L;

    private Boolean success;

    private String errorCode;

    private String errorMsg;

    private T data;

    private Pager pager;

    public Response() {
        this.errorCode = ResultCode.SUCCESS.getCode();
        this.setSuccess(true);
    }

    public Response(String errorCode, String msg) {
        this.errorCode = errorCode;
        this.errorMsg = msg;
        if (ResultCode.SUCCESS.getCode().equals(errorCode)) {
            this.setSuccess(true);
        } else {
            this.setSuccess(false);
        }
    }
    public Response(String errorCode, String msgCode, String msg) {
        this.errorCode = errorCode;
        this.errorMsg = msg;
        if (ResultCode.SUCCESS.getCode().equals(errorCode)) {
            this.setSuccess(true);
        } else {
            this.setSuccess(false);
        }
    }

    public Response(ResultCode errorCode, String msg) {
        this.errorCode = errorCode.getCode();
        this.errorMsg = msg;
        if (ResultCode.SUCCESS.equals(errorCode)) {
            this.setSuccess(true);
        } else {
            this.setSuccess(false);
        }
    }

    public static Response clone(Response ipaasResponse) {
        Response Response = new Response();
        Response.setErrorCode(ipaasResponse.getErrorCode());
        Response.setErrorMsg(ipaasResponse.getErrorMsg());
        Response.setPager(ipaasResponse.getPager());
        Response.setSuccess(ipaasResponse.getSuccess());
        return Response;
    }

    public Response withData(T data) {
        this.setData(data);
        return this;
    }

    public Response withSuccess(Boolean isSuccess) {
        this.setSuccess(isSuccess);
        return this;
    }

    public Response withErrorMsg(String message) {
        this.setErrorMsg(message);
        return this;
    }

    public Response withErrorCode(String code) {
        this.setErrorCode(code);
        return this;
    }

    public Response withPager(Pager pager) {
        this.setPager(pager);
        return this;
    }

}
