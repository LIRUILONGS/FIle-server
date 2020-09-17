package com.liruilong.fileserver.fserve.domain;


import com.liruilong.fileserver.common.annotation.PropertyDesc;

/**
 * @author Liruilong
 * @Date: 2020/8/4 10:21
 * @Description:
 */
public class UploadingFileDTO {
    public static final int defaultWidth = Integer.parseInt("");
    public static final int defaultHeight = Integer.parseInt("");
    @PropertyDesc("图片默认的压缩比例")
    static final double DEFAULT_COMPRESS_RATE = 0.5D;
    @PropertyDesc("文件信息id（可选，如果id不为空且数据库中存在则认为修改，否则插入）")
    private final String id;
    @PropertyDesc("md5")
    private final String md5;
    @PropertyDesc("文件组id")
    private final String groupId;
    @PropertyDesc("表字段（可选）")
    private final String targetField;
    @PropertyDesc("表名（可选）")
    private final String targetTable;
    @PropertyDesc("是否生成缩略图")
    private final String isGenerateThumbnail;
    @PropertyDesc("缩略图大小（可选,例如（宽x高）50x50,,如果为单个数字则长宽相同）")
    private final String thumbWH;
    @PropertyDesc("视频中缩略图的截取位置（秒，可选）")
    private final String thumbPos;
    @PropertyDesc("是否产生压缩图片（可选）")
    private final String isGenerateCompressImage;
    @PropertyDesc("图片压缩比率（可选，默认0.5）")
    private final String rate;
    @PropertyDesc("备注")
    private final String remark;
    @PropertyDesc("图片最大宽度")
    private final double maxW;
    @PropertyDesc("图片最大高度")
    private final double maxH;
    @PropertyDesc("图片最大大小")
    private final double maxSize;
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
        ;
        targetField = builder.targetField;
        targetTable = builder.targetTable;
        isGenerateThumbnail = builder.isGenerateThumbnail;
        thumbWH = builder.thumbWH;
        thumbPos = builder.thumbPos;
        isGenerateCompressImage = builder.isGenerateCompressImage;
        rate = builder.rate;
        remark = builder.remark;
        maxW = builder.maxW;
        maxH = builder.maxH;
        maxSize = builder.maxSize;
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
        private String targetField = "";
        private String targetTable = "";
        private String isGenerateThumbnail = "true";
        private String thumbWH = "";
        private String thumbPos = "";
        private String isGenerateCompressImage = "false";
        private String rate = "0.5";
        private String remark = "";
        private double maxW = -1.0D;
        private double maxH = -1.0D;
        private double maxSize = -1.0D;
        private String fileName;
        private Long fileSize;
        private String fileContentType;
        private String pathName;
        private String dirId;

        public Builder(String fileName, Long fileSize, String fileContentType, String pathName, String dirId) {
            this.fileName = fileName;
            this.fileSize = fileSize;
            this.pathName = pathName;
            this.fileContentType = fileContentType;
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

        public Builder setTargetField(String targetField) {
            this.targetField = targetField;
            return this;
        }

        public Builder setTargetTable(String targetTable) {
            this.targetTable = targetTable;
            return this;
        }

        public Builder setIsGenerateThumbnail(String isGenerateThumbnail) {
            this.isGenerateThumbnail = isGenerateThumbnail;
            return this;
        }

        public Builder setThumbWH(String thumbWH) {
            this.thumbWH = thumbWH;
            return this;
        }

        public Builder setThumbPos(String thumbPos) {
            this.thumbPos = thumbPos;
            return this;
        }

        public Builder setIsGenerateCompressImage(String isGenerateCompressImage) {
            this.isGenerateCompressImage = isGenerateCompressImage;
            return this;
        }

        public Builder setRate(String rate) {
            this.rate = rate;
            return this;
        }

        public Builder setRemark(String remark) {
            this.remark = remark;
            return this;
        }

        public Builder setMaxW(double maxW) {
            this.maxW = maxW;
            return this;
        }

        public Builder setMaxH(double maxH) {
            this.maxH = maxH;
            return this;
        }

        public Builder setMaxSize(double maxSize) {
            this.maxSize = maxSize;
            return this;
        }

    }


    public static int getDefaultWidth() {
        return defaultWidth;
    }

    public static int getDefaultHeight() {
        return defaultHeight;
    }

    public static double getDefaultCompressRate() {
        return DEFAULT_COMPRESS_RATE;
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

    public String getTargetField() {
        return targetField;
    }

    public String getTargetTable() {
        return targetTable;
    }

    public String getIsGenerateThumbnail() {
        return isGenerateThumbnail;
    }

    public String getThumbWH() {
        return thumbWH;
    }

    public String getThumbPos() {
        return thumbPos;
    }

    public String getIsGenerateCompressImage() {
        return isGenerateCompressImage;
    }

    public String getRate() {
        return rate;
    }

    public String getRemark() {
        return remark;
    }

    public double getMaxW() {
        return maxW;
    }

    public double getMaxH() {
        return maxH;
    }

    public double getMaxSize() {
        return maxSize;
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
                ", targetField='" + targetField + '\'' +
                ", targetTable='" + targetTable + '\'' +
                ", isGenerateThumbnail='" + isGenerateThumbnail + '\'' +
                ", thumbWH='" + thumbWH + '\'' +
                ", thumbPos='" + thumbPos + '\'' +
                ", isGenerateCompressImage='" + isGenerateCompressImage + '\'' +
                ", rate='" + rate + '\'' +
                ", remark='" + remark + '\'' +
                ", maxW=" + maxW +
                ", maxH=" + maxH +
                ", maxSize=" + maxSize +
                ", fileName='" + fileName + '\'' +
                ", fileSize=" + fileSize +
                ", fileContentType='" + fileContentType + '\'' +
                ", pathName='" + pathName + '\'' +
                ", dirId='" + dirId + '\'' +
                '}';
    }


}
