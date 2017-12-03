package com.cmy.controller;

import com.cmy.apidoc.generator.annotations.ApiParam;
import com.cmy.apidoc.generator.annotations.ApiUse;
import com.cmy.commons.Node;
import com.cmy.factory.resp.RespWrap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>@author chenmingyi
 * <p>@version 1.0
 * <p>date: 2017/10/4
 */
@RestController
@RequestMapping("user")
public class UserController {

    @RequestMapping(name = "修改", value = "role", method = RequestMethod.PUT)
    @ApiUse({"unauthorized", "paramError"})
    public List<Integer> get(@ApiParam(name = "roleId", size = "0-", allowedValues = "1,2", defaultValue = "1", desc = "角色id") Integer roleId,
                             @RequestBody Node node) {
        return null;
    }

    @RequestMapping(name = "文件上传", value = "", method = RequestMethod.POST)
    public RespWrap<List<String>> uploadFile(@ApiParam(name = "file", desc = "上传的文件") MultipartFile file) {
        return null;
    }
}