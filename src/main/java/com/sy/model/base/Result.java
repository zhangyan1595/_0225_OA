package com.sy.model.base;

public class Result<T> extends Page {
    public final static int SUCCESS_CODE=0;
    public final static int FailED_CODE=1;
    public final static String SUCCESS_MSG="sucess";
    public final static String FAILED_MSG="failed";

    private T data;
    private Integer code;
    private String  msg;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }


}
