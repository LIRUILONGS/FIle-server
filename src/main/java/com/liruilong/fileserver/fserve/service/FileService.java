package com.liruilong.fileserver.fserve.service;


import com.liruilong.fileserver.fserve.domain.FileDO;
import com.liruilong.fileserver.fserve.domain.FileInfoDO;
import com.mongodb.BasicDBObject;
import org.springframework.web.multipart.support.DefaultMultipartHttpServletRequest;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author Liruilong
 * @Date 2020/8/18 15:24
 * @Description:
 */

public interface FileService {



    /**
     * <per>
     * <p>断点传输</p>
     * <per/>
     *
     * @param basicDBObject
     * @return com.liruilong.fserve.domain.FileInfoDO
     * @throws
     * @Description
     * @author Liruilong
     * @Date 2020年08月18日  17:08:56
     **/
    FileInfoDO breakingPointSupervention(BasicDBObject basicDBObject);


    /**
     * <per>
     * <p>根据文件目录id获取文件目录信息</p>
     * <per/>
     *
     * @param fileDirId
     * @param version_
     * @return com.liruilong.fserve.domain.FileInfoDO
     * @throws
     * @Description
     * @author Liruilong
     * @Date 2020年08月18日  17:08:04
     **/
    FileInfoDO dirDetailById(String fileDirId, Integer version_);

    /**
     * <per>
     * <p>指定文件目录路径查看文件详情</p>
     * <per/>
     *
     * @param filePath
     * @param version_
     * @return com.liruilong.fserve.domain.FileInfoDO
     * @throws
     * @Description
     * @author Liruilong
     * @Date 2020年08月19日  09:08:34
     **/
    FileInfoDO dirDetail(String filePath, Integer version_);

   /**
    * <per>
    * <p>构建目录</p>
    * <per/>
    * @param fileDO
    * @return java.util.List
    * @throws
    * @Description : TODO
    * @author Liruilong
    * @Date 2020/9/15 13:39
    **/
    List mkdir(FileDO fileDO);


    /**
     * <per>
     * <p>文件上传（非断点）</p>
     * <per/>
     *
     * @param request
     * @param response
     * @param b
     * @return java.lang.Object
     * @throws
     * @Description : TODO TODO File upload (non-breakpoint)
     * @author Liruilong
     * @Date 2020/9/17 19:43
     **/
    Object uploadFile(DefaultMultipartHttpServletRequest request, HttpServletResponse response, boolean b);
}
