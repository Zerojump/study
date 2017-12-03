package com.cmy.doc.doc02;

public class ApiDoc {

/**
 * @apiDefine paramError
 * @apiError (Error-Response) {RespWrap} paramError 参数错误
 * @apiErrorExample {json} paramError
 * HTTP 200
 *{
"code": 400,
"desc": "参数错误"
}
 */

/**
 * @apiDefine unauthorized
 * @apiError (Error-Response) {RespWrap} unauthorized 权限不足
 * @apiErrorExample {json} unauthorized
 * HTTP 200
 *{
"code": 403,
"desc": "权限不足"
}
 */

/**
 * @api {PUT} /user/role 修改
 * @apiVersion 0.0.1
 * @apiName com.cmy.controller.UserController.get
 * @apiGroup user
 * @apiDescription 修改
 *
 * @apiParam {Integer {0-}=1,2} roleId=1 角色id
 *
 * @apiParam ({json}Node) {Long} id 节点id
 * @apiParam ({json}Node) {String} name 节点名称
 *
 * @apiParam ({json}Date) {Date} date 节点名称
 *
 * @apiParamExample {json} Request-Example:
 * {
"id": 0,
"name": "",
"date": 1507129247822
}
 *
 * @apiSuccess (Integer) {Integer[]} Integer Integer
 *
 * @apiSuccessExample {json} Success-Response:
 * HTTP 200 OK
 * [
0
]
 *
 * @apiUse unauthorized
 * @apiUse paramError
 */

/**
 * @api {POST} /user 文件上传
 * @apiVersion 0.0.1
 * @apiName com.cmy.controller.UserController.uploadFile
 * @apiGroup user
 * @apiDescription 文件上传
 *
 * @apiParam {MultipartFile} file 上传的文件
 *
 * @apiSuccess (RespWrap) {int} code 返回码
 * @apiSuccess (RespWrap) {String} desc 返回描述
 * @apiSuccess (RespWrap) {Object} data 返回数据
 *
 * @apiSuccess (String) {String[]} String String
 *
 * @apiSuccessExample {json} Success-Response:
 * HTTP 200 OK
 * {
"code": 200,
"desc": "请求成功",
"data": [
""
]
}
 */
}