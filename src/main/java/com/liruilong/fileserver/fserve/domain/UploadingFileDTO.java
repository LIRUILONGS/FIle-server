package com.liruilong.fileserver.fserve.domain;


import com.liruilong.fileserver.common.annotation.PropertyDesc;

/**
 * @author Liruilong
 * @Date: 2020/8/4 10:21
 * @Description:
 */
public class UploadingFileDTO {

    @PropertyDesc("文件信息id（可选，如果id不为空且数据库中存在则认为修改，否则插入）")
    private final String id;
    @PropertyDesc("md5")
    private final String md5;
    @PropertyDesc("文件组id")
    private final String groupId;
    @PropertyDesc("文件名称")
    private final String fileName;
    @PropertyDesc("文件大小")
    private final Long fileSize;
    @PropertyDesc("文件类型")
    private final String fileContentType;
    @PropertyDesc("上传目标目录路径")
    private final String pathName;
    @PropertyDesc("上传目录id")
    private final String dirId;

    private UploadingFileDTO(Builder builder) {
        id = builder.id;
        md5 = builder.md5;
        groupId = builder.groupId;
        fileName = builder.fileName;
        fileSize = builder.fileSize;
        fileContentType = builder.fileContentType;
        pathName = builder.pathName;
        dirId = builder.dirId;
    }

    public static class Builder {
        private String id = "";
        private String md5 = "";
        private String groupId = "";
        private String fileName;
        private Long fileSize;
        private String fileContentType;
        private String pathName;
        private String dirId;

        public Builder( String dirId) {
            this.dirId = dirId;
        }


        public UploadingFileDTO build() {
            return new UploadingFileDTO(this);
        }

        public Builder setId(String id) {
            this.id = id;
            return this;
        }

        public Builder setMd5(String md5) {
            this.md5 = md5;
            return this;
        }

        public Builder setGroupId(String groupId) {
            this.groupId = groupId;
            return this;
        }

        public Builder setFileName(String fileName) {
            this.fileName = fileName;
            return this;
        }

        public Builder setFileSize(Long fileSize) {
            this.fileSize = fileSize;
            return this;
        }

        public Builder setFileContentType(String fileContentType) {
            this.fileContentType = fileContentType;
            return this;
        }

        public Builder setPathName(String pathName) {
            this.pathName = pathName;
            return this;
        }

        public String getId() {
            return id;
        }

        public String getMd5() {
            return md5;
        }

        public String getGroupId() {
            return groupId;
        }

        public String getFileName() {
            return fileName;
        }

        public Long getFileSize() {
            return fileSize;
        }

        public String getFileContentType() {
            return fileContentType;
        }

        public String getPathName() {
            return pathName;
        }

        public String getDirId() {
            return dirId;
        }
    }

    public String getId() {
        return id;
    }

    public String getMd5() {
        return md5;
    }

    public String getGroupId() {
        return groupId;
    }

    public String getFileName() {
        return fileName;
    }

    public Long getFileSize() {
        return fileSize;
    }

    public String getFileContentType() {
        return fileContentType;
    }

    public String getPathName() {
        return pathName;
    }

    public String getDirId() {
        return dirId;
    }

    @Override
    public String toString() {
        return "UploadingFileDTO{" +
                "id='" + id + '\'' +
                ", md5='" + md5 + '\'' +
                ", groupId='" + groupId + '\'' +
                ", fileName='" + fileName + '\'' +
                ", fileSize=" + fileSize +
                ", fileContentType='" + fileContentType + '\'' +
                ", pathName='" + pathName + '\'' +
                ", dirId='" + dirId + '\'' +
                '}';
    }



}
