package org.laziji.commons.mybatis.controller;

public class ResponseData {

    public static final Status SUCCESS = Status.SUCCESS;
    public static final Status ERROR = Status.ERROR;
    public static final Status ERROR_NOT_LOGGED = Status.ERROR_NOT_LOGGED;

    private boolean success;
    private Status status;
    private String message;
    private Object data;


    public static ResponseData successResponse(Object data) {
        ResponseData requestJson = successResponse();
        requestJson.setData(data);
        return requestJson;
    }

    public static ResponseData successResponse() {
        ResponseData requestJson = new ResponseData();
        requestJson.setSuccess(true);
        requestJson.setStatus(SUCCESS);
        return requestJson;
    }

    public static ResponseData errorResponse(String message) {
        ResponseData requestJson = errorResponse();
        requestJson.setMessage(message);
        return requestJson;
    }

    public static ResponseData errorResponse() {
        ResponseData requestJson = new ResponseData();
        requestJson.setSuccess(false);
        requestJson.setStatus(ERROR);
        return requestJson;
    }

    public ResponseData() {
    }

    public Integer getCode() {
        if(status==null){
            return null;
        }
        return status.getCode();
    }
    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
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
}
