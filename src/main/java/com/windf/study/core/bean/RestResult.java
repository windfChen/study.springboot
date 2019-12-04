package com.windf.study.core.bean;

public class RestResult {


    public static RestResult result(int code, Object msg) {
        return result(code + "", msg.toString());
    }

    public static RestResult result(String code, String msg) {
        RestResult restResult = new RestResult();
        restResult.setCode(code);
        restResult.setMsg(msg);
        return restResult;
    }

    private String code;
    private String msg;
    private Object data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
