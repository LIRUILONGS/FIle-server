package com.liruilong.fileserver.common.util.fileutil;


import org.springframework.util.DigestUtils;

import java.io.*;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.util.List;
import java.util.Objects;

import static com.liruilong.fileserver.common.util.interfaceutil.InterfaceUtilMethod.*;
import static com.liruilong.fileserver.common.util.ziptar.ZipResolveUtil.checkCharset;
import static org.apache.commons.io.FileUtils.*;


/**
 * @Author Liruilong
 * @Date 2020/8/12 15:53
 * @Description:
 */
public class FileUtil {

    private static final char[] hexadecimal = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};


    private FileUtil() {
        throw new AssertionError();
    }

    /**
     * @param file
     * @return java.lang.String
     * <per>
     * <p>文件的MD5<p/>
     * <per/>
     * @Description
     * @author Liruilong
     * @Date 2020年08月13日  08:08:58
     **/
    public static String getMd5(File file) {
        StringBuilder sb = new StringBuilder();
        try {
            InputStream in = new FileInputStream(file);

            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            byte[] bytes = new byte[1024];
            int byteCount = 0;
            while ((byteCount = in.read(bytes)) != -1) {
                messageDigest.update(bytes, 0, byteCount);
            }
            byte[] digest = messageDigest.digest();

            for (byte b : digest) {
                int a = b & 0xff;
                String hex = Integer.toHexString(a);

                if (hex.length() == 1) {
                    hex = 0 + hex;
                }
                sb.append(hex);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    public static String getMD5(File file) {
        String s = null;
        s = exceptionUtil((f) -> {
            try (InputStream inputStream = new FileInputStream((File) f)) {
                String md5DigestAsHex = DigestUtils.md5DigestAsHex(inputStream);
                return md5DigestAsHex;
            }
        }, "获取Md5失败", file).toString();
        return s;
    }

    /**
     * <per>
     * <p>字节的MD5</p>
     * <per/>
     *
     * @param binaryData
     * @return java.lang.String
     * @throws
     * @Description
     * @author Liruilong
     * @Date 2020年08月14日  11:08:12
     **/
    public static String encodeMd5(byte[] binaryData) {
        if (binaryData.length != 16) {
            return null;
        } else {
            char[] buffer = new char[32];

            for (int i = 0; i < 16; ++i) {
                int low = binaryData[i] & 15;
                int high = (binaryData[i] & 240) >> 4;
                buffer[i * 2] = hexadecimal[high];
                buffer[i * 2 + 1] = hexadecimal[low];
            }
            return new String(buffer);
        }
    }


    /**
     * @param path
     * @param list
     * @return java.util.List<java.lang.String>
     * <per>
     * <p>获取文件夹里所有文件及所有子目录内文件<p/>
     * <per/>
     * @Description
     * @author Liruilong
     * @Date 2020年08月12日  17:08:22
     **/
    public static List<String> recursiveFiles(String path, List<String> list) {
        File file = new File(path);
        File files[] = file.listFiles();
        if (files == null || files.length == 0) {
            return null;
        }
        for (File f : files) {
            if (f.isDirectory()) {
                recursiveFiles(f.getAbsolutePath(), list);
            } else if (f.isFile()) {
                list.add(f.getAbsolutePath());
            }
        }
        return list;
    }


    /**
     * <per>
     * <p>递归删除目录</p>
     * <per/>
     *
     * @param sourceFile
     * @return void
     * @throws
     * @Description
     * @author Liruilong
     * @Date 2020年08月13日  09:08:14
     **/
    public static void cleanUtil(File sourceFile) {
        if (Objects.nonNull(sourceFile)) {
            if (sourceFile.isDirectory()) {
                String sc[] = sourceFile.list();
                if (sc == null || sc.length <= 0) {
                    sourceFile.delete();
                } else {
                    for (String fname : sc) {
                        String fpath = sourceFile.getPath() + File.separator + fname;
                        File f2 = new File(fpath);
                        if (f2.exists() && f2.isFile()) {
                            f2.delete();
                        } else if (f2.exists() && f2.isDirectory()) {
                            cleanUtil(f2);
                        }
                    }
                    sourceFile.delete();
                }
            } else if (sourceFile.exists()) {
                sourceFile.delete();
            }
        }
    }

    /**
     * <per>
     * <p>递归删除目录</p>
     * <per/>
     *
     * @param o 可以为：<code>File、String</code>
     * @return void
     * @throws
     * @Description
     * @author Liruilong
     * @Date 2020年08月14日  13:08:36
     **/
    public static void cleanUtil(Object o) {
        exceptionUtilNo((file) -> {
            cleanUtil((File) file);
        }, "删除目录为空！", Objects.requireNonNull(o) instanceof String ? new File((String) o) : o);
    }


    /**
     * @param pathName
     * @return void
     * <per>
     * <p>文件夹递归创建<p/>
     * <per/>
     * @Description
     * @author Liruilong
     * @Date 2020年08月12日  18:08:43
     **/
    public static void makeDirUtil(String pathName) {
        exceptionUtilNo((fileName) -> {
            File file = new File((String) fileName);
            if (!file.exists()) {
                file.mkdirs();
            }
        }, "Exceptions ： 文件夹创建失败！", pathName);
    }


    /**
     * <per>
     * <p>文件转化为字节数组</p>
     * <p>函数签名为：InputStream, OutputStream ——>ByteArrayOutputStream</p>
     * <per/>
     *
     * @param o 可以为<code>File、InputStream、String</code>
     * @return byte[]
     * @throws
     * @Description
     * @author Liruilong
     * @Date 2020年08月14日  10:08:45
     **/
    public static byte[] fileReadByteUtil(Object o) {
        o = Objects.requireNonNull(o, "参数为空") instanceof String ? new File((String) o) : o;
        return toByte((input, output) -> {
            byte[] buffer = new byte[input.available()];
            int i = 0;
            while ((i = input.read(buffer)) != -1) {
                output.write(buffer, 0, i);
            }
            return (ByteArrayOutputStream) output;
        }, o);
    }


    /**
     * <per>
     * <p>文件转化为字节数组</p>
     * <p>包装commons方法</p>
     * <per/>
     *
     * @param file 参数可以为<code>File</code>
     * @return byte[]
     * @throws
     * @Description
     * @author Liruilong
     * @Date 2020年08月17日  14:08:17
     **/
    public static byte[] readFileToByteArrayUtil(File file) {
        return (byte[]) exceptionUtil((o) -> {
            return readFileToByteArray((File) o);
        }, "文件转化为字节数组", file);
    }


    /**
     * <per>
     * <p>文件转化为字符串,指定編碼</p>
     * <p>包装commons方法</p>
     * <per/>
     *
     * @param file
     * @param charSet
     * @return java.lang.String
     * @throws
     * @Description
     * @author Liruilong
     * @Date 2020年08月17日  14:08:14
     **/
    public static String readFileToStringUtil(File file, String charSet) {
        return (String) exceptionUtils((os) ->
                        readFileToString((File) os[0], (String) os[1])
                , "文件转化为字符串异常", file, charSet);
    }


    /**
     * <per>
     * <p>文件转化为字符串</p>
     * <per/>
     *
     * @param file
     * @return java.lang.String
     * @throws
     * @Description
     * @author Liruilong
     * @Date 2020年08月17日  17:08:17
     **/
    public static String readJsonToStringUtil(File file) {
        return fileToBufferedReader((bufferedReader) -> {
            String str = null;
            StringBuilder stringBuilder = new StringBuilder();
            while ((str = bufferedReader.readLine()) != null) {
                stringBuilder.append(str);
            }
            return stringBuilder.toString();
        }, file);
    }


    /**
     * <per>
     * <p>清空文件夹.不删除顶层目录</p>
     * <per/>
     *
     * @param o 可以为：<code>File、String</code>
     * @return void
     * @throws
     * @Description
     * @author Liruilong
     * @Date 2020年08月14日  14:08:02
     **/
    public static void clearFileUtil(Object o) {
        exceptionUtilNo((file) -> {
            cleanDirectory(Objects.requireNonNull(file, "参数为空") instanceof String ? new File((String) file) : (File) file);
        }, "清空文件夹异常", o);

    }


    /**
     * <per>
     * <p>比较两个文件的内容，以确定它们是否相等</p>
     * <p>如果文件的内容相等或都不存在，则为true，否则为false</p>
     * <per/>
     *
     * @param file <code>参数为：File，File，String</code>
     * @return boolean
     * @throws
     * @Description
     * @author Liruilong
     * @Date 2020年08月17日  11:08:05
     **/
    public static boolean contentEqualsUtil(File file, File file1, String charSet) {
        return (Boolean) exceptionUtils((os) ->
                        contentEqualsIgnoreEOL((File) os[0], (File) os[1], (String) os[2])
                , "文件内容比较异常", file, file1, charSet);
    }


    /**
     * <per>
     * <p>文本内容轉化為字符串列表</p>
     * <per/>
     *
     * @param file
     * @param charSet
     * @return java.util.List<java.lang.String>
     * @throws
     * @Description
     * @author Liruilong
     * @Date 2020年08月17日  17:08:58
     **/
    public static List<String> readLinesUtil(File file, String charSet) {
        return (List<String>) exceptionUtils((os) ->
                        readLines((File) os[0], (Charset) os[1])
                , "文本内容轉化為字符串列表異常", file, checkCharset(charSet));
    }


    /**
     * <per>
     * <p>获取文件大小异常</p>
     * <per/>
     *
     * @param o 可以为：<code>File、String</code>
     * @return java.lang.Long
     * @throws
     * @Description
     * @author Liruilong
     * @Date 2020年08月17日  17:08:17
     **/
    public static Long fileSizeUtil(Object o) {
        return (Long) exceptionUtil((file) -> {
            return sizeOfAsBigInteger(Objects.requireNonNull(file, "参数为空") instanceof String ? new File((String) file) : (File) file).longValue();
        }, "获取文件大小异常", o);

    }


    /**
     * <per>
     * <p>获取目录大小异常</p>
     * <per/>
     *
     * @param o 可以为：<code>File、String</code>
     * @return java.lang.Long
     * @throws
     * @Description
     * @author Liruilong
     * @Date 2020年08月17日  17:08:59
     **/
    public static Long fileDirSizeUtil(Object o) {
        return (Long) exceptionUtil((file) -> {
            return sizeOfDirectoryAsBigInteger(Objects.requireNonNull(file, "参数为空") instanceof String ? new File((String) file) : (File) file).longValue();
        }, "获取目录大小异常", o);
    }


    public static void main(String[] args) throws FileNotFoundException {
        // 未使用流  220M未使用流:165   2.4G 未使用流:22996  26.1 GB 未使用流:144
       /* String name1 = "J:\\RHCE8模拟环境";
        File file = new File(name1);
        long l = System.currentTimeMillis();
        clean(file);
        System.out.println("未使用流:" + (System.currentTimeMillis() - l));*/
        // 使用流 ：220MB 使用流:10423   2.4GB 使用流:23429   26.1 GB 使用流:173
      /*  String name2 = "C:\\Users\\admin\\Desktop\\RHCE8模拟环境\\RHCE8模拟环境";
        File file2 = new File(name2);
        long l1 = System.currentTimeMillis();
        fileClean(file2);
        System.out.println("使用流:" + (System.currentTimeMillis() - l1));*/
        //String s = null;
        //clean(s);
        //InputStream inputStream = new FileInputStream("C:\\Users\\admin\\Desktop\\common\\src\\main\\java\\com\\liruilong\\common\\Calzone.java");

        // System.out.println(getMd5(new File("D:\\NLP")));
        //fileReadByte(new File(""));
        // System.out.println(encodeMd5(fileReadByte("D:\\NLP")));

        if (true ^ true) {
            System.out.println("全為真？");
        }
        if (false ^ false) {
            System.out.println("全為假");
        }
        if (false ^ true) {
            System.out.println("有一個為真");
        }
        fileSizeUtil("");
        //System.out.println(getMd5(new File("D:\\新建文件夹\\apache-maven-3.3.9.zip")));


    }


}
