package com.liruilong.fileserver.fserve.domain;

/**
 * @author Liruilong
 * @Date 2020/9/14 14:59
 * @Description: 消息響應實體。
 */
public class ResponseDTO {
    private Integer status;
    private String msg;
    private  Object obj;

    public Integer getStatus() {
        return status;
    }

    public ResponseDTO setStatus(Integer status) {
        this.status = status;
        return this;
    }

    public String getMsg() {
        return msg;
    }

    public ResponseDTO setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public Object getObj() {
        return obj;
    }

    public ResponseDTO setObj(Object obj) {
        this.obj = obj;
        return this;
    }

    @Override
    public String toString() {
        return "ResponseDTO{" +
                "status=" + status +
                ", msg='" + msg + '\'' +
                ", obj=" + obj +
                '}';
    }
}
