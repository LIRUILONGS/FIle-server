package com.liruilong.fileserver.common.aop;

/**
 * @Auther: Liruilong
 * @Date: 2020/7/29 15:50
 * @Description: 由函数名获取元类Class实例
 * 函数签名：   String ==> Class
 */
@FunctionalInterface
public interface ClassFindInterface {
    Class<?> classNametoClass(String className)throws ClassNotFoundException;
}
