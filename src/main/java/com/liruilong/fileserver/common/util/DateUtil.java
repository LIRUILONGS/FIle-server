package com.liruilong.fileserver.common.util;

import java.sql.Timestamp;

/**
 * @author Liruilong
 * @Date 2020/9/17 20:47
 * @Description: 时间工具类
 */
public  class DateUtil {

    private DateUtil() {
        throw new AssertionError();
    }

    public static Timestamp builder(){
        return new Timestamp(System.currentTimeMillis());
    }

    public static void main(String[] args) {
        System.out.println(DateUtil.builder());
    }

}
