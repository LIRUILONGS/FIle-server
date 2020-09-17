package com.liruilong.fileserver.common.annotation;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Liruilong
 * @Date 2020/8/18 14:16
 * @Description:
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface PropertyDesc {
    String value() default "";

    String dateFormat() default "";

    String desc() default "";

    String type() default "";

    String dict() default "";

    boolean isFile() default false;
}
