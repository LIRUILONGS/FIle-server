package com.liruilong.fileserver.common.util.fileutil;


import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.net.URISyntaxException;


import static com.liruilong.fileserver.common.util.interfaceutil.InterfaceUtilMethod.exceptionUtils;
import static org.apache.commons.io.IOUtils.copyLarge;

/**
 * @author Liruilong
 * @Date 2020/8/17 17:47
 * @Description:
 */
public class IoUtil {

    private IoUtil() {

    }

    /**
     * <per>
     * <p>流转化</p>
     * <per/>
     *
     * @param input
     * @param Output
     * @return boolean
     * @throws
     * @Description
     * @author Liruilong
     * @Date 2020年08月17日  17:08:34
     **/
    public static void InputCopyOutput(InputStream input, OutputStream Output) {
        exceptionUtils((os) ->
                        copyLarge((InputStream) os[0], (OutputStream) os[1])
                , "流转化异常", input, Output);
    }


    public static void main(String[] args) {
        try {
            System.out.println(IOUtils.toString(new URI("https://www.baidu.com/s?ie=UTF-8&wd=BigInteger"), "UTF-8"));
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
