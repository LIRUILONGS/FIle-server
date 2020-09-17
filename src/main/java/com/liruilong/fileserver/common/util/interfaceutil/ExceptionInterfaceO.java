package com.liruilong.fileserver.common.util.interfaceutil;

/**
 * @author Liruilong
 * @Date 2020/8/13 09:55
 * @Description:
 */
@FunctionalInterface
public interface ExceptionInterfaceO {

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
    Object exceptionUtil(Object o) throws Exception;
}

