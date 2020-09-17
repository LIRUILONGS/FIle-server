package com.liruilong.fileserver.common.exception;


public enum ErrorCode {

    //错误编码
    ParameterEmpty("ParameterEmpty", "参数为空"),
    ParameterError("ParameterError", "参数错误"),
    NotPower("NotPower", "无权限"),
    DirIdError("DirIdError", "目录id传入错误"),
    FileIdError("FileIdError", "文件id传入错误"),
    Lock("Lock", "文件被锁定"),
    PathFormatRrror("PathFormatRrro", "路径格式错误"),
    NotFoundFile("NotFoundFile", "未找到对应文件"),
    FileNameRepeat("FileNameRepeat", "文件名重复!"),
    NotMatching("NotMatching", "不匹配!"),
    FormatErroe("FormatErroe", "格式错误!");

    private String code;
    private String name;

    //构造方法
    private ErrorCode(String code, String name) {
        this.code = code;
        this.name = name;
    }

    //生成get/set方法
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    /**
     * @return 返回 code。
     */
    public String getCode() {
        return code;
    }

    /**
     * @param code 要设置的 code。
     */
    public void setCode(String code) {
        this.code = code;
    }

    //获取锁状态名称
    public static String getCodeName(String code) {
        for (ErrorCode d : ErrorCode.values()) {
            if (d.getCode().equals(code)) {
                return d.name;
            }
        }
        return null;
    }


}
