package com.liruilong.fileserver.common.aop;


import java.lang.annotation.*;


/**
 * @author Liruilong
 * @Date: 2020/7/29 14:38
 * @Description:
 */
@Target({ ElementType.METHOD, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MethodsLog {
     FileCRUDEnum operType() default FileCRUDEnum.OPERATION;
     String remark() default "备注";
     String paramData() default "参数";
}
