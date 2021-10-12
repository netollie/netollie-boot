package com.netollie.boot.starter.js.autoconfigure;

import com.netollie.boot.beans.factory.support.ClassPathBeanRegistrar;
import com.netollie.boot.beans.factory.support.ClassPathScanner;
import com.netollie.boot.starter.js.annotation.JsEngine;
import com.netollie.boot.starter.js.proxy.JsEngineProxy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;

import java.lang.reflect.Proxy;
import java.util.Objects;
import java.util.Set;
import java.util.function.Predicate;

/**
 * <p>
 * Java Script引擎自动装配
 * </p>
 *
 * @author netollie
 * @since 1.0.0
 */
@Slf4j
public class JsEngineAutoConfigure extends ClassPathBeanRegistrar implements EnvironmentAware {
    /**
     * Spring Environment
     */
    private Environment environment;

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    @Override
    protected void registerSingleton(ConfigurableListableBeanFactory beanFactory, Class<?> scannedClass) {
        ClassLoader classLoader = this.getClass().getClassLoader();
        // 为接口生成代理类并注册
        Object instance = Proxy.newProxyInstance(classLoader, new Class<?>[]{scannedClass}, new JsEngineProxy());
        String name = scannedClass.getSimpleName();
        log.info(String.format("Load js engine [%s]", name));
        beanFactory.registerSingleton(name, instance);
    }

    @Override
    protected Set<Class<?>> scanClass() {
        // 根据配置扫描出带有JsEngine注解的接口
        String basePackages = environment.getProperty("netollie.js-engine.base-packages");
        Predicate<Class<?>> classFilter = (Class<?> clazz) ->
            Objects.nonNull(clazz.getAnnotation(JsEngine.class)) && clazz.isInterface();
        return ClassPathScanner.scan(basePackages, classFilter);
    }
}
