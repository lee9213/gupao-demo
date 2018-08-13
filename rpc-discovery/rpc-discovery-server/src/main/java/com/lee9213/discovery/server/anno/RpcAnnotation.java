package com.lee9213.discovery.server.anno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author lee9213@163.com
 * @version 1.0
 * @date 2018-08-13 0:43
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface RpcAnnotation {


    Class<?> name();

    String version() default "";
}
