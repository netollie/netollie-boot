package com.netollie.boot.commons.mvc.type;

/**
 * <p>
 * MVC响应接口
 * </p>
 *
 * @param <T> 响应数据的类型
 * @author netollie
 * @date 2021/11/01
 */
public interface IResponse <T> {
    /**
     * <p>
     * 设置错误码
     * </p>
     *
     * @param code 错误码
     * @author netollie
     * @date 2021/11/01
     */
    void setCode(Integer code);

    /**
     * <p>
     * 设置错误信息
     * </p>
     *
     * @param msg 错误信息
     * @author netollie
     * @date 2021/11/01
     */
    void setMsg(String msg);

    /**
     * <p>
     * 设置响应数据
     * </p>
     *
     * @param data 响应数据
     * @author netollie
     * @date 2021/11/01
     */
    void setData(T data);

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

    /**
     * <p>
     * 获取响应数据
     * </p>
     *
     * @return 响应数据
     * @author netollie
     * @date 2021/11/01
     */
    T getData();
}
