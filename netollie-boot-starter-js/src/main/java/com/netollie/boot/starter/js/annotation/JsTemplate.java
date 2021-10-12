package com.netollie.boot.starter.js.annotation;

import java.lang.annotation.*;

/**
 * <p>
 * Java Script代码模板
 * </p>
 *
 * @author netollie
 * @since 1.0.0
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface JsTemplate {
    /*
     * 代码模板文本
     */
    String value();
}