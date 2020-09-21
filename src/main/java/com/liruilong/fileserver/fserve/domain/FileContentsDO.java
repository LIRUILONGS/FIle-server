package com.liruilong.fileserver.fserve.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.liruilong.fileserver.common.util.DateUtil;

import javax.persistence.Column;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Arrays;


/**
 * @Author Liruilong
 * @Date 2020/8/4 14:33
 * @Description:
 */
public class FileContentsDO implements Serializable {
    private static final long serialVersionUID = 3733621175312343434L;
    private final String id;
    private final byte[] fileContent;
    private final byte[] compressFileContent;
    private final String createUser;
    private final String updateUser;
    private final CompressTypeEnum compressTypeEnum;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private final Timestamp createTime;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private  final Timestamp updateTime ;

    public FileContentsDO(Builder builder) {
        id  = builder.id;
        fileContent = builder.fileContent;
        compressFileContent = builder.compressFileContent;
        createUser = builder.createUser;
        updateUser = builder.updateUser;
        compressTypeEnum = builder.compressTypeEnum;
        createTime = builder.createTime;
        updateTime = builder.updateTime;
    }

    public static class Builder {
        private static final long serialVersionUID = 3733621175312343434L;
        private String id;
        private byte[] fileContent;
        private byte[] compressFileContent;
        private String createUser;
        private String updateUser;
        private CompressTypeEnum compressTypeEnum;
        private Timestamp createTime;
        private Timestamp updateTime;

        public Builder() {
            this.createUser = "Liruilong";
            this.createTime = DateUtil.builder();
        }

        public FileContentsDO buide() {
            return new FileContentsDO(this);
        }

        public Builder setId(String id) {
            this.id = id;
            return this;

        }

        public Builder setFileContent(byte[] fileContent) {
            this.fileContent = fileContent;
            return this;
        }

        public Builder setCompressFileContent(byte[] compressFileContent) {
            this.compressFileContent = compressFileContent;
            return this;
        }

        public Builder setCreateUser(String createUser) {
            this.createUser = createUser;
            return this;
        }

        public Builder setUpdateUser(String updateUser) {
            this.updateUser = updateUser;
            return this;
        }

        public Builder setCompressTypeEnum(CompressTypeEnum compressTypeEnum) {
            this.compressTypeEnum = compressTypeEnum;
            return this;
        }

        public void setUpdateTime(Timestamp updateTime) {
            this.updateTime = updateTime;
        }
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getId() {
        return id;
    }

    public byte[] getFileContent() {
        return fileContent;
    }

    public byte[] getCompressFileContent() {
        return compressFileContent;
    }

    public String getCreateUser() {
        return createUser;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public CompressTypeEnum getCompressTypeEnum() {
        return compressTypeEnum;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    @Override
    public String toString() {
        return "FileContentsDO{" +
                "fileContent=" + Arrays.toString(fileContent) +
                ", compressFileContent=" + Arrays.toString(compressFileContent) +
                ", createUser='" + createUser + '\'' +
                ", updateUser='" + updateUser + '\'' +
                ", compressTypeEnum=" + compressTypeEnum +
                ", createTime=" + createTime +
                '}';
    }
}
