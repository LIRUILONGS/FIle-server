package com.liruilong.fileserver.common.util.ThreadUtil;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.apache.commons.lang3.StringUtils;

import java.util.concurrent.*;

/**
 * @Description :
 * @Author: Liruilong
 * @Date: 2020/10/7 11:21
 */
public class ThreadFactoryUtil {
    private ThreadFactoryUtil(){}

    /**
     * @param
     * @return
     * @description 使用guava的ThreadFactoryBuilder来创建线程池
     * TODO Use Guava's Thread Factory Builder to create a thread pool
     * @author Liruilong
     * @date  2020年10月07日  11:10:30
     **/

    public static ThreadFactory builder(String ...s){
        if (s.length > 0 && StringUtils.isNotBlank(s[0])){
            return new ThreadFactoryBuilder().setNameFormat(s[0]+"ThreadPool-%d")
                    .build();
        }else {
            return new ThreadFactoryBuilder().setNameFormat("TimerTaskThread-Pool-%d").build();
        }
    }

    public static void main(String[] args) {
        ExecutorService service = new ThreadPoolExecutor(1,1,0L,
                TimeUnit.MILLISECONDS,new LinkedBlockingQueue<Runnable>(1024),ThreadFactoryUtil.builder(),new ThreadPoolExecutor.AbortPolicy());
        for (int i = 0; i < 15; i++) {
            service.execute(() ->System.out.println(Thread.currentThread().getName()));

        }

        service.shutdown();
    }

}
