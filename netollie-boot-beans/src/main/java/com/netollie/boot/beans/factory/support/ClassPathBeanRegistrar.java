package com.netollie.boot.beans.factory.support;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.beans.factory.support.RootBeanDefinition;

import java.util.Set;

/**
 * <p>
 * 扫描类并进行自动注册
 * </p>
 *
 * @author netollie
 * @since 1.0.0
 */
public abstract class ClassPathBeanRegistrar implements BeanDefinitionRegistryPostProcessor {
    /*
     * 扫描到的类型
     */
    private Set<Class<?>> scannedClasses;

    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        this.scannedClasses = scanClass();
        scannedClasses.forEach((Class<?> clientInterface) -> {
            RootBeanDefinition rootBeanDefinition = new RootBeanDefinition();
            rootBeanDefinition.setTargetType(clientInterface);
            rootBeanDefinition.setBeanClassName(clientInterface.getName());
            registry.registerBeanDefinition(clientInterface.getSimpleName(), rootBeanDefinition);
        });
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        this.scannedClasses.forEach((Class<?> scannedClass) -> registerSingleton(beanFactory, scannedClass));
    }

    /**
     * <p>
     * 根据扫描到的类进行注册
     * </p>
     *
     * @param beanFactory beanFactory
     * @param scannedClass 扫描到的类
     * @author netollie
     * @since 1.0.0
     */
    protected abstract void registerSingleton(ConfigurableListableBeanFactory beanFactory, Class<?> scannedClass);

    /**
     * <p>
     * 扫描出需要的类型
     * </p>
     *
     * @return 扫描到的类
     * @author netollie
     * @since 1.0.0
     */
    protected abstract Set<Class<?>> scanClass();
}
