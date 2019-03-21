package org.laziji.commons.mybatis.controller;

import com.alibaba.fastjson.JSON;

import java.io.PrintWriter;
import java.util.Map;

public class ResponseData {

    public static final Status SUCCESS = Status.SUCCESS;
    public static final Status ERROR = Status.ERROR;
    public static final Status NOT_LOGGED_ERROR = Status.NOT_LOGGED_ERROR;
    public static final Status PERMISSION_DENIED_ERROR = Status.PERMISSION_DENIED_ERROR;
    public static final Status SERVER_ERROR = Status.SERVER_ERROR;

    public static final Status SELECT_ERROR = Status.SELECT_ERROR;
    public static final Status UPDATE_ERROR = Status.UPDATE_ERROR;
    public static final Status INSERT_ERROR = Status.INSERT_ERROR;
    public static final Status DELETE_ERROR = Status.DELETE_ERROR;
    public static final Status PARAMS_ERROR = Status.PARAMS_ERROR;
    public static final Status PASSWORD_ERROR = Status.PASSWORD_ERROR;
    public static final Status CAPTCHA_ERROR = Status.CAPTCHA_ERROR;

    public static final ResponseData SUCCESS_RESPONSE = successResponse();
    public static final ResponseData ERROR_RESPONSE = errorResponse();
    public static final ResponseData SELECT_ERROR_RESPONSE = errorResponse(SELECT_ERROR);
    public static final ResponseData UPDATE_ERROR_RESPONSE = errorResponse(UPDATE_ERROR);
    public static final ResponseData INSERT_ERROR_RESPONSE = errorResponse(INSERT_ERROR);
    public static final ResponseData DELETE_ERROR_RESPONSE = errorResponse(DELETE_ERROR);
    public static final ResponseData PARAMS_ERROR_RESPONSE = errorResponse(PARAMS_ERROR);
    public static final ResponseData PASSWORD_ERROR_RESPONSE = errorResponse(PASSWORD_ERROR);
    public static final ResponseData CAPTCHA_ERROR_RESPONSE = errorResponse(CAPTCHA_ERROR);
    public static final ResponseData NOT_LOGGED_ERROR_RESPONSE = errorResponse(NOT_LOGGED_ERROR);
    public static final ResponseData PERMISSION_DENIED_ERROR_RESPONSE = errorResponse(PERMISSION_DENIED_ERROR);
    public static final ResponseData SERVER_ERROR_RESPONSE = errorResponse(SERVER_ERROR);

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

    public static ResponseData errorResponse(Status status) {
        ResponseData response = new ResponseData();
        response.success = false;
        response.status = status;
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

    public void writeAndClose(PrintWriter writer) {
        write(writer);
        writer.close();
    }

    public void write(PrintWriter writer) {
        writer.print(this.toString());
    }

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
        NOT_LOGGED_ERROR(501),
        PERMISSION_DENIED_ERROR(502),
        SERVER_ERROR(503),

        INSERT_ERROR(510),
        DELETE_ERROR(511),
        UPDATE_ERROR(512),
        SELECT_ERROR(513),
        PARAMS_ERROR(514),
        PASSWORD_ERROR(515),
        CAPTCHA_ERROR(516);

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
