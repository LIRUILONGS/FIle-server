package com.liruilong.fileserver.common.util.interfaceutil;

/**
 * @author Liruilong
 * @Date 2020/8/17 10:51
 * @Description:
 */
@FunctionalInterface
public interface ExceptionInterfaceOs {
    /**
     * <per>
     * <p>用于异常处理</p>
     * <per/>
     *
     * @param o
     * @return Object
     * @throws Exception
     * @Description
     * @author Liruilong
     * @Date 2020年08月13日  09:08:03
     **/
    Object exceptionUtil(Object... o) throws Exception;
}
