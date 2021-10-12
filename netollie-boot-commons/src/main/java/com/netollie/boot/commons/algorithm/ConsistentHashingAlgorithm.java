package com.netollie.boot.commons.algorithm;

import java.util.SortedMap;
import java.util.TreeMap;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.ToIntFunction;

/**
 * <p>
 * 一致性Hash算法
 * </p>
 *
 * @param <T> T 节点对象类型
 * @author netoliie
 * @date 2021/10/25
 */
public class ConsistentHashingAlgorithm <T> {
    /*
     * 自定义哈希算法, 默认使用FNV1_32算法
     */
    private final ToIntFunction<String> hashingAlgorithm;

    /*
     * 节点名称生成器
     */
    private final Function<T, String> nodeNameGenerator;

    /*
     * 虚拟节点数量, 避免出现节点数量较少而产生的分配不均的情况
     */
    private final int virtualNodeCount;

    /*
     * 一致性Hash环
     */
    private final SortedMap<Integer, T> circle = new TreeMap<>();

    /**
     * <p>
     * 构造方法
     * </p>
     *
     * @param nodeNameGenerator 节点名称生成器
     * @param virtualNodeCount 虚拟节点数量
     * @author netollie
     * @date 2021/10/26
     */
    public ConsistentHashingAlgorithm(Function<T, String> nodeNameGenerator, int virtualNodeCount) {
        this(new DefaultHashAlgorithm(), nodeNameGenerator, virtualNodeCount);
    }

    /**
     * <p>
     * 构造方法
     * </p>
     *
     * @param hashingAlgorithm 自定义哈希算法
     * @param nodeNameGenerator 节点名称生成器
     * @param virtualNodeCount 虚拟节点数量
     * @author netollie
     * @date 2021/10/26
     */
    public ConsistentHashingAlgorithm(
        ToIntFunction<String> hashingAlgorithm, Function<T, String> nodeNameGenerator, int virtualNodeCount) {
        this.hashingAlgorithm = hashingAlgorithm;
        this.nodeNameGenerator = nodeNameGenerator;
        this.virtualNodeCount = virtualNodeCount;
    }

    /**
     * <p>
     * 获取给定key最近的节点
     * </p>
     *
     * @param key 键名
     * @return 节点对象
     * @author netOllie
     * @date 2021/10/26
     */
    public T getNode(String key) {
        int hash = this.hashingAlgorithm.applyAsInt(key);
        SortedMap<Integer, T> subCircle = circle.tailMap(hash);
        if (subCircle.isEmpty()) {
            return circle.get(circle.firstKey());
        } else {
            return circle.get(subCircle.firstKey());
        }
    }

    /**
     * <p>
     * 增加节点
     * </p>
     *
     * @param node 节点对象
     * @author netollie
     * @date 2021/10/25
     */
    public void addNode(T node) {
        consumeNode(node, circle::put);
    }

    /**
     * <p>
     * 移除节点
     * </p>
     *
     * @param node 节点对象
     * @author netollie
     * @date 2021/10/25
     */
    public void removeNode(T node) {
        consumeNode(node, circle::remove);
    }

    /**
     * <p>
     * 消费节点
     * </p>
     *
     * @param node 节点对象
     * @param nodeConsumer 节点消费者
     * @author netollie
     * @date 2021/10/25
     */
    private void consumeNode(T node, BiConsumer<Integer, T> nodeConsumer) {
        // 生成虚拟节点，避免出现分布不均的情况
        for (int i = 0; i < virtualNodeCount; i++) {
            String virtualNodeName = String.format("%s#%d", nodeNameGenerator.apply(node), i);
            Integer virtualNodeHashCode = hashingAlgorithm.applyAsInt(virtualNodeName);
            nodeConsumer.accept(virtualNodeHashCode, node);
        }
    }

    /**
     * <p>
     * 默认哈希算法(FNV1_32)
     * </p>
     *
     * @author netollie
     * @date 2021/10/26
     */
    private static class DefaultHashAlgorithm implements ToIntFunction<String> {
        @Override
        public int applyAsInt(String s) {
            final int p = 16777619;
            int hash = (int) 2166136261L;
            for (int i = 0; i < s.length(); i++)
                hash = (hash ^ s.charAt(i)) * p;
            hash += hash << 13;
            hash ^= hash >> 7;
            hash += hash << 3;
            hash ^= hash >> 17;
            hash += hash << 5;
            if (hash < 0) {
                hash = Math.abs(hash);
            }
            return hash;
        }
    }
}
