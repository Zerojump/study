package com.cmy.apidoc;

/**
 * Created by YSZ on 2015/11/11.
 */
public class UserController {

    /**
     * @api {get} /admin/user/getUsers getUsers
     * @apiName getUsers
     * @apiGroup Admin/User.
     * @apiDescription 获取用户管理列表信息.
     * @apiVersion 1.0.0-SNAPSHOT
     * @apiSampleRequest /admin/user/getUsers
     * @apiSuccess {Object} users 用户列表信息.
     * @apiPermission fuge dsp advertiser agency
     * @apiSuccessExample Success-Response:
     * HTTP/1.1 200 OK
     * {
     * "users":true
     * }
     */

    /**
     * @api {post} /admin/user/add add
     * @apiName add
     * @apiGroup Admin/User.
     * @apiDescription 保存用户及其对应的推广计划信息.
     * @apiVersion 1.0.0-SNAPSHOT
     * @apiSampleRequest /admin/user/add
     * @apiParam (user) {String} login_name 登陆邮箱
     * @apiParam (user) {String} username 姓名
     * @apiParam (user) {String} phone 电话
     * @apiParam (user) {long} portrait_id 头像的id
     * @apiParam {String} advertisers 广告主信息，jsonarray格式，如[{"agency_id":1,"advertiser_id":[1,2,3,4]}]
     * @apiParam (user_info) {int} company_type 公司类型，当选择Amnet时，是Amnet对应的type；当选择代理商，是代理商对应的type；当选择客户是，是客户对应的type
     * @apiParam (user_info) {long} company_id 公司id，当选择Amnet时，是Amnet对应的id；当选择代理商，是具体某个代理商对应的id；当选择客户时，是客户对应的id
     * @apiParam (user_role) {long} role_id 角色id，角色下拉菜单中选择Amnet后跟出的Product，Leader等信息，这些选项的id；当选择代理商或客户时，此处不需要传值
     * @apiParam {int} tv_dashboard 是否可以查看黑底Dashboard，0不可以，1可以
     * @apiSuccess {Object} accountID 账户id,-100添加失败，-99邮箱已存在,-98之前存在过该邮箱用户，但是删除了，现已恢复.
     * @apiPermission fuge dsp advertiser agency
     * @apiSuccessExample Success-Response:
     * HTTP/1.1 200 OK
     * {
     * "accountID":1
     * }
     */

    /**
     * @api {get} /admin/user/getRolesAndAdvertiserByCompany getRolesAndAdvertiserByCompany
     * @apiName getRolesAndAdvertiserByCompany
     * @apiGroup Admin/User.
     * @apiDescription 获取公司对应的角色信息.
     * @apiVersion 1.0.0-SNAPSHOT
     * @apiSampleRequest /admin/user/getRolesAndAdvertiserByCompany
     * @apiSuccess {Object} display 是否显示角色信息,true显示，false不显示.
     * @apiSuccess {Object} list 当显示角色信息时才有，包括td的角色信息和对应的agency信息.
     * @apiPermission fuge dsp advertiser agency
     * @apiSuccessExample Success-Response:
     * HTTP/1.1 200 OK
     * {
     * "roles":{
     * "display":true,
     * "list":[{.....}, {.....}]
     * }
     * }
     */

    /**
     * @api {post} /admin/user/updateForAdmin updateForAdmin
     * @apiName updateForAdmin
     * @apiGroup Admin/User.
     * @apiDescription 更新用户及其对应的推广计划信息，适用于管理员账户更新普通用户.
     * @apiVersion 1.0.0-SNAPSHOT
     * @apiSampleRequest /admin/user/updateForAdmin
     * @apiParam (user) {long} account_id 账户id
     * @apiParam (user) {String} username 姓名
     * @apiParam (user) {String} phone 电话
     * @apiParam (user) {long} portrait_id 头像的id
     * @apiParam {String} advertisers 广告主信息，jsonarray格式，如[{"agencyID":1,"advertiserID":[1,2,3,4]}]
     * @apiParam {int} tv_dashboard 是否可以查看黑底Dashboard，0不可以，1可以
     * @apiParam (user_info) {int} company_type 公司类型，当选择Amnet时，是Amnet对应的type；当选择代理商，是代理商对应的type；当选择客户是，是客户对应的type
     * @apiParam (user_info) {long} company_id 公司id，当选择Amnet时，是Amnet对应的id；当选择代理商，是具体某个代理商对应的id；当选择客户时，是客户对应的id
     * @apiParam (user_role) {long} role_id 角色id，角色下拉菜单中选择Amnet后跟出的Product，Leader等信息，这些选项的id；当选择代理商或客户时，此处不需要传值
     * @apiSuccess {Object} success 执行结果.
     * @apiPermission tradingdesk
     * @apiSuccessExample Success-Response:
     * HTTP/1.1 200 OK
     * {
     * "success":true
     * }
     */

    /**
     * @api {get} /admin/user/updateForGeneral updateForGeneral
     * @apiName updateForAdmin
     * @apiGroup Admin/User.
     * @apiDescription 更新用户信息，适用于管理员账户和普通用户编辑自己的信息.
     * @apiVersion 1.0.0-SNAPSHOT
     * @apiSampleRequest /admin/user/updateForGeneral
     * @apiParam {String} username 姓名
     * @apiParam {String} password 登陆密码
     * @apiParam {String} phone 电话
     * @apiParam {long} portrait_id 头像的id
     * @apiSuccess {Object} success 执行结果.
     * @apiPermission tradingdesk
     * @apiSuccessExample Success-Response:
     * HTTP/1.1 200 OK
     * {
     * "success":true
     * }
     */

    /**
     * @api {get} /admin/user/delete delete
     * @apiName delete
     * @apiGroup Admin/User.
     * @apiDescription 删除用户.
     * @apiVersion 1.0.0-SNAPSHOT
     * @apiSampleRequest /admin/user/delete
     * @apiParam {long} account_id 账户id
     * @apiSuccess {Object} success 执行结果.
     * @apiPermission tradingdesk
     * @apiSuccessExample Success-Response:
     * HTTP/1.1 200 OK
     * {
     * "success":true
     * }
     */

    /**
     * @api {get} /admin/user/getUser getUser
     * @apiName getUser
     * @apiGroup Admin/User.
     * @apiDescription 获取单个用户.
     * @apiVersion 1.0.0-SNAPSHOT
     * @apiSampleRequest /admin/user/getUser
     * @apiParam {long} account_id 账户id
     * @apiSuccess {Object} user 用户信息.
     * @apiSuccess {Object} user_info 用户的公司信息.
     * @apiSuccess {Object} user_role 用户的角色信息.
     * @apiSuccess {Object} user_advertisers 授权用户的广告主信息.
     * @apiSuccess {Object} role 所有的角色信息和关联的广告主信息.
     * @apiPermission tradingdesk
     * @apiSuccessExample Success-Response:
     * HTTP/1.1 200 OK
     * {
     * "user":{"username":"fdsafd"},
     * "user_info":{"company_id":1},
     * "user_role":{"role_id":1},
     * "user_advertisers":[{"advertiser_id":1}],
     * "role":{....}
     * }
     */

    /**
     * @api {get} /admin/user/updateUserPortrait updateUserPortrait
     * @apiName updateUserPortrait
     * @apiGroup Admin/User.
     * @apiDescription 更新用户头像信息.
     * @apiVersion 1.0.0-SNAPSHOT
     * @apiSampleRequest /admin/user/updateUserPortrait
     * @apiParam {long} portrait_id 头像id
     * @apiSuccess {Object} success 更新结果，true成功，false失败.
     * @apiPermission tradingdesk
     * @apiSuccessExample Success-Response:
     * HTTP/1.1 200 OK
     * {
     * "success":true
     * }
     */


    /**
     * @api {get} /admin/user/sendForgetPasswordEmail sendForgetPasswordEmail
     * @apiName sendForgetPasswordEmail
     * @apiGroup Admin/User.
     * @apiDescription 发送忘记密码邮件.
     * @apiVersion 1.0.0-SNAPSHOT
     * @apiSampleRequest /admin/user/sendForgetPasswordEmail
     * @apiParam {String} user_email 登陆邮箱
     * @apiSuccess {Object} code 执行结果.
     * @apiPermission tradingdesk
     * @apiSuccessExample Success-Response:
     * HTTP/1.1 200 OK
     * {
     *  result : success
     * }
     */

    /**
     * @api {get} /admin/user/changePassword changePassword
     * @apiName changePassword
     * @apiGroup Admin/User.
     * @apiDescription 首次登陆修改密码.
     * @apiVersion 1.0.0-SNAPSHOT
     * @apiSampleRequest /admin/user/changePassword
     * @apiParam {String} user_email 登陆邮箱
     * @apiParam {String} password 登陆密码
     * @apiParam {int} email_type 邮件类型
     * @apiParam {String} md5_code 加密的字符串标识
     * @apiSuccess {Object} code 执行结果，1成功，0用户不存在，-1更新密码链接已失效，-2更新密码密匙不符, -100操作失败.
     * @apiPermission tradingdesk
     * @apiSuccessExample Success-Response:
     * HTTP/1.1 200 OK
     * {
     * "code":1
     * }
     */

    /**
     * @api {post} /admin/user/getAdvertiserCampaignTree getAdvertiserCampaignTree
     * @apiName getAdvertiserCampaignTree
     * @apiGroup Admin/User.
     * @apiDescription advertiser与campaign关系树
     * @apiVersion 1.0.0-SNAPSHOT
     * @apiSampleRequest /admin/user/getAdvertiserCampaignTree
     * @apiSuccess {Object} advertisers advertiser列表.
     * @apiPermission fuge dsp tradingDesk agency
     * @apiSuccessExample Success-Response:
     * HTTP/1.1 200 OK
     * [{"id":8,"name":"test","campaigns":[{"id":54,"name":"campaignName"},{"id":56,"name":"campaignName"}]},{"id":16,"name":"DSP导入","campaigns":[{"id":52,"name":"晶赞Campaign"}]}]
     */

    /**
     * @api {post} /admin/user/addAgencySpendingKpi addAgencySpendingKpi
     * @apiName addAgencySpendingKpi
     * @apiGroup Admin/User.
     * @apiDescription 保存代理商的花费目标设置.
     * @apiVersion 1.0.0-SNAPSHOT
     * @apiSampleRequest /admin/user/addAgencySpendingKpi
     * @apiParam (agencySpendingKpi) {String} month 时间，格式yyyyMM
     * @apiParam (agencySpendingKpi) {long} agency_id 办公室的id
     * @apiParam (agencySpendingKpi) {double} target_value 目标值
     * @apiSuccess {Object} result 执行结果.
     * @apiPermission fuge dsp tradingDesk agency
     * @apiSuccessExample Success-Response:
     * HTTP/1.1 200 OK
     * {
     *     result : true
     * }
     */


    /**
     * @api {post} /admin/user/getAgencySpendingKpi getAgencySpendingKpi
     * @apiName getAgencySpendingKpi
     * @apiGroup Admin/User.
     * @apiDescription 获取代理商的花费目标设置.
     * @apiVersion 1.0.0-SNAPSHOT
     * @apiSampleRequest /admin/user/getAgencySpendingKpi
     * @apiParam {long} agency_id 代理商id
     * @apiParam {int} start_month 开始月份
     * @apiParam {int} end_month 结束月份
     * @apiSuccess {Object} result 执行结果.
     * @apiPermission fuge dsp tradingDesk agency
     * @apiSuccessExample Success-Response:
     * HTTP/1.1 200 OK
     * {
     *     result : {}
     * }
     */

    /**
     * @api {get} /admin/user/getLoginUser getLoginUser
     * @apiName getLoginUser
     * @apiGroup Admin.
     * @apiDescription 获取登录后的用户信息，仅用于前端api测试.
     * @apiVersion 1.0.0-SNAPSHOT
     * @apiSampleRequest /admin/user/getLoginUser
     * @apiSuccess {Object} user 登录后的用户对象.
     * @apiPermission fuge dsp tradingDesk agency
     * @apiSuccessExample Success-Response:
     * HTTP/1.1 200 OK
     * {
     *     user : {...}
     * }
     */
}
