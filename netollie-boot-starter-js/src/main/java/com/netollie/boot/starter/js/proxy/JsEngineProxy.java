package com.netollie.boot.starter.js.proxy;

import com.netollie.boot.commons.util.JsUtil;
import com.netollie.boot.starter.js.annotation.JsTemplate;
import com.netollie.boot.starter.js.annotation.JsVariable;

import javax.script.Bindings;
import javax.script.SimpleBindings;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

/**
 * <p>
 * Java Script引擎代理类
 * </p>
 *
 * @author netollie
 * @since 1.0.0
 */
public class JsEngineProxy implements InvocationHandler {
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // 获取代码模板
        JsTemplate template = method.getAnnotation(JsTemplate.class);
        String script = template.value();
        // 聚合变量
        Parameter[] parameters = method.getParameters();
        int parameterCount = parameters.length;
        Bindings bindings = new SimpleBindings();
        for (int i = 0; i < parameterCount; i++) {
            JsVariable variable = parameters[i].getAnnotation(JsVariable.class);
            bindings.put(variable.value(), args[i]);
        }
        // 执行JS代码,获取结果
        return JsUtil.eval(script, bindings);
    }
}
