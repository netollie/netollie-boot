package com.netollie.boot.commons.util;

import org.junit.Assert;
import org.junit.Test;

import javax.script.Bindings;
import javax.script.ScriptException;
import javax.script.SimpleBindings;

/**
 * <p>
 * Java Script工具类单元测试
 * </p>
 *
 * @author netollie
 * @date 2021/10/26
 */
public class JsUtilTest {
    /**
     * <p>
     * 执行单元测试
     * </p>
     *
     * @author netollie
     * @date 2021/10/26
     */
    @Test
    public void doTest() throws ScriptException {
        String script = "value + 1";
        Bindings bindings = new SimpleBindings();
        bindings.put("value", 100);
        Assert.assertEquals(101D, JsUtil.eval(script, bindings));
    }
}
