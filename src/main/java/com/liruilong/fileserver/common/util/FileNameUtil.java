package com.liruilong.fileserver.common.util;

import org.apache.commons.lang3.StringUtils;

/**
 * @author Liruilong
 * @Date 2020/8/19 10:06
 * @Description:
 */
public class FileNameUtil {


    private FileNameUtil() {
        throw new AssertionError();
    }

    /**
     * <per>
     * <p>文件目录格式验证</p>
     * <per/>
     *
     * @param pathname
     * @return boolean
     * @throws
     * @Description
     * @author Liruilong
     * @Date 2020年08月19日  10:08:33
     **/
    public static boolean dirPathVerification(String pathname) {
        if (pathname.length() > 2000) {
            return false;
        }
        return true;
    }


    /**
     * <per>
     * <p>验证文件名，不能有带"/"</p>
     * <per/>
     *
     * @param dirName
     * @return boolean
     * @throws
     * @Description
     * @author Liruilong
     * @Date 2020年08月19日  10:08:00
     **/
    public static boolean dirVerification(String dirName) {
        if (StringUtils.isBlank(dirName)) {
            return false;
        }
        return dirName.indexOf("/") < 0;

    }
}
