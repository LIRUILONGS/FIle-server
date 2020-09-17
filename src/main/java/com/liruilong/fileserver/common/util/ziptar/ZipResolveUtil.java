package com.liruilong.fileserver.common.util.ziptar;


import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.Channel;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;
import java.nio.charset.Charset;
import java.nio.charset.IllegalCharsetNameException;
import java.nio.charset.UnsupportedCharsetException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Enumeration;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

import static com.liruilong.fileserver.common.util.fileutil.FileUtil.cleanUtil;
import static com.liruilong.fileserver.common.util.fileutil.FileUtil.makeDirUtil;
import static com.liruilong.fileserver.common.util.interfaceutil.InterfaceUtilMethod.exceptionUtil;


/**
 * <per>
 * <p><p/>
 * <per/>
 *
 * @Author Liruilong
 * @Date 2020/8/12 13:38
 * @Description: Zip压缩文件解构工具类
 */
public class ZipResolveUtil {

    /**
     * <per>
     * <p>本地文件存放路径</p>
     * <per/>
     **/
    public static String FILEPATH = "C:\\Users\\admin\\Desktop" + File.separator;

    private ZipResolveUtil() {
        throw new AssertionError();
    }

    /**
     * <per>
     * <p> Zip字节包并解压</p>
     * <p>接受字节数组</p>
     * zipInputStream + FileOutputStream :  5506
     * <per/>
     *
     * @param zipInput 输入字节数组
     * @param zipCoder 编码方式
     * @param rootPath 存放目录
     * @return java.lang.Boolean
     * @throws
     * @Description
     * @author Liruilong
     * @Date 2020年08月13日  18:08:26
     **/
    public static Boolean zipReadFile(byte[] zipInput, String zipCoder, String rootPath) {
        boolean mark = false;
        ZipEntry zipEntry;
        String targetPath = Objects.requireNonNull(rootPath) + File.separator + new SimpleDateFormat("yyyy/MM/dd/").format(new Date());
        Charset charset = checkCharset(Objects.requireNonNull(zipCoder));
        try (ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(zipInput)) {
            try (ZipInputStream zipInputStream = new ZipInputStream(byteArrayInputStream, charset)) {
                while (Objects.nonNull(zipEntry = zipInputStream.getNextEntry())) {
                    String zipEntryName = zipEntry.getName();
                    String fileName = targetPath + File.separator + zipEntryName;
                    if (zipEntry.isDirectory() || zipEntryName.indexOf("/") == zipEntryName.length() - 1) {
                        File dir = new File(fileName);
                        if (dir.exists()) {
                            cleanUtil(dir);
                        }
                        makeDirUtil(fileName);
                        continue;
                    } else {
                        try (OutputStream outputStream = new FileOutputStream(fileName)) {
                            int len;
                            byte[] bytes = new byte[2048];
                            while ((len = zipInputStream.read(bytes)) != -1) {
                                outputStream.write(bytes, 0, len);
                            }
                        }
                    }
                }
            }
            mark = true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return mark;
    }

    /**
     * <per>
     * <p>Zip解压</p>
     * 使用：ReadableByteChannel + FileChannel : 2781
     * <per/>
     *
     * @param fileZip  解压文件
     * @param zipCoder 编码
     * @param rootPath 解压路径
     * @return java.lang.Boolean
     * @throws
     * @Description
     * @author Liruilong
     * @Date 2020年08月13日  20:08:53
     **/
    public static Boolean zipReadFile(File fileZip, String zipCoder, String rootPath) {
        boolean mark = false;
        Charset c = checkCharset(Objects.requireNonNull(zipCoder));
        ZipFile z = (ZipFile) exceptionUtil((o) -> {
            File file = (File) Objects.requireNonNull(o);
            if (!file.exists()) {
                throw new IOException();
            }
            return new ZipFile(fileZip, c);
        }, "Exceptions ： 解压文件初始异常！", fileZip);

        String targetPath = Objects.requireNonNull(rootPath) + File.separator +
                new SimpleDateFormat("yyyy" + File.separator + "MM" + File.separator + "dd").format(new Date());
        Enumeration<? extends ZipEntry> entries = z.entries();
        while (entries.hasMoreElements()) {
            ZipEntry entry = entries.nextElement();
            String fileName = targetPath + File.separator + entry.getName();
            if (entry.isDirectory()) {
                File dir = new File(fileName);
                if (dir.exists()) {
                    cleanUtil(dir);
                }
                makeDirUtil(fileName);
                continue;
            } else {
                int size = ((int) entry.getSize());
                try (InputStream inputStream = z.getInputStream(entry)) {
                    try (Channel channeInt = Channels.newChannel(inputStream)) {
                        try (Channel channeOut = new FileOutputStream(fileName).getChannel();) {
                            ByteBuffer byteBuffer = ByteBuffer.allocate(size);
                            while (true) {
                                byteBuffer.clear();
                                int len;
                                if ((len = ((ReadableByteChannel) channeInt).read(byteBuffer)) == -1) {
                                    break;
                                }
                                byteBuffer.flip();
                                ((FileChannel) channeOut).write(byteBuffer);
                            }
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            mark = true;
        }
        return mark;
    }


    /**
     * <per>
     * <p>本地构建Zip文件(文件的Zip打包)</p>
     * <p>
     * 1. 创建单个文件(目录)进行zip打包(单参)
     * 2. 将多个单文件(目录)添加到现有zip包中(多参):(目标zip,文件1，文件2...)</p>
     * <per/>
     *
     * @param o is <code>String,File</code>
     * @return void
     * @throws
     * @Description
     * @author Liruilong
     * @Date 2020年08月19日  11:08:15
     **/
    public static void thisLocalityFileCreateZip(Object... o) {
        // 异常处理
        exceptionUtil(() -> {
            File f = null;
            net.lingala.zip4j.ZipFile zipFile = null;
            for (int i = 0; i < o.length; i++) {
                if (o[i] instanceof String) {
                    f = new File((String) o[i]);
                } else if (o[i] instanceof File) {
                    f = (File) o[i];
                } else {
                    throw new IllegalArgumentException("参数异常");
                }
                if (i == 0) {
                    String fn = f.getName();
                    String fN = fn.substring(0, fn.indexOf(".") != -1 ? fn.indexOf(".") : fn.length());
                    String s = FILEPATH + fN + ".zip";
                    if (new File(s).exists()) {
                        cleanUtil(s);
                    }
                    zipFile = new net.lingala.zip4j.ZipFile(s);
                    zipFile.setRunInThread(true);
                }
                if (f.isDirectory()) {
                    zipFile.addFolder(f);
                } else if (f.isFile()) {
                    zipFile.addFile(f);
                }
            }
        }, "本地构建Zip文件异常");
    }


    public static void main(String[] args) throws Exception {
        // System.out.println(Charset.defaultCharset());
        //  System.out.println(Charset.availableCharsets());
        //  System.out.println("返回数据：" + checkCharset("GBK"));
        //InputStream inputStream = new FileInputStream(new File("C:\\Users\\admin\\Desktop\\超时处理20200813\\110kV磨憨（口岸）输变电工程变电工程（预审优化）.zip"));
        // 使用：ReadableByteChannel + FileChannel : 2781
        //  zipReadFile(new File("C:\\Users\\admin\\Desktop\\超时处理20200813\\110kV磨憨（口岸）输变电工程变电工程（预审优化）.zip"), "GBK", "C:\\Users\\admin\\Desktop");

        //使用：zipInputStream + FileOutputStream :  5506
        // zipReadFile(fileReadByteUtil(""), "GBK", "C:\\Users\\admin\\Desktop");
        //  thisLocalityCreateZip(new File("C:\\Users\\admin\\Desktop\\【java经验总结】Effective Java（中文版第3版）.pdf"),"C:\\Users\\admin\\Desktop\\旧数据.json","C:\\Users\\admin\\Desktop\\新数据.json","C:\\Users\\admin\\Desktop\\超时处理20200813.zip");
        //thisLocalityCreateZip("C:\\Users\\admin\\Desktop\\超时处理20200813.zip","C:\\Users\\admin\\Desktop\\旧数据.json");
        //   thisLocalityFileCreateZip("C:\\Users\\admin\\Desktop\\新数据.json");
        // thisLocalityFileCreateZip("C:\\Users\\admin\\Desktop\\common");
        thisLocalityFileCreateZip("C:\\Users\\admin\\Desktop\\新数据.zip", "C:\\Users\\admin\\Desktop\\common.zip", "C:\\Users\\admin\\Desktop\\common", "C:\\Users\\admin\\Desktop\\【java经验总结】Effective Java（中文版第3版）.pdf");
        // main1();
     /*   String stringPassword ="password";
        System.out.print("密码的值为: ");
        System.out.println(stringPassword);
        System.out.println("密码的 hashCode 为: "
                + Integer.toHexString(stringPassword.hashCode()));

        String newString = "********";
        stringPassword.replace(stringPassword, newString);

        System.out.println("试图替换密码后的密码值: "+stringPassword);
        System.out.println("在尝试替换原始字符串之后 hashCode : "
                        + Integer.toHexString(stringPassword.hashCode()));*/

        char[] charPassword = new char[]{'p', 'a', 's', 's', 'w', 'o', 'r', 'd'};

        System.out.print("密码的值为: ");
        System.out.println(charPassword);
        System.out.println(
                "密码的 hashCode 为: "
                        + Integer.toHexString(charPassword.hashCode()));
        // 可以理解为指定的数组初识化一组默认值以外的一组值，可以做区间处理。
        Arrays.fill(charPassword, '*');

        System.out.print("试图替换密码后的密码值: ");
        System.out.println(charPassword);
        System.out.println(
                "在尝试替换原始字符串之后 hashCode: "
                        + Integer.toHexString(charPassword.hashCode()));

        String passwordString = "password";
        char[] passwordArray = new char[]{'p', 'a', 's', 's', 'w', 'o', 'r', 'd'};
        System.out.println("Printing String password -> " + passwordString);
        System.out.println("Printing char[] password -> " + passwordArray);
    }


    /**
     * @param zipCoder
     * @return java.nio.charset.Charset
     * <per>
     * <p>字符集校验<p/>
     * <per/>
     * @Description
     * @author Liruilong
     * @Date 2020年08月12日  14:08:53
     **/
    public static Charset checkCharset(String zipCoder) {
        Charset charset = null;
        try {
            charset = Charset.forName(zipCoder);
        } catch (IllegalCharsetNameException e) {
            System.err.println("字符集名称为空！");
        } catch (UnsupportedCharsetException e) {
            System.err.println("字符集名称非法");
        } catch (IllegalArgumentException e) {
            System.err.println("不支持命名的字符集!");
        }
        return charset;
    }

    /**
     * <per>
     * <p>控制台进度条</p>
     * <per/>
     *
     * @param
     * @return void
     * @throws
     * @Description
     * @author Liruilong
     * @Date 2020年08月19日  15:08:12
     **/
    public static void progress(String s) {
        Thread thread = new Thread(() -> {
            StringBuilder tu = new StringBuilder("▧");
            while (true) {
                tu.append("▧");
                System.out.print("\r本地构建" + "Zip中：\t" + tu + "\t");
                try {
                    TimeUnit.MILLISECONDS.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.setDaemon(true);
        thread.start();

    }


}
