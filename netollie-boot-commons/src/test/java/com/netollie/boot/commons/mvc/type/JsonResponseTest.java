package com.netollie.boot.commons.mvc.type;

import org.junit.Assert;
import org.junit.Test;

/**
 * <p>
 * MVC Json 响应对象单元测试
 * </p>
 *
 * @author netollie
 * @date 2021/11/01
 */
public class JsonResponseTest {
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
        JsonResponse<String> success = JsonResponse.ok("123");
        Assert.assertEquals(0, success.getCode().intValue());
        Assert.assertEquals("success", success.getMsg());
        Assert.assertEquals("123", success.getData());

        JsonResponse<String> fail = JsonResponse.fail(100, "data not found");
        Assert.assertEquals(100, fail.getCode().intValue());
        Assert.assertEquals("data not found", fail.getMsg());
    }
}
