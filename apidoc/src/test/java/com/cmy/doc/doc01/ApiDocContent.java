package com.cmy.doc.doc01;

public class ApiDocContent {

/**
 * @apiDefine ResponseWrapgetParamErrorSpringMvcAnnotationClass
 * @apiError parameterError please check your parameter is right
 * @apiErrorExample {json} Error-Response:
 * HTTP 401
 *{
"code": 303,
"desc": null,
"data": null
}
 */

/**
 * @apiDefine ResponseWrapgetAccountErrorSpringMvcAnnotationClass
 * @apiError getAccountError
 * @apiErrorExample {json} Error-Response:
 * HTTP 200
 *{
"code": 302,
"desc": null,
"data": null
}
 */

/**
 * @api {GET} /brand/read/{id} read list
 * @apiVersion 0.0.1
 * @apiName read list
 * @apiGroup brand
 *
 * @apiParam (mine) {Integer {0-}=1,2} [id=1] just send id
 *
 * @apiParam (json-PermissionVo) {Integer} id
 * @apiParam (json-PermissionVo) {String {..5}} code
 * @apiParam (json-PermissionVo) {String} name
 *
 * @apiParamExample {json} Request-Example:
 * {
"id": 0,
"code": "",
"name": ""
}
 *
 * @apiSuccess (Integer) {Integer[]} id 不知道什么id
 *
 * @apiSuccessExample {json} Success-Response:
 * HTTP 200 OK
 * [
0
]
 *
 * @apiUse ResponseWrapgetParamErrorSpringMvcAnnotationClass
 * @apiUse ResponseWrapgetAccountErrorSpringMvcAnnotationClass
 */

/**
 * @api {GET} /brand/generic generic
 * @apiVersion 0.0.1
 * @apiName generic
 * @apiGroup brand
 *
 * @apiSuccess (A) {Object} b
 * @apiSuccess (A) {Object} c
 * @apiSuccess (A) {Object} d
 *
 * @apiSuccess (C) {Object} d
 * @apiSuccess (C) {Object} e
 *
 * @apiSuccess (EmployeeVo) {Integer} id
 * @apiSuccess (EmployeeVo) {String} name
 *
 * @apiSuccess (PermissionVo) {Integer} id
 * @apiSuccess (PermissionVo) {String} code
 * @apiSuccess (PermissionVo) {String} name
 *
 * @apiSuccess (B) {Byte} id
 * @apiSuccess (B) {String} name
 * @apiSuccess (B) {Object} object
 * @apiSuccess (B) {Integer[]} ints
 *
 * @apiSuccess (String) {String} String
 *
 * @apiSuccessExample {json} Success-Response:
 * HTTP 200 OK
 * {
"b": {},
"c": {
"d": {},
"e": {},
"employeeVo": {
"id": 0,
"name": "",
"permissionVoSet": [
{
"id": 0,
"code": "",
"name": ""
}
]
}
},
"d": {}
}
 *
 * @apiUse ResponseWrapgetParamErrorSpringMvcAnnotationClass
 * @apiUse ResponseWrapgetAccountErrorSpringMvcAnnotationClass
 */

/**
 * @apiDefine ResponseWrapgetPasswordErrorSpringMvcAnnotationClasspost
 * @apiError getPasswordError
 * @apiErrorExample {json} Error-Response:
 * HTTP 200
 *{
"code": 301,
"desc": null,
"data": null
}
 */

/**
 * @api {POST} /brand/create post
 * @apiVersion 0.0.1
 * @apiName post
 * @apiGroup brand
 *
 * @apiParam (json-Integer) {Integer[]} arg0 权限id数组
 *
 * @apiParamExample {json} Request-Example:
 * [
0
]
 *
 * @apiSuccess (ResponseWrap) {int} code
 * @apiSuccess (ResponseWrap) {String} desc
 * @apiSuccess (ResponseWrap) {Object} data
 *
 * @apiSuccess (PermissionVo) {Integer} id
 * @apiSuccess (PermissionVo) {String} code
 * @apiSuccess (PermissionVo) {String} name
 *
 * @apiSuccessExample {json} Success-Response:
 * HTTP 200 OK
 * {
"code": 0,
"desc": "",
"data": [
{
"id": 0,
"code": "",
"name": ""
}
]
}
 *
 * @apiUse ResponseWrapgetPasswordErrorSpringMvcAnnotationClasspost
 * @apiUse ResponseWrapgetParamErrorSpringMvcAnnotationClass
 * @apiUse ResponseWrapgetAccountErrorSpringMvcAnnotationClass
 */

/**
 * @api {PUT} /brand edit
 * @apiVersion 0.0.1
 * @apiName edit
 * @apiGroup brand
 *
 * @apiParam (json-Integer) {Integer[]} arg0 权限id数组
 *
 * @apiParamExample {json} Request-Example:
 * [
0
]
 *
 * @apiSuccess (ResponseWrap) {int} code
 * @apiSuccess (ResponseWrap) {String} desc
 * @apiSuccess (ResponseWrap) {Object} data
 *
 * @apiSuccess (PageInfo) {int} pageNum
 * @apiSuccess (PageInfo) {int} pageSize
 * @apiSuccess (PageInfo) {int} size
 * @apiSuccess (PageInfo) {String} orderBy
 * @apiSuccess (PageInfo) {int} startRow
 * @apiSuccess (PageInfo) {int} endRow
 * @apiSuccess (PageInfo) {long} total
 * @apiSuccess (PageInfo) {int} pages
 * @apiSuccess (PageInfo) {int} firstPage
 * @apiSuccess (PageInfo) {int} prePage
 * @apiSuccess (PageInfo) {int} nextPage
 * @apiSuccess (PageInfo) {int} lastPage
 * @apiSuccess (PageInfo) {boolean} isFirstPage
 * @apiSuccess (PageInfo) {boolean} isLastPage
 * @apiSuccess (PageInfo) {boolean} hasPreviousPage
 * @apiSuccess (PageInfo) {boolean} hasNextPage
 * @apiSuccess (PageInfo) {int} navigatePages
 * @apiSuccess (PageInfo) {int[]} navigatepageNums
 *
 * @apiSuccess (PermissionVo) {Integer} id
 * @apiSuccess (PermissionVo) {String} code
 * @apiSuccess (PermissionVo) {String} name
 *
 * @apiSuccessExample {json} Success-Response:
 * HTTP 200 OK
 * {
"code": 0,
"desc": "",
"data": {
"pageNum": 0,
"pageSize": 0,
"size": 0,
"orderBy": "",
"startRow": 0,
"endRow": 0,
"total": 0,
"pages": 0,
"list": [
{
"id": 0,
"code": "",
"name": ""
}
],
"firstPage": 0,
"prePage": 0,
"nextPage": 0,
"lastPage": 0,
"isFirstPage": false,
"isLastPage": false,
"hasPreviousPage": false,
"hasNextPage": false,
"navigatePages": 0,
"navigatepageNums": null
}
}
 *
 * @apiUse ResponseWrapgetParamErrorSpringMvcAnnotationClass
 * @apiUse ResponseWrapgetAccountErrorSpringMvcAnnotationClass
 */
}