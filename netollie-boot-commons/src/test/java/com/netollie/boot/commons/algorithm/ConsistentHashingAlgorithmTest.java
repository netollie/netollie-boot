package com.netollie.boot.commons.algorithm;

import org.junit.Assert;
import org.junit.Test;

/**
 * <p>
 * 一致性哈希算法单元测试
 * </p>
 *
 * @author netollie
 * @date 2021/10/26
 */
public class ConsistentHashingAlgorithmTest {
    /**
     * <p>
     * 执行单元测试
     * </p>
     *
     * @author netollie
     * @date 2021/10/26
     */
    @Test
    public void doTest() {
        ConsistentHashingAlgorithm<String> algorithm = new ConsistentHashingAlgorithm<>(s -> s, 3);
        algorithm.addNode("192.168.1.1");
        algorithm.addNode("192.168.1.2");
        algorithm.addNode("192.168.1.3");
        algorithm.addNode("192.168.1.4");
        Assert.assertEquals("192.168.1.1", algorithm.getNode("192.168.1.21"));
        Assert.assertEquals("192.168.1.4", algorithm.getNode("foo"));
        Assert.assertEquals("192.168.1.1", algorithm.getNode("bar"));
        algorithm.removeNode("192.168.1.4");
        Assert.assertEquals("192.168.1.2", algorithm.getNode("foo"));
        Assert.assertEquals("192.168.1.1", algorithm.getNode("bar"));
    }
}
