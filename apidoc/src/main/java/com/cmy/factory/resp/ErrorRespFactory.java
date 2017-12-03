package com.cmy.factory.resp;

import com.cmy.apidoc.generator.annotations.ApiErrorFactoryMethod;

/**
 * <p>@author chenmingyi
 * <p>@version 1.0
 * <p>date: 2017/10/4
 */
public class ErrorRespFactory {

    @ApiErrorFactoryMethod(desc = "参数错误")
    public static <T> RespWrap<T> paramError(T t) {
        return new RespWrap<>(400, "参数错误", t);
    }

    @ApiErrorFactoryMethod(desc = "权限不足")
    public static <T> RespWrap<T> unauthorized() {
        return new RespWrap<>(403, "权限不足", null);
    }
}
