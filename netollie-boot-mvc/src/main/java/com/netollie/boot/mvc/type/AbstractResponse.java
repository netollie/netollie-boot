package com.netollie.boot.mvc.type;

/**
 * <p>
 * MVC抽象响应对象
 * </p>
 *
 * @param <T> 响应对象类型
 * @author netollie
 * @date 2021/11/01
 */
public abstract class AbstractResponse<T> implements IResponse<T> {
    /**
     * <p>
     * 构造方法
     * </p>
     *
     * @param code 错误码
     * @param message 错误信息
     * @author netollie
     * @date 2021/11/01
     */
    public AbstractResponse(Integer code, String message) {
        setCode(code);
        setMsg(message);
    }

    /**
     * <p>
     * 构造方法
     * </p>
     *
     * @param code 错误码
     * @param message 错误信息
     * @param data 传输数据
     * @author netollie
     * @date 2021/11/01
     */
    public AbstractResponse(Integer code, String message, T data) {
        setCode(code);
        setMsg(message);
        setData(data);
    }
}
