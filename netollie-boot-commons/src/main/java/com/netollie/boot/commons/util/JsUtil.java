package com.netollie.boot.commons.util;

import jdk.nashorn.api.scripting.ClassFilter;
import jdk.nashorn.api.scripting.NashornScriptEngineFactory;

import javax.script.Bindings;
import javax.script.ScriptEngine;
import javax.script.ScriptException;

/**
 * <p>
 * Java Script工具类
 * </p>
 *
 * @author netollie
 * @since 1.0.0
 */
public final class JsUtil {
    /**
     * Java Script代码引擎
     * 全局只能存在1个实例m, n个实例会产生n倍的eval类, 从而占用metaspace
     */
    private static final ScriptEngine SCRIPT_ENGINE;

    static {
        NashornScriptEngineFactory nashornScriptEngineFactory = new NashornScriptEngineFactory();
        // 禁止一切类库调用, 避免代码注入风险
        ClassFilter classFilter = (String className) -> false;
        SCRIPT_ENGINE = nashornScriptEngineFactory.getScriptEngine(classFilter);
    }

    /**
     * <p>
     * 私有构造方法
     * <p>
     *
     * @author netollie
     * @since 1.0.0
     */
    private JsUtil() {
        // do nothing
    }

    /**
     * <p>
     * 执行java script代码
     * 由于引擎是非线程安全的, 需要加上同步锁
     * </p>
     *
     * @param script 代码
     * @param bindings 参数
     * @return 执行结果
     * @throws ScriptException 执行结果
     * @author netollie
     * @since 1.0.0
     */
    public static synchronized Object eval(String script, Bindings bindings) throws ScriptException {
        return SCRIPT_ENGINE.eval(script, bindings);
    }
}
