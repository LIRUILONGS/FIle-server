package com.liruilong.fileserver.common.util.interfaceutil;

/**
 * @Author Liruilong
 * @Date 2020/8/12 17:58
 * @Description: 处理异常隐藏的的函数接口
 */
@FunctionalInterface
public interface ExceptionInterfaceV {

    /**
     * <per>
     * <p>用于异常处理</p>
     * <per/>
     *
     * @param o
     * @return void
     * @throws Exception
     * @Description
     * @author Liruilong
     * @Date 2020年08月13日  09:08:03
     **/
    void exceptionUtil(Object o) throws Exception;
}
