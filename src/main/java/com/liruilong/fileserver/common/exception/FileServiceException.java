package com.liruilong.fileserver.common.exception;


/**
 * <per>
 * <p></p>
 * <per/>
 *
 * @param null
 * @author Liruilong
 * @return
 * @throws
 * @Description
 * @Date 2020年08月18日  17:08:21
 **/
public class FileServiceException extends RuntimeException {

    /**
     *
     */
    private static final long serialVersionUID = -6425645800126717288L;
    private String code;

    public FileServiceException(String message, String code) {
        super(message);
        this.code = code;
    }

    public FileServiceException(String message, Throwable t) {
        super(message, t);
    }

    public FileServiceException(String message, ErrorCode code) {
        super(message);
        this.code = code.getCode();
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


}
