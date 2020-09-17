package com.liruilong.fileserver.fserve.controller;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.liruilong.fileserver.common.aop.FileCRUDEnum;
import com.liruilong.fileserver.common.aop.MethodsLog;
import com.liruilong.fileserver.fserve.domain.FileDO;
import com.liruilong.fileserver.fserve.domain.FileInfoDO;
import com.liruilong.fileserver.fserve.domain.ResponseDTO;
import com.liruilong.fileserver.fserve.service.FileService;
import com.mongodb.BasicDBObject;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.support.DefaultMultipartHttpServletRequest;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author Liruilong
 * @Date 2020/8/18 15:18
 * @Description:
 */
@RestController
public class FileController {
    public static final Logger log = LoggerFactory.getLogger(FileController.class);
    @Autowired
    private FileService fileService;






    /**
     * <per>
     * <p>构建目录</p>
     * <per/>
     * @param fileDO
     * @return com.liruilong.fileserver.fserve.domain.ResponseDTO
     * @throws
     * @Description : TODO
     * @author Liruilong
     * @Date 2020/9/15 13:57
     **/
    @PostMapping("/mkdir")
    public ResponseDTO mkdir(@RequestBody FileDO fileDO){
        List<FileInfoDO> list = fileService.mkdir(fileDO);

        return new ResponseDTO().setMsg("请求成功").setStatus(200).setObj(list.get(0).getFileName());
    }




    /**
     * <per>
     * <p>文件上传</p>
     * <per/>
     *
     * @param request
     * @param response
     * @return java.lang.Object
     * @throws
     * @Description : TODO 文件上传
     * @author Liruilong
     * @Date 2020/9/17 19:43
     **/
    @PostMapping("/upload ")
    public Object uploadFile(DefaultMultipartHttpServletRequest request ,HttpServletResponse response) throws JsonProcessingException {
        Object list = fileService.uploadFile(request, response, true);
        String result = new ObjectMapper().setSerializationInclusion(JsonInclude.Include.NON_NULL).writeValueAsString(list);
        return result;
    }




    /**
     * <per>
     * <p>文件上传(支持断点续传)</p>
     * <per/>
     *
     * @param basicDBObject
     * @return com.liruilong.fserve.domain.FileInfoDO
     * @throws
     * @Description
     * @author Liruilong
     * @Date 2020年08月18日  16:08:53
     **/
    @PostMapping("/FTP/FilesUpload")
    public FileInfoDO breakingPointSupervention(@RequestBody BasicDBObject basicDBObject) {
        return fileService.breakingPointSupervention(basicDBObject);

    }


    // 日志测试
    @GetMapping("/seacr")
    @MethodsLog(operType = FileCRUDEnum.QUERY,remark = "根据目录查找文件",paramData = "data")
    public ResponseDTO sea(@RequestParam(defaultValue = "23") int num,@RequestParam(defaultValue = "liruilong") String name){
      //  System.out.println(12 / 0);
        return    new ResponseDTO().setMsg("请求成功").setStatus(200).setObj("123");
    }

}
