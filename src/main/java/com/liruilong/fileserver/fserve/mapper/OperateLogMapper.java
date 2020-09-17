package com.liruilong.fileserver.fserve.mapper;

import com.liruilong.fileserver.fserve.domain.OperateLog;

import org.springframework.stereotype.Component;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;


/**
 * @author Liruilong
 * @Date 2020/9/15 10:53
 * @Description:
 */
@Component
public interface OperateLogMapper extends Mapper<OperateLog> {



}
