package com.spring.cloud.alibaba.remote.vo;

/**
 * 简单的响应结构体
 */
public class MyResponse {
    private Integer status;
    private String msg;

    public Integer getStatus() {
        return status;
    }

    public static Object builder() {
        // TODO Auto-generated method stub
        return null;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

}
