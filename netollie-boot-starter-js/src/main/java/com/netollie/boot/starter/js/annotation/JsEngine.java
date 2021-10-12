package com.netollie.boot.starter.js.annotation;

import java.lang.annotation.*;

/**
 * <p>
 * 标记一个接口为Java Script引擎
 * </p>
 *
 * @author netollie
 * @since 1.0.0
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface JsEngine {
}
