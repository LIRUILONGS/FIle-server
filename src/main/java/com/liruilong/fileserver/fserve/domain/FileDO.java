package com.liruilong.fileserver.fserve.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author Liruilong
 * @Date 2020/9/14 16:33
 * @Description:
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class FileDO {
    private String fileId;

    private String tagDirId;

    private String pathname;

    private String newFileName;

    public String getFileId() {
        return fileId;
    }

    public FileDO setFileId(String fileId) {
        this.fileId = fileId;
        return this;
    }

    public String getTagDirId() {
        return tagDirId;
    }

    public FileDO setTagDirId(String tagDirId) {
        this.tagDirId = tagDirId;
        return this;
    }

    public String getPathname() {
        return pathname;
    }

    public FileDO setPathname(String pathname) {
        this.pathname = pathname;
        return this;
    }

    public String getNewFileName() {
        return newFileName;
    }

    public FileDO setNewFileName(String newFileName) {
        this.newFileName = newFileName;
        return this;
    }

    @Override
    public String toString() {
        return "FileDO{" +
                "fileId='" + fileId + '\'' +
                ", tagDirId='" + tagDirId + '\'' +
                ", pathname='" + pathname + '\'' +
                ", newFileName='" + newFileName + '\'' +
                '}';
    }
}
