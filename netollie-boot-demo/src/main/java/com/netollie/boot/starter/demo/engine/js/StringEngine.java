package com.netollie.boot.starter.demo.engine.js;

import com.netollie.boot.starter.js.annotation.JsTemplate;
import com.netollie.boot.starter.js.annotation.JsEngine;
import com.netollie.boot.starter.js.annotation.JsVariable;

/**
 * <p>
 * Java Script字符串引擎
 * </p>
 *
 * @author netollie
 * @since 1.0.0
 */
@JsEngine
public interface StringEngine {
    /**
     * <p>
     * 字符串escape操作
     * </p>
     *
     * @param value 原始字符串
     * @return escape结果
     * @author netollie
     * @since 1.0.0
     */
    @JsTemplate("escape(value)")
    String escape(@JsVariable("value") String value);
}
