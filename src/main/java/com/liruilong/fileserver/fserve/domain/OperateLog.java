package com.liruilong.fileserver.fserve.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.liruilong.fileserver.common.annotation.PropertyDesc;


import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.logging.Logger;

/**
 * @author Liruilong
 * @Date 2020/9/15 10:45
 * @Description:
 */
@Table(name = "operate_log")
public class OperateLog implements Serializable {

    private static final long serialVersionUID = -7211614218675259556L;

    @Id
    private String id;

    @PropertyDesc("")
    private String userId;

    @PropertyDesc("")
    private String userName;

    @PropertyDesc("")
    private String module;

    @PropertyDesc("")
    private String method;

    @PropertyDesc("")
    private String ip;

    @PropertyDesc("")
    private String remark;

    @PropertyDesc("")
    private String className;


    @PropertyDesc("")
    private String operType;


    @PropertyDesc("状态信息：0正常，1异常")
    private Integer status;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
    private Timestamp createTime;

    @Override
    public String toString() {
        Logger l = Logger.getLogger(OperateLog.class.getCanonicalName());
        String s = "OperateLog{" +
                "id='" + id + '\'' +
                ", userId='" + userId + '\'' +
                ", userName='" + userName + '\'' +
                ", module='" + module + '\'' +
                ", method='" + method + '\'' +
                ", ip='" + ip + '\'' +
                ", remark='" + remark + '\'' +
                ", className='" + className + '\'' +
                ", operType='" + operType + '\'' +
                ", status=" + status +
                ", createTime=" + createTime +
                '}';
        l.info(s);
        return s;
    }

    public String getId() {
        return id;
    }

    public OperateLog setId(String id) {
        this.id = id;
        return this;
    }

    public String getUserId() {
        return userId;
    }

    public OperateLog setUserId(String userId) {
        this.userId = userId;
        return this;
    }

    public String getUserName() {
        return userName;
    }

    public OperateLog setUserName(String userName) {
        this.userName = userName;
        return this;
    }

    public String getModule() {
        return module;
    }

    public OperateLog setModule(String module) {
        this.module = module;
        return this;
    }

    public String getMethod() {
        return method;
    }

    public OperateLog setMethod(String method) {
        this.method = method;
        return this;
    }

    public String getIp() {
        return ip;
    }

    public OperateLog setIp(String ip) {
        this.ip = ip;
        return this;
    }

    public String getRemark() {
        return remark;
    }

    public OperateLog setRemark(String remark) {
        this.remark = remark;
        return this;
    }

    public String getClassName() {
        return className;
    }

    public OperateLog setClassName(String className) {
        this.className = className;
        return this;
    }

    public String getOperType() {
        return operType;
    }

    public OperateLog setOperType(String operType) {
        this.operType = operType;
        return this;
    }

    public Integer getStatus() {
        return status;
    }

    public OperateLog setStatus(Integer status) {
        this.status = status;
        return this;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public OperateLog setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
        return this;
    }


}







