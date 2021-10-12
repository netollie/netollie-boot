package com.netollie.boot.commons.mvc.type;

import com.netollie.boot.commons.mvc.constants.enums.ResultCodeEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * MVC Json 响应
 * </p>
 *
 * @param <T> 响应数据类型
 * @author netollie
 * @date 2021/11/01
 */
@Getter
@Setter
@ApiModel(value = "Json响应对象", description = "包含错误码，错误信息，响应数据等数据")
public class JsonResponse<T> extends AbstractResponse<T> {
    /**
     * 错误码
     */
    @ApiModelProperty(value = "错误码", dataType = "int", example = "0")
    private Integer code;

    /**
     * 错误信息
     */
    @ApiModelProperty(value = "错误信息", dataType = "string", example = "success")
    private String msg;

    /**
     * 响应数据
     */
    @ApiModelProperty(value = "返回数据", dataType = "object")
    private T data;

    /**
     * <p>
     * 构造方法
     * </p>
     *
     * @param code 错误码
     * @param msg 错误信息
     * @author netollie
     * @date 2021/11/01
     */
    private JsonResponse(Integer code, String msg) {
        super(code, msg);
    }

    /**
     * <p>
     * 构造方法
     * </p>
     *
     * @param code 错误码
     * @param msg 错误信息
     * @param data 传输数据
     * @author netollie
     * @date 2021/11/01
     */
    private JsonResponse(Integer code, String msg, T data) {
        super(code, msg, data);
    }

    /**
     * <p>
     * 失败响应
     * </p>
     *
     * @param <T> 传输数据类型
     * @param code 错误码
     * @param message 错误信息
     * @return 失败响应
     * @author netollie
     * @date 2021/11/01
     */
    public static <T> JsonResponse<T> fail(Integer code, String message) {
        return new JsonResponse<>(code, message);
    }

    /**
     * <p>
     * 成功响应
     * </p>
     * @param <T> 传输对象类型
     * @param data 传输对象
     * @return 成功响应
     * @author netollie
     * @date 2021/11/01
     */
    public static <T> JsonResponse<T> ok(T data) {
        return new JsonResponse<>(ResultCodeEnum.SUCCESS.getCode(), ResultCodeEnum.SUCCESS.getMsg(), data);
    }
}
