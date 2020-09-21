package com.liruilong.fileserver.common.util.fileutil;


import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


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

    public static String getFileMd5(InputStream fileInputStream) throws IOException {
        if (fileInputStream == null) {
            return null;
        }
        // 缓冲区大小（这个可以抽出一个参数）
        int bufferSize = 512 * 1024;
        DigestInputStream digestInputStream = null;
        try {
            // 拿到一个MD5转换器（同样，这里可以换成SHA1）
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            // 使用DigestInputStream
            digestInputStream = new DigestInputStream(fileInputStream, messageDigest);
            // read的过程中进行MD5处理，直到读完文件
            byte[] buffer = new byte[bufferSize];
            while (digestInputStream.read(buffer) > 0);
            // 获取最终的MessageDigest
            messageDigest = digestInputStream.getMessageDigest();
            // 拿到结果，也是字节数组，包含16个元素
            byte[] resultByteArray = messageDigest.digest();
            // 同样，把字节数组转换成字符串
            return byteArrayToHex(resultByteArray);
        } catch (NoSuchAlgorithmException e) {
            return null;
        } finally {
            try {
                digestInputStream.close();
            } catch (Exception e) {
            }
            try {
                fileInputStream.close();
            } catch (Exception e) {
            }
        }
    }

    public static String byteArrayToHex(byte[] b) {
        String hs = "";
        String stmp = "";
        for (int n = 0; n < b.length; n++) {
            stmp = (Integer.toHexString(b[n] & 0XFF));
            if (stmp.length() == 1) {
                hs = hs + "0" + stmp;
            } else {
                hs = hs + stmp;
            }
            if (n < b.length - 1) {
                hs = hs + "";
            }
        }
        return hs;
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
