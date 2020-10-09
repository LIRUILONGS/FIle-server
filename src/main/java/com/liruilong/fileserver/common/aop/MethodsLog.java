package com.liruilong.fileserver.common.aop;


import java.lang.annotation.*;


/**
 * TODO Declare a log annotation as the entry point for AOP
 * @author Liruilong
 * @Date: 2020/7/29 14:38
 * @Description: 声明一个日志注解作为AOP的切入点
 */
@Target({ ElementType.METHOD, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MethodsLog {
     FileCRUDEnum operType() default FileCRUDEnum.OPERATION;
     String remark() default "备注";
     String paramData() default "参数";
}
