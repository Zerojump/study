package com.cmy.permission;

import com.alibaba.druid.util.StringUtils;
import org.apache.shiro.authz.Permission;

/**
 * 规则
 * +资源字符串+权限位+实例ID
 * <p>
 * 以+开头 中间通过+分割
 * <p>
 * 权限：
 * 0 表示所有权限
 * 1 新增 0001
 * 2 修改 0010
 * 4 删除 0100
 * 8 查看 1000
 * <p>
 * 如 +user+10 表示对资源user拥有修改/查看权限
 *
 * @author Lankidd
 * @date 2017/5/14
 * <p>
 * To change this templates use File | Settings | File and Code Templates | Includes .
 */
public class BitPermission implements Permission {
    private String resourceIdentify;
    private int permissionBit;
    private String instanceId;

    public BitPermission(String permissionString) {
        String[] array = permissionString.split("\\+");

        if (array.length > 1) {
            resourceIdentify = array[1];
        }
        if (StringUtils.isEmpty(resourceIdentify)) {
            resourceIdentify = "*";
        }
        if (array.length > 2) {
            permissionBit = Integer.valueOf(array[2]);
        }
        if (array.length > 3) {
            instanceId = array[3];
        }
        if (StringUtils.isEmpty(instanceId)) {
            instanceId = "*";
        }
    }

    @Override
    public boolean implies(Permission permission) {
        if (!(permission instanceof BitPermission)) {
            return false;
        }
        BitPermission bitPermission = (BitPermission) permission;
        //对资源有无权限判断
        if (!("*".equals(this.resourceIdentify) || this.resourceIdentify.equals(bitPermission.resourceIdentify))) {
            return false;
        }
        //对操作有无权限判断
        if (!((this.permissionBit == 0) || (this.permissionBit & bitPermission.permissionBit) != 0)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "BitPermission{" +
                "resourceIdentify='" + resourceIdentify + '\'' +
                ", permissionBit=" + permissionBit +
                ", instanceId='" + instanceId + '\'' +
                '}';
    }
}
