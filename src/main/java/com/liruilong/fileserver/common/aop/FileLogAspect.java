package com.liruilong.fileserver.common.aop;


import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.liruilong.fileserver.common.util.DateUtil;
import com.liruilong.fileserver.common.util.IpUtil;
import com.liruilong.fileserver.common.util.ThreadUtil.ThreadFactoryUtil;
import com.liruilong.fileserver.common.util.UUIDUtil;
import com.liruilong.fileserver.fserve.domain.OperateLog;
import com.liruilong.fileserver.fserve.service.OperateLogService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.concurrent.*;

/**
 * @Auther: Liruilong
 * @Date: 2020/7/29 14:33
 * @Description:
 */
@Aspect
@Component
public class FileLogAspect {

    private Logger logger4j = LoggerFactory.getLogger(FileLogAspect.class);

    private static ExecutorService threadPool = new ThreadPoolExecutor(1, 1, 0L,
            TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>(1024), ThreadFactoryUtil.builder("Log"), new ThreadPoolExecutor.AbortPolicy());


    @Autowired
    private OperateLogService operateLogService;

    @Pointcut("@annotation(com.liruilong.fileserver.common.aop.MethodsLog)")
    public void pointCut() {
    }

    @Around("pointCut()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        threadPool.execute(() -> handleLog(joinPoint, "--->[begin!]", 0));
        Object obj = joinPoint.proceed();
        threadPool.execute(() -> handleLog(joinPoint, "--->[finish!]", 0));
        return obj;
    }


    @AfterThrowing(value = "pointCut()", throwing = "errorMsg")
    public void afterThrowException(JoinPoint joinpoint, Exception errorMsg) {
        threadPool.execute(() -> handleLog(joinpoint, "--->[Exception!]", 1));
    }


    /**
     * @param joinPoint
     * @param joinPoint
     * @return void
     * @Description 切入点处理器
     * @Author Liruilong
     * @Date 2020年07月29日  15:07:24
     **/
    private void handleLog(JoinPoint joinPoint, Object... o) {
        MethodsLog loggerAnnotation = giveController(joinPoint);
        if (loggerAnnotation == null) {
            return;
        }
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = null;
        String ipAddr = null;
        if (null != attributes) {
            request = attributes.getRequest();
            ipAddr = IpUtil.getIpAddr(request);
        } else {
            ipAddr = "未知";
        }
        String methodName = joinPoint.getSignature().getName();
        String className = joinPoint.getTarget().getClass().getName();
        OperateLog operateLog = new OperateLog().setId(UUIDUtil.builder()).setModule(loggerAnnotation.remark())
                .setRemark(o[0] + ":" + Arrays.toString(joinPoint.getArgs()))
                .setClassName(className).setMethod(methodName).setIp(ipAddr).setOperType(loggerAnnotation.operType().getName())
                .setCreateTime(DateUtil.builder()).setUserId("123").setUserName("小明").setStatus((Integer) (o[1]));
        operateLog.toString();
        operateLogService.saveOperateLog(operateLog);

    }


    /**
     * <p> 由切入点对象得到注解对象<p/>
     *
     * @param joinPoint
     * @return
     */
    private static MethodsLog giveController(JoinPoint joinPoint) {
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method method = methodSignature.getMethod();
        return method != null ? method.getAnnotation(MethodsLog.class) : null;
    }


    /**
     * <p>由类名得到一个元类<p/>
     *
     * @param classFindInterface
     * @param className
     * @return
     */
    public Class classFind(ClassFindInterface classFindInterface, String className) {
        Class<?> clazz = null;
        try {
            clazz = classFindInterface.classNametoClass(className);
        } catch (ClassNotFoundException e) {
            logger4j.error("˙·．.．·˙`˙·．..．·…┉═∞═…┉ ═∞═┈━═┈━═┈━═┈━═┈━═☆☆☆☆、" + e.getMessage() + "☆☆☆☆☆☆☆☆☆");
            e.printStackTrace();
        }
        return clazz;

    }

        /* @After("pointCut()")
    public void doAfter(JoinPoint joinPoint) {
        logger4j.warn("˙·．.．·˙`˙·．..．·…┉═∞═…┉ ═∞═┈━═┈━═┈━═┈━═┈━═☆、后置返回通知—-┈━═┈━═┈━═");
    }*/

   /* @Around("pointCut()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable{
        logger4j.warn("˙·．.．·˙`˙·．..．·…┉═∞═…┉ ═∞═┈━═┈━═┈━═┈━═┈━═☆、环绕前通知—-┈━═┈━═┈━═");
        Object obj= joinPoint.proceed();
        logger4j.warn("˙·．.．·˙`˙·．..．·…┉═∞═…┉ ═∞═┈━═┈━═┈━═┈━═┈━═☆、环绕后通知—-┈━═┈━═┈━═");
        return  obj;
    }*/
  /* @Before("pointCut()")
   public void doBefore(JoinPoint joinPoint) {
       threadPool.execute(() -> handleLog(joinPoint, "--->[begin!]", 0));
   }

    @AfterReturning("pointCut()")
    public void doAfterReturning(JoinPoint joinPoint) {
        threadPool.execute(() -> handleLog(joinPoint, "--->[finish!]", 0));
    }
*/



}
