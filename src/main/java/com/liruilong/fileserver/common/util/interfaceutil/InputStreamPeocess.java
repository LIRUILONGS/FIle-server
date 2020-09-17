package com.liruilong.fileserver.common.util.interfaceutil;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * @Description : 函数接口，描述BufferedReader ->String的转化方式
 * @Author: Liruilong
 * @Date: 2020/3/17 15:44
 */
@FunctionalInterface
public interface InputStreamPeocess {
    /**
     * @return String
     * @throws IOException
     * @Author Liruilong
     * @Description 方法签名 BufferedReader ->String
     * @Date 15:47 2020/3/17
     * @Param [inputStream]
     **/

    String peocess(BufferedReader bufferedReader) throws IOException;
}