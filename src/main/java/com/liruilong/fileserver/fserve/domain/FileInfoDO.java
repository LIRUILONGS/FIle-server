package com.liruilong.fileserver.fserve.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.liruilong.fileserver.common.annotation.PropertyDesc;
import com.liruilong.fileserver.common.util.UUIDUtil;
import com.mongodb.BasicDBObject;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.logging.Logger;

/**
 * <p>文件信息实体<p/>
 *
 * @author Liruilong
 * @Date 2020/8/4 11:22
 * @Description:
 */
@Document(collection = "FileInfo")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class FileInfoDO implements Serializable {
    private static final long serialVersionUID = -2099412985509362066L;
    @PropertyDesc("文件ID，用户获取文件唯一键")
    @JsonProperty("id")
    private   String abstractFileId;
    @JsonIgnore
    private   String id;
    private   String createUser;
    private   String updateUser;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private   Timestamp createTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private   Timestamp updateTime;
    @PropertyDesc("文件id")
    private   String fileId;
    @PropertyDesc("文件组id")
    private   String groupId;
    @PropertyDesc("文件名称")
    private   String fileName;
    @PropertyDesc("扩展名")
    private   String extension;
    @PropertyDesc("文件类型(0,目录，1,文件)")
    private   String contentType;
    @PropertyDesc("文件大小")
    private   Long fileSize;
    @PropertyDesc("md5")
    private   String md5;
    @PropertyDesc("文件修改时间")
    private   Long fileLastModified;
    @PropertyDesc("是否使用GridFS存储")
    private   boolean useGridFS;
    @PropertyDesc("照片拍摄日期")
    private   Date photoTime;
    @PropertyDesc("文件对应的字段名")
    private   String targetField;
    @PropertyDesc("文件对应的表名")
    private   String targetTable;
    @PropertyDesc("备注")
    private   String remark;
    @PropertyDesc("文件路径")
    private   String path;
    @PropertyDesc("锁")
    private   String lock_state;
    @PropertyDesc("锁定人")
    private   String lockUpSign;
    @PropertyDesc("锁定时间")
    private   Date lockDate;
    @PropertyDesc("文件状态")
    private   Integer fileState;
    @JsonIgnore
    @PropertyDesc("租户id")
    private   String tenantId;
    @PropertyDesc("文件版本")
    private   Integer version_;
    @PropertyDesc("文件信息扩展信息")
    public BasicDBObject fileExtend = new BasicDBObject();

    public FileInfoDO() {
        Timestamp t = new Timestamp(System.currentTimeMillis());
        this.id = UUIDUtil.builder();
        this.createTime = t;
        this.createUser = "小明";
        this.fileExtend = new BasicDBObject();

    }



    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getAbstractFileId() {
        return abstractFileId;
    }

    public String getId() {
        return id;
    }

    public String getCreateUser() {
        return createUser;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public String getFileId() {
        return fileId;
    }

    public String getGroupId() {
        return groupId;
    }

    public String getFileName() {
        return fileName;
    }

    public String getExtension() {
        return extension;
    }

    public String getContentType() {
        return contentType;
    }

    public Long getFileSize() {
        return fileSize;
    }

    public String getMd5() {
        return md5;
    }

    public Long getFileLastModified() {
        return fileLastModified;
    }

    public boolean isUseGridFS() {
        return useGridFS;
    }

    public Date getPhotoTime() {
        return photoTime;
    }

    public String getTargetField() {
        return targetField;
    }

    public String getTargetTable() {
        return targetTable;
    }

    public String getRemark() {
        return remark;
    }

    public String getPath() {
        return path;
    }

    public String getLock_state() {
        return lock_state;
    }

    public String getLockUpSign() {
        return lockUpSign;
    }

    public Date getLockDate() {
        return lockDate;
    }

    public Integer getFileState() {
        return fileState;
    }

    public String getTenantId() {
        return tenantId;
    }

    public Integer getVersion_() {
        return version_;
    }

    public FileInfoDO setAbstractFileId(String abstractFileId) {
        this.abstractFileId = abstractFileId;
        return this;
    }

    public FileInfoDO setId(String id) {
        this.id = id;
        return this;
    }

    public FileInfoDO setCreateUser(String createUser) {
        this.createUser = createUser;
        return this;
    }

    public FileInfoDO setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
        return this;
    }

    public FileInfoDO setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
        return this;
    }

    public FileInfoDO setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
        return this;
    }

    public FileInfoDO setFileId(String fileId) {
        this.fileId = fileId;
        return this;
    }

    public FileInfoDO setGroupId(String groupId) {
        this.groupId = groupId;
        return this;
    }

    public FileInfoDO setFileName(String fileName) {
        this.fileName = fileName;
        return this;
    }

    public FileInfoDO setExtension(String extension) {
        this.extension = extension;
        return this;
    }

    public FileInfoDO setContentType(String contentType) {
        this.contentType = contentType;
        return this;
    }

    public FileInfoDO setFileSize(Long fileSize) {
        this.fileSize = fileSize;
        return this;
    }

    public FileInfoDO setMd5(String md5) {
        this.md5 = md5;
        return this;
    }

    public FileInfoDO setFileLastModified(Long fileLastModified) {
        this.fileLastModified = fileLastModified;
        return this;
    }

    public FileInfoDO setUseGridFS(boolean useGridFS) {
        this.useGridFS = useGridFS;
        return this;
    }

    public FileInfoDO setPhotoTime(Date photoTime) {
        this.photoTime = photoTime;
        return this;
    }

    public FileInfoDO setTargetField(String targetField) {
        this.targetField = targetField;
        return this;
    }

    public FileInfoDO setTargetTable(String targetTable) {
        this.targetTable = targetTable;
        return this;
    }

    public FileInfoDO setRemark(String remark) {
        this.remark = remark;
        return this;
    }

    public FileInfoDO setPath(String path) {
        this.path = path;
        return this;
    }

    public FileInfoDO setLock_state(String lock_state) {
        this.lock_state = lock_state;
        return this;
    }

    public FileInfoDO setLockUpSign(String lockUpSign) {
        this.lockUpSign = lockUpSign;
        return this;
    }

    public FileInfoDO setLockDate(Date lockDate) {
        this.lockDate = lockDate;
        return this;
    }

    public FileInfoDO setFileState(Integer fileState) {
        this.fileState = fileState;
        return this;
    }

    public FileInfoDO setTenantId(String tenantId) {
        this.tenantId = tenantId;
        return this;
    }

    public FileInfoDO setVersion_(Integer version_) {
        this.version_ = version_;
        return this;
    }

    public FileInfoDO setFileExtend(BasicDBObject fileExtend) {
        this.fileExtend = fileExtend;
        return this;
    }

    @Override
    public String toString() {
         Logger l = Logger.getLogger(FileInfoDO.class.getCanonicalName());
         String s = "FileInfoDO{" +
                 "abstractFileId='" + abstractFileId + '\'' +
                 ", id='" + id + '\'' +
                 ", createUser='" + createUser + '\'' +
                 ", updateUser='" + updateUser + '\'' +
                 ", createTime=" + createTime +
                 ", updateTime=" + updateTime +
                 ", fileId='" + fileId + '\'' +
                 ", groupId='" + groupId + '\'' +
                 ", fileName='" + fileName + '\'' +
                 ", extension='" + extension + '\'' +
                 ", contentType='" + contentType + '\'' +
                 ", fileSize=" + fileSize +
                 ", md5='" + md5 + '\'' +
                 ", fileLastModified=" + fileLastModified +
                 ", useGridFS=" + useGridFS +
                 ", photoTime=" + photoTime +
                 ", targetField='" + targetField + '\'' +
                 ", targetTable='" + targetTable + '\'' +
                 ", remark='" + remark + '\'' +
                 ", path='" + path + '\'' +
                 ", lock_state='" + lock_state + '\'' +
                 ", lockUpSign='" + lockUpSign + '\'' +
                 ", lockDate=" + lockDate +
                 ", fileState=" + fileState +
                 ", tenantId='" + tenantId + '\'' +
                 ", version_=" + version_ +
                 '}';
         l.info(s);
        return s;
    }

}
