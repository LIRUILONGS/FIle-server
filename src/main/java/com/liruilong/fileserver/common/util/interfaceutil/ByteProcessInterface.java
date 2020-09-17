package com.liruilong.fileserver.common.util.interfaceutil;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @author Liruilong
 * @Date 2020/8/13 11:50
 * @Description:
 */
@FunctionalInterface
public interface ByteProcessInterface {

    /**
     * <per>
     * <p>文件转换为字节数组</p>
     * <per/>
     *
     * @param inputStream
     * @param outputStream
     * @return ByteArrayOutputStream
     * @throws IOException
     * @Description
     * @author Liruilong
     * @Date 2020年08月13日  13:08:51
     **/
    ByteArrayOutputStream process(InputStream inputStream, OutputStream outputStream) throws IOException;
}


