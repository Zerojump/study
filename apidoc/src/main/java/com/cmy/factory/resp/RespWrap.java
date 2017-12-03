package com.cmy.factory.resp;

import com.cmy.apidoc.generator.annotations.ApiParam;

/**
 * <p>@author chenmingyi
 * <p>@version 1.0
 * <p>date: 2017/10/4
 */
public class RespWrap<T> {
    @ApiParam(desc = "返回码")
    private int code;

    @ApiParam(desc = "返回描述")
    private String desc;

    @ApiParam(desc = "返回数据")
    private T data;

    public RespWrap() {
        this(200, "请求成功", null);
    }

    public RespWrap(int code, String desc, T data) {
        this.code = code;
        this.desc = desc;
        this.data = data;
    }
}
