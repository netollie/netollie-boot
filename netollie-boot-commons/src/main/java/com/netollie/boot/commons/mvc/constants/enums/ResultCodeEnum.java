package com.netollie.boot.commons.mvc.constants.enums;

import com.netollie.boot.commons.mvc.type.IResultCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * <p>
 * 错误码枚举
 * </p>
 *
 * @author netollie
 * @date 2021/11//01
 */
@Getter
@AllArgsConstructor
public enum ResultCodeEnum implements IResultCode {
    // 成功
    SUCCESS(0, "success");

    /**
     * 错误码
     */
    private Integer code;

    /**
     * 错误信息
     */
    private String msg;
}
