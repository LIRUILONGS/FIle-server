package com.liruilong.fileserver.common.util;

import org.apache.commons.lang3.StringUtils;

import java.util.Objects;

/**
 * @author Liruilong
 * @Date 2020/8/18 13:59
 * @Description:
 */
public class UUIDUtil {
    private UUIDUtil() {
    }
    private static  String PreviousUUID;

    public static String builder() {
        return PreviousUUID = java.util.UUID.randomUUID().toString().replace("-", "");
    }
    public static String PreviousUUID(){
        if (StringUtils.isBlank(PreviousUUID)){
            throw new NullPointerException();
        }
        return PreviousUUID;
    }




    public static void main(String[] args) {
        for (int i = 0; i < 20; i++) {
            System.out.println(builder());
            System.err.println(PreviousUUID());
        }

    }
}
