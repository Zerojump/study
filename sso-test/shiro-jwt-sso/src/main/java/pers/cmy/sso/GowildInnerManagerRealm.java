package pers.cmy.sso;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * <p>@author chenmingyi
 * <p>@version 1.0
 * <p>date: 2017/8/28
 */
public class GowildInnerManagerRealm extends AuthorizingRealm {

    private static final Map<String,String> userAndPasswordMap;
    private static final Map<String, Set<String>> userAndRoleMap;
    private static final Map<String, Set<String>> roleAndPermissionMap;
    static {
        userAndPasswordMap = new HashMap<>(2);
        userAndPasswordMap.put("ruby", "123456");
        userAndPasswordMap.put("will", "123456");

        userAndRoleMap = new HashMap<>();
        Set<String> roleSet1 = new HashSet<>();
        roleSet1.add("read");
        roleSet1.add("write");
        userAndRoleMap.put("ruby", roleSet1);

        Set<String> roleSet2 = new HashSet<>();
        roleSet2.add("read");
        userAndRoleMap.put("will", roleSet2);

        roleAndPermissionMap = new HashMap<>();
        Set<String> permissionSetRead = new HashSet<>();
        permissionSetRead.add("count");
        permissionSetRead.add("list");
        roleAndPermissionMap.put("read", permissionSetRead);

        Set<String> permissionSetWrite = new HashSet<>();
        permissionSetWrite.add("add");
        permissionSetWrite.add("edit");
        roleAndPermissionMap.put("write", permissionSetWrite);
    }

    @Override
    public String getName() {
        return "GowildInnerManagerRealm";
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();

        String username = (String) principalCollection.getPrimaryPrincipal();
        Set<String> roleSet = userAndRoleMap.get(username);
        authorizationInfo.setRoles(roleSet);
        for (String role : roleSet) {
            authorizationInfo.addStringPermissions(roleAndPermissionMap.get(role));
        }
        return authorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        //获取用户的输入的账号.
        String username = token.getUsername();
        //通过username从数据库中查找 User对象，如果找到，没找到.
        //实际项目中，这里可以根据实际情况做缓存，如果不做，Shiro自己也是有时间间隔机制，2分钟内不会重复执行该方法
        String password = userAndPasswordMap.get(username);
        if (password == null) {
            throw new UnknownAccountException();
        }

        char[] tokenPassword = token.getPassword();
        if (!new String(tokenPassword).equals(password)) {
            throw new IncorrectCredentialsException();
        }

        return new SimpleAuthenticationInfo(username, password, getName());
    }

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof UsernamePasswordToken;
    }
}
