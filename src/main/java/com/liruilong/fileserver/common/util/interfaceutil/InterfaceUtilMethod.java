package com.liruilong.fileserver.common.util.interfaceutil;



import com.liruilong.fileserver.common.exception.ErrorCode;
import com.liruilong.fileserver.common.exception.FileServiceException;
import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.util.logging.Logger;

/**
 * @author Liruilong
 * @Date 2020/8/13 10:00
 * @Description:
 */
public class InterfaceUtilMethod<T> {

    private static Logger logger = Logger.getLogger("com.liruilong.common.util.interfaceutil.InterfaceUtilMethod");

    private InterfaceUtilMethod() {
        throw new AssertionError();
    }


    /**
     * <per>
     * <p>没有返回值的异常处理</p>
     * <per/>
     *
     * @param exceptionInterface
     * @param msg
     * @param o
     * @return void
     * @throws
     * @Description
     * @author Liruilong
     * @Date 2020年08月13日  09:08:21
     **/
    public static void exceptionUtilNo(ExceptionInterfaceV exceptionInterface, String msg, Object o) {
        try {
            exceptionInterface.exceptionUtil(o);
        } catch (Exception e) {
            logger.info(msg);
            e.printStackTrace();
        }
    }

    /**
     * <per>
     * <p>没有返回值的异常处理</p>
     * <per/>
     *
     * @param exceptionInterface
     * @param msg
     * @return void
     * @throws
     * @Description
     * @author Liruilong
     * @Date 2020年08月19日  11:08:48
     **/
    public static void exceptionUtil(ExceptionInterfaceVo exceptionInterface, String msg) {
        try {
            exceptionInterface.exceptionUtil();
        } catch (Exception e) {
            logger.info(msg);
            e.printStackTrace();
        }
    }

    /**
     * <per>
     * <p>没有返回值的异常处理（多参数）</p>
     *<p>用于参数处理<p/>
     * <per/>
     *
     * @param exceptionInterface
     * @param msg
     * @param ec
     * @return void
     * @throws
     * @Description
     * @author Liruilong
     * @Date 2020年08月18日  17:08:22
     **/
    public static void exceptionUtil(ExceptionInterfaceVs exceptionInterface, String msg, ErrorCode ec) {
        try {
            if (StringUtils.isBlank((String) exceptionInterface.exceptionUtil())) {
                throw new FileServiceException(msg, ec.getCode());
            }
        } catch (Exception e) {
            logger.info(msg);
            e.printStackTrace();
        }
    }


    /**
     * <per>
     * <p>有返回值的异常处理(多参数处理)</p>
     * <p>多参数处理</p>
     * <per/>
     *
     * @param exceptionInterfaceOs
     * @param msg
     * @param o
     * @return void
     * @throws
     * @Description todo Returns a value exception processing (Processing multi-parameter)
     * @author Liruilong
     * @Date 2020年08月13日  09:08:21
     **/
    public static Object exceptionUtils(ExceptionInterfaceOs exceptionInterfaceOs, String msg, Object... o) {
        Object old = null;
        try {
            old = exceptionInterfaceOs.exceptionUtil((Object[]) o);
        } catch (Exception e) {
            logger.info(msg);
            e.printStackTrace();
        }
        return old;
    }


    /**
     * <per>
     * <p>有返回值的异常处理（单参数）</p>
     * <per/>
     *
     * @param exceptionInterface
     * @param msg
     * @param o
     * @return void
     * @throws
     * @Description todo Exception handler returns a value (one-parameter)
     * @author Liruilong
     * @Date 2020年08月13日  09:08:21
     **/
    public static Object exceptionUtil(ExceptionInterfaceO exceptionInterface, String msg, Object o) {
        Object old = null;
        try {
            old = exceptionInterface.exceptionUtil(o);
        } catch (Exception e) {
            logger.info(msg);
            e.printStackTrace();
        }
        return old;
    }

    /**
     * <per>
     * <p>处理没有参数有返回值的异常处理，</p>
     * <per/>
     *
     * @param exceptionInterfaceVs
     * @param msg
     * @return java.lang.Object
     * @throws
     * @Description : TODO There is no process parameters exception handler return values
     * @author Liruilong
     * @Date 2020/9/21 20:26
     **/
    public static Object exceptionUtil(ExceptionInterfaceVs exceptionInterfaceVs, String msg) {
        Object old = null;
        try {
            old = exceptionInterfaceVs.exceptionUtil();
        } catch (Exception e) {
            logger.info(msg);
            e.printStackTrace();
        }
        return old;
    }




    /**
     * <per>
     * <p>文件转字节数组</p>
     * <per/>
     *
     * @param byteProcessInterface
     * @param o
     * @return byte[]
     * @throws
     * @Description
     * @author Liruilong
     * @Date 2020年08月13日  13:08:55
     **/
    public static byte[] toByte(ByteProcessInterface byteProcessInterface, Object o) {
        byte[] bytes = null;
        try {
            try (InputStream inputStream = (o instanceof InputStream) ? ((InputStream) o) : new FileInputStream((File) o)) {
                try (OutputStream byteArrayOutputStream = new ByteArrayOutputStream()) {
                    bytes = byteProcessInterface.process(inputStream, byteArrayOutputStream).toByteArray();
                }
            } catch (FileNotFoundException e) {
                throw new FileNotFoundException("文件未找到");
            }
        } catch (IOException e) {
            logger.info("转化字节数组异常");
            e.printStackTrace();
        }
        return bytes;
    }

    /**
     * <per>
     * <p> 文件转换为字符串</p>
     * <per/>
     *
     * @param inputStreamPeocess
     * @param file
     * @return java.lang.String
     * @throws
     * @Description
     * @author Liruilong
     * @Date 2020年08月17日  14:08:11
     **/
    public static String fileToBufferedReader(InputStreamPeocess inputStreamPeocess, File file) {
        String resoult = null;
        try (FileInputStream fileInputStream = new FileInputStream(file)) {
            try (InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream)) {
                try (BufferedReader bufferedReader = new BufferedReader(inputStreamReader)) {
                    resoult = inputStreamPeocess.peocess(bufferedReader);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            return resoult;
        }
    }

}
