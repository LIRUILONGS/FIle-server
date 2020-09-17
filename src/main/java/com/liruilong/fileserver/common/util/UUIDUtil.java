package com.liruilong.fileserver.common.util;

/**
 * @author Liruilong
 * @Date 2020/8/18 13:59
 * @Description:
 */
public class UUIDUtil {
    private UUIDUtil() {
    }

    public static String builder() {
        return java.util.UUID.randomUUID().toString().replace("-", "");
    }


    public static void main(String[] args) {
        for (int i = 0; i < 20; i++) {
            System.out.println(builder());
        }
    }
}
