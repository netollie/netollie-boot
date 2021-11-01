package com.netollie.boot.starter.demo.controller;

import com.netollie.boot.mvc.type.JsonResponse;
import com.netollie.boot.starter.demo.engine.js.StringEngine;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * demo for netollie-boot-starter-js
 *
 * @author netollie
 * @since 1.0.0
 */
@RestController
public class JsStarterDemo {
    @Resource
    private StringEngine stringEngine;

    /**
     * js escape demo
     *
     * @return escape result
     * @author netollie
     * @since 1.0.0
     */
    @GetMapping("/js")
    public JsonResponse<String> js() {
        String value = "%";
        String data = String.format("escape [%s] then get [%s]", value, stringEngine.escape(value));
        return JsonResponse.ok(data);
    }
}
