package com.netollie.boot.starter.js.annotation;

import java.lang.annotation.*;

/**
 * <p>
 * Java Script变量
 * </p>
 *
 * @author netollie
 * @since 1.0.0
 */
@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface JsVariable {
    /*
     * 代码变量
     */
    String value();
}
