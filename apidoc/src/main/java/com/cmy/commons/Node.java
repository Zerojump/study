package com.cmy.commons;

import com.cmy.apidoc.generator.annotations.ApiParam;

import java.util.Date;

/**
 * <p>@author chenmingyi
 * <p>@version 1.0
 * <p>date: 2017/10/4
 */
public class Node {
    @ApiParam(desc = "节点id")
    private Long id;

    @ApiParam(desc = "节点名称")
    private String name;

    @ApiParam(desc = "节点名称")
    private Date date;
}
