package com.liruilong.fileserver.fserve.domain;


public class FileContants {

    /**
     * 标记头
     */
    public static String FWEB_TOKEN_SIGN = "FWEB";

    /**
     * 用户域/租户
     */
    public static String TENANT_ID = "tenantId";

    /**
     * 未授权用户
     */
    public static String TEANTID_OLD = "-1";


    /**
     * 分页信息
     */
    public static String PAGEABLE = "pageable";


    /**
     * 用户标记
     */
    public static String USER_SIGN = "user_sign";

    /**
     * 用户访问key
     */
    public static String ACCESS_KEY = "AccessKey";


    /**
     * 文件分割符号
     */
    public static String SPLIT_SIGN = "/";


    /**
     * Token 分割标识
     */
    public static String SPLIT_TOKEN_SIGN = ":";


    /**
     * 文件夹状态 只有2种状态：
     *
     *
     */

    /**
     * 断点续传版本
     */
    public static int FILE_STATE_POINT = 0;

    /**
     * 正常版本
     */
    public static int FILE_STATE_NORMAL = 1;


    /**
     * 文件状态
     */
    public static int FILE_STATE_DEL = 2;


    /**
     * 版本号,未完成版本
     */
    public static int VSERSION_0 = 0;

    /***
     * 版本号,第一版
     */
    public static int VSERSION_1 = 1;

    /**
     * 版本号,第二版本
     */
    public static int VSERSION_2 = 2;

    /**
     * 锁状态
     */
    public static String LOCK_STATE = "1";


    public static String LOCK_STATE_NOT = "0";


    /**
     * 文件类型 0 目录, 1 文件
     */
    public static String FILE_TYPE_DIR = "0";

    /**
     * 文件类型 0 目录, 1 文件
     */
    public static String FILE_TYPE_FILE = "1";

    /**
     * 1.公开【域下所有均可以查看】，2.私有【只有自己才能查看】,可授权查看【指定范围的人员才能查看,需要出示授权凭证】,4.无限制
     */

    /**
     * 1.公开
     */
    public static int SHARE_SIGN_PUBLIC = 1;

    /**
     * 私有
     */
    public static int SHARE_SIGN_PRIVATE = 2;


    /***
     * 4.无限制
     */
    public static int SHARE_SIGN_ALL = 4;


    public static String CHARACTER_ENCODING_UTF_8 = "UTF-8";

    public static String CHARACTER_ENCODING_UTF_GB2312 = "GB2312";


    public static String DEFAULT_CONTENT_TYPE = "application/octet-stream";


    /**
     * 分块大小 默认2m
     */
    public static long SLICE_SIZE = 2 * 1024 * 1024;


    public static boolean isDir(String fileType) {

        return FILE_TYPE_DIR.equals(fileType);
    }
}
