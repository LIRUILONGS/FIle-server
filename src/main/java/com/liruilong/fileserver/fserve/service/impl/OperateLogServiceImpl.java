package com.liruilong.fileserver.fserve.service.impl;

import com.liruilong.fileserver.fserve.domain.OperateLog;
import com.liruilong.fileserver.fserve.mapper.OperateLogMapper;
import com.liruilong.fileserver.fserve.service.OperateLogService;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @author Liruilong
 * @Date 2020/9/15 10:57
 * @Description:
 */
@Service
public class OperateLogServiceImpl implements OperateLogService {


   @Autowired
   OperateLogMapper operateLogMapper;

    @Override
    public OperateLog getOperateLogById(String id) {
        return null;
    }

    @Override
    public void saveOperateLog(OperateLog operateLog) {
        operateLogMapper.insertSelective(operateLog);
    }

    @Override
    public void updateOperateLog(OperateLog operateLog) {
        operateLogMapper.updateByExampleSelective(operateLog,"");
    }

    @Override
    public void deleteOperateLog(String[] ids) {
        Arrays.stream(ids).forEach( o -> operateLogMapper.deleteByPrimaryKey(o));
    }

    @Override
    public List<OperateLog> getOperateLogPage(OperateLog operateLog) {
        operateLogMapper.selectByPrimaryKey(operateLog);
        return null;
    }
}
