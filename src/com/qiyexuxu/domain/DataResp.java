package com.qiyexuxu.domain;

public class DataResp<T> {
    private String code;
    private String msg;
    private T data;

    public DataResp() {
    }

    public DataResp(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public DataResp(String code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public DataResp(RetMsg msg) {
        this.code = msg.getCode();
        this.msg = msg.getMsg();
    }

    public DataResp(RetMsg msg, T data) {
        this.code = msg.getCode();
        this.msg = msg.getMsg();
        this.data = data;
    }


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

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "DataResp{" +
                "code='" + code + '\'' +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
