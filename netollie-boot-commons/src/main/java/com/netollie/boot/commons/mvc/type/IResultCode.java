package com.netollie.boot.commons.mvc.type;

/**
 * <p>
 * 错误码接口
 * </p>
 *
 * @author netollie
 * @date 2021/11/01
 * @since 1.0.0
 */
public interface IResultCode {
    /**
     * <p>
     * 获取错误码
     * </p>
     *
     * @return 错误码
     * @author netollie
     * @date 2021/11/01
     */
    Integer getCode();

    /**
     * <p>
     * 获取错误信息
     * </p>
     *
     * @return 错误信息
     * @author netollie
     * @date 2021/11/01
     */
    String getMsg();
}
