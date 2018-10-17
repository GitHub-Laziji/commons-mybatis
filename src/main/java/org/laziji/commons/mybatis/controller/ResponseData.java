package org.laziji.commons.mybatis.controller;

import com.alibaba.fastjson.JSON;

public class ResponseData {

    public static final Status SUCCESS = Status.SUCCESS;
    public static final Status ERROR = Status.ERROR;
    public static final Status ERROR_NOT_LOGGED = Status.ERROR_NOT_LOGGED;

    public static final ResponseData SUCCESS_RESPONSE = successResponse();
    public static final ResponseData ERROR_RESPONSE = errorResponse();
    public static final ResponseData SELECT_ERROR_RESPONSE = errorResponse("查询失败");
    public static final ResponseData UPDATE_ERROR_RESPONSE = errorResponse("更新失败");
    public static final ResponseData INSERT_ERROR_RESPONSE = errorResponse("插入失败");
    public static final ResponseData PARAMS_ERROR_RESPONSE = errorResponse("参数错误");
    public static final ResponseData PASSWORD_ERROR_RESPONSE = errorResponse("用户名或密码错误");
    public static final ResponseData CAPTCHA_ERROR_RESPONSE = errorResponse("验证码错误");

    private boolean success;
    private Status status;
    private String message;
    private Object data;


    public static ResponseData successResponse(Object data) {
        ResponseData response = successResponse();
        response.data = data;
        return response;
    }

    public static ResponseData errorResponse(String message) {
        ResponseData response = errorResponse();
        response.message = message;
        return response;
    }

    public static ResponseData errorResponse(Status status,String message) {
        ResponseData response = new ResponseData();
        response.success = false;
        response.status = status;
        response.message = message;
        return response;
    }

    private static ResponseData successResponse() {
        ResponseData response = new ResponseData();
        response.success = true;
        response.status = SUCCESS;
        return response;
    }

    private static ResponseData errorResponse() {
        ResponseData response = new ResponseData();
        response.success = false;
        response.status = ERROR;
        return response;
    }

    private ResponseData() {}

    public Integer getCode() {
        if(status==null){
            return null;
        }
        return status.getCode();
    }

    public boolean isSuccess() {
        return success;
    }

    public Status getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public Object getData() {
        return data;
    }

    public enum Status {
        SUCCESS(200),
        ERROR(500),
        ERROR_NOT_LOGGED(501);

        private int code;

        Status(int code) {
            this.code = code;
        }

        public int getCode() {
            return code;
        }
    }

    @Override
    public String toString(){
        return JSON.toJSONString(this);
    }
}
