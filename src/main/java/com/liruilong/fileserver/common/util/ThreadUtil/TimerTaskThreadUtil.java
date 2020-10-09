package com.liruilong.fileserver.common.util.ThreadUtil;

import com.liruilong.fileserver.common.util.DateUtil;

import java.util.Objects;

/**
 * @Description : 显示定义线程名
 * TODO Display definition thread name
 * @Author: Liruilong
 * @Date: 2020/10/6 22:24
 */
public class TimerTaskThreadUtil extends Thread {


    public TimerTaskThreadUtil(Runnable r){
        super(r);
    }

    public TimerTaskThreadUtil(ThreadGroup threadGroup,Runnable r){
        super(threadGroup,r );
    }
    public TimerTaskThreadUtil(ThreadGroup threadGroup,Runnable r,String s){
        super(threadGroup,r,s );
    }
    public TimerTaskThreadUtil(ThreadGroup threadGroup,String s){
        super(threadGroup,s );
    }
    public TimerTaskThreadUtil(ThreadGroup threadGroup,Runnable r,String s,Long l){
        super(threadGroup,r,s ,l);
    }

    /**
     * @param threadName
     * @return
     * TODO Specify thread name to define thread
     * @description 指定线程名字定义线程
     * @author Liruilong
     * @date  2020年10月06日  22:10:36
     **/

    public TimerTaskThreadUtil(String threadName){
        super.setName(Objects.requireNonNull(threadName,"线程名初始化异常"));
    }
    public TimerTaskThreadUtil(){
        super("TimerTaskThread-"+ DateUtil.builder().toString());
    }
    /**
     * @param threadName
     * @param b
     * @return
     *  TODO Define the daemon
     * @description 定义守护进程
     * @author Liruilong
     * @date  2020年10月06日  22:10:10
     **/

    public TimerTaskThreadUtil(String threadName,boolean b){
        super.setName(Objects.requireNonNull(threadName,"线程名初始化异常"));
        super.setDaemon(b);
    }



    public static void main(String[] args) {
      new   TimerTaskThreadUtil(() ->System.out.println("1211113")).start();
      new Thread(() ->System.out.println("123")).start();
    }
}
