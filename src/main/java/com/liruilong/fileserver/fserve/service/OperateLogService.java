package com.liruilong.fileserver.fserve.service;

import com.liruilong.fileserver.fserve.domain.OperateLog;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author Liruilong
 * @Date 2020/9/15 10:56
 * @Description:
 */

public interface OperateLogService {


     OperateLog getOperateLogById(String id);


     void saveOperateLog(OperateLog operateLog);


     void updateOperateLog(OperateLog operateLog);


     void deleteOperateLog(String[] ids);


     List<OperateLog> getOperateLogPage(OperateLog operateLog);
}
