package com.netollie.boot.beans.factory.support;

import lombok.SneakyThrows;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.type.ClassMetadata;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.util.ClassUtils;
import org.springframework.util.StringUtils;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Predicate;

/**
 * <p>
 * 类路径扫描器
 * </p>
 *
 * @author netollie
 * @date 2021/11/01
 */
public final class ClassPathScanner {
    /*
     * 资源文件解析类
     */
    private static final ResourcePatternResolver RESOURCE_PATTERN_RESOLVER = new PathMatchingResourcePatternResolver();

    /*
     * 数据资源读取工厂
     */
    private static final MetadataReaderFactory METADATA_READER_FACTORY = new CachingMetadataReaderFactory();

    /**
     * <p>
     * 私有构造方法
     * </p>
     *
     * @author netollie
     * @date 2021/11/01
     */
    private ClassPathScanner() {
        // do nothing
    }

    /**
     * <p>
     * 根据给定的包扫描需要的类型
     * </p>
     *
     * @param packagePatterns packagePatterns,存在多个时用英文逗号隔开,"com.example.a.**,com.example.b"
     * @param classFilter 类过滤器
     * @return 扫描到的类
     * @author netollie
     * @since 1.0.0
     */
    @SneakyThrows
    public static Set<Class<?>> scan(String packagePatterns, Predicate<Class<?>> classFilter) {
        Set<Class<?>> classes = new HashSet<>();
        String[] packagePatternArray = StringUtils.tokenizeToStringArray(packagePatterns, ",");
        for (String packagePattern : packagePatternArray) {
            String resourcePath = ClassUtils.convertClassNameToResourcePath(packagePattern);
            String locationPattern = String.format("classpath*:%s/**/*.class", resourcePath);
            Resource[] resources = RESOURCE_PATTERN_RESOLVER.getResources(locationPattern);
            for (Resource resource : resources) {
                ClassMetadata metadata = METADATA_READER_FACTORY.getMetadataReader(resource).getClassMetadata();
                Class<?> clazz = Class.forName(metadata.getClassName());
                if (classFilter.test(clazz)) {
                    classes.add(clazz);
                }
            }
        }
        return classes;
    }
}
