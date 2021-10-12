package com.netollie.boot.commons.mvc.constants.enums;

import org.junit.Assert;
import org.junit.Test;

/**
 * <p>
 * 响应码枚举单元测试
 * </p>
 *
 * @author netollie
 * @date 2021/11/01
 */
public class ResultCodeEnumTest {
    /**
     * <p>
     * 执行单元测试
     * </p>
     *
     * @author netollie
     * @date 2021/11/01
     */
    @Test
    public void doTest() {
        ResultCodeEnum resultCode = ResultCodeEnum.SUCCESS;
        Assert.assertEquals(0, resultCode.getCode().intValue());
        Assert.assertEquals("success", resultCode.getMsg());
    }
}