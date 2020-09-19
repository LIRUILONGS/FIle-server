package com.liruilong.fileserver.fserve.service.impl;


import com.liruilong.fileserver.common.aop.FileCRUDEnum;
import com.liruilong.fileserver.common.aop.MethodsLog;
import com.liruilong.fileserver.common.exception.ErrorCode;
import com.liruilong.fileserver.common.exception.FileServiceException;
import com.liruilong.fileserver.common.util.UUIDUtil;
import com.liruilong.fileserver.fserve.domain.FileContants;
import com.liruilong.fileserver.fserve.domain.FileDO;
import com.liruilong.fileserver.fserve.domain.FileInfoDO;
import com.liruilong.fileserver.fserve.domain.UploadingFileDTO;
import com.liruilong.fileserver.fserve.service.FileService;
import com.mongodb.BasicDBObject;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.DefaultMultipartHttpServletRequest;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.*;

import static com.liruilong.fileserver.common.util.interfaceutil.InterfaceUtilMethod.exceptionUtil;
import static org.springframework.data.mongodb.core.query.Criteria.where;


/**
 * @author Liruilong
 * @Date 2020/8/18 15:24
 * @Description:
 */
@Service
public class FileServiceImpl implements FileService {


    @Autowired(required=false)
    private MongoTemplate mongoTemplate;

    @Override
    public FileInfoDO breakingPointSupervention(BasicDBObject b) {

        Map<String, ?> p = b.toMap();
        //值校验
        exceptionUtil(() -> b.getString("fileName"), "文件名不能为空!", ErrorCode.ParameterEmpty);
        exceptionUtil(() -> b.getString("md5"), "MD5值不能为空!", ErrorCode.ParameterEmpty);
        exceptionUtil(() -> b.getString("fileSize"), "文件大小参数错误<0kb文件!", ErrorCode.ParameterError);

        String filePath = File.separator;
        FileInfoDO fileInfoDO = null;
        if (StringUtils.isNoneBlank(b.getString("dirId"))) {
            fileInfoDO = dirDetailById(b.getString("dirId"), null);
            if (Objects.nonNull(fileInfoDO)) {
                filePath = fileInfoDO.getPath() + fileInfoDO.getFileName() + File.separator;
            }
        } else if (StringUtils.isNoneBlank(b.getString("dirId"))) {
            fileInfoDO = dirDetail(filePath, null);
        }


        return null;
    }

   /**
    * <per>
    * <p>根据 filePath ，version 获取目录详情  </p>
    * <per/>
    * @param filePath
    * @param version_
    * @return com.liruilong.fileserver.fserve.domain.FileInfoDO
    * @throws
    * @Description : TODO According file Path, version for catalog details
    * @author Liruilong
    * @Date 2020/9/19 19:51
    **/
    @Override
    public FileInfoDO dirDetail(String filePath, Integer version_) {
        return null;

    }


    /**
     * <per>
     * <p>构建文件目录</p>
     * <per/>
     * @param fileDO
     * @return java.util.List
     * @throws
     * @Description : TODO Build file path
     * @author Liruilong
     * @Date 2020/9/15 14:02
     **/
    @Override
    @MethodsLog(operType = FileCRUDEnum.CREATE,remark = "构建文件夹",paramData = "fileDO")
    public List<FileInfoDO> mkdir(final FileDO fileDO) {
        String pathname = fileDO.getPathname();
        // 参数为空校验
        checkoutNull(pathname);
        // 参数格式校验

        String[] dirNameArr = pathname.split(FileContants.SPLIT_SIGN);
        int size = dirNameArr.length;
        List<FileInfoDO> arrayList = new ArrayList<>(size);
        StringBuilder path = new StringBuilder(FileContants.SPLIT_TOKEN_SIGN);
        List pathList = new ArrayList();
        List nameList = new ArrayList();
        // 构建的时候需要一层层构建
        for (int i = 0; i < size; i++) {
            if (StringUtils.isBlank(dirNameArr[i])){
                continue;
            }
            FileInfoDO fileInfoDO = new  FileInfoDO().setAbstractFileId(UUIDUtil.builder())
                    .setFileName(dirNameArr[i]).setVersion_(FileContants.VSERSION_1)
                    .setContentType(FileContants.FILE_TYPE_DIR).setAbstractFileId(UUIDUtil.builder())
                    .setPath(path.toString());

            path.append(dirNameArr[i]).append(FileContants.SPLIT_SIGN);
            arrayList.add(0,fileInfoDO);
            pathList.add(fileInfoDO.getPath());
            nameList.add(fileInfoDO.getFileName());
        }
        Query query = new Query();
        query.addCriteria(where("path").in(pathList).and("fileName").in(nameList).and("lock_state").is(FileContants.LOCK_STATE));
        // 判断目录是否存在锁定
        List resList = mongoTemplate.find(query, FileInfoDO.class);
        if (resList.size()> 0){
            Iterator iterator = resList.iterator();
            while (iterator.hasNext()){
                FileInfoDO fileInfoDO = (FileInfoDO) iterator.next();
                if(path.toString().startsWith(fileInfoDO.getPath()+fileInfoDO.getFileName()+FileContants.SPLIT_SIGN)){
                    throw new FileServiceException("目录下有锁不能创建新目录!",ErrorCode.Lock.getCode());
                }
            }
        }
        arrayList.stream().forEach(System.out::print);
        mongoTemplate.insertAll(arrayList);

        if (!arrayList.isEmpty()){
            FileInfoDO fileInfoDO = arrayList.get(0);
            FileInfoDO oldFileInfoDO = dirDetail(fileInfoDO.getPath()+ fileInfoDO.getFileName()+ FileContants.SPLIT_SIGN,null);
            if (Objects.nonNull(oldFileInfoDO)){
                fileInfoDO.setAbstractFileId(oldFileInfoDO.getAbstractFileId());
            }
        }
        return arrayList;
    }


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
     * @Description : TODO File upload (non-breakpoint)
     * @author Liruilong
     * @Date 2020/9/17 19:42
     **/
    @Override
    @MethodsLog(operType = FileCRUDEnum.CREATE,remark = "文件上传（非断点）",paramData ="" )
    public Object uploadFile(DefaultMultipartHttpServletRequest request, HttpServletResponse response, boolean isUseGridFS) {

        // 断点续传的处理
        if (StringUtils.isNotBlank(request.getHeader("RANGE"))){
            // 到断点续传
        }
        Map<String, String[]> parameterMap = request.getParameterMap();
        // 初始化上传对象
        UploadingFileDTO.Builder builder = new UploadingFileDTO.Builder("");
        if (getUploadFileNum(request) == 1){
            // 获取文件的ID和MD5 只针对单文件处理
            builder.setId("").setMd5("");
        }
        /*
        目录 锁处理
         */
        Iterator<String> fileNames = request.getFileMap().keySet().iterator();
        List<FileInfoDO> list = new LinkedList<>();
        UploadingFileDTO uploadingFileDTO = builder.build();
        while (fileNames.hasNext()){
            List<MultipartFile> requestFiles = request.getFiles(fileNames.next());

           /*
           文件合规验证
            */
            for (MultipartFile multipartFile : requestFiles) {
                /*
                文件存储对象
                 */
                FileInfoDO fileInfoDO = new FileInfoDO().setUseGridFS(isUseGridFS).setFileId(UUIDUtil.builder());
                /*
                文件对象
                 */
                FileDO fileDO = new FileDO().setFileId(UUIDUtil.PreviousUUID());

                String tempDirPath = "";


            }

        }
        return null;
    }


  /**
   * <per>
   * <p>获取上传文件个数</p>
   * <per/>
   * @param request
   * @return long
   * @throws
   * @Description : TODO Gets the number of uploaded files
   * @author Liruilong
   * @Date 2020/9/19 19:56
   **/
    private long getUploadFileNum(DefaultMultipartHttpServletRequest request) {
        Iterator<String> fileNames = request.getFileMap().keySet().iterator();
        long fileNum = 0L;
        while (fileNames.hasNext()) {
            String s = fileNames.next();
            fileNum += request.getFiles(s).stream().filter(o -> Objects.nonNull(o) && StringUtils.isNoneBlank(o.getOriginalFilename()))
                    .count();
        }
        return fileNum;
    }



    @Override
    public FileInfoDO dirDetailById(String fileDirId, Integer version_) {
        if (StringUtils.isBlank(fileDirId)) {
            return null;
        }
        return null;

    }


    /**
     * <per>
     * <p>参数校验</p>
     * <per/>
     * @param param
     * @return void
     * @throws
     * @Description : TODO 参数校验
     * @author Liruilong
     * @Date 2020/9/15 14:11
     **/
    public static void checkoutNull(Object... param){
        for (Object o : param) {
            exceptionUtil(() -> o, "参数为空异常!", ErrorCode.ParameterEmpty);
        }
    }


}
