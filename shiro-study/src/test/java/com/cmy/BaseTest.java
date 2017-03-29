package com.cmy;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Assert;
import org.apache.shiro.util.Factory;
import org.apache.shiro.util.ThreadContext;
import org.junit.After;

/**
 * @author chenmingyi
 * @version 1.0
 * @ DATE:3/15/2017
 */
public class BaseTest {

    protected Subject subject;

    protected UsernamePasswordToken token;

    @After
    public void tearDown() throws Exception {
        if (subject == null || token == null) {
            return;
        }

/*        try {
            //登录，即做身份校验
            subject.login(token);
        } catch (AuthenticationException e) {
            //无法通过校验处理
            e.printStackTrace();
        }*/

        PrincipalCollection principalCollection = subject.getPrincipals();

        Assert.isTrue(subject.isAuthenticated(), "验证不通过");
        //退出
        subject.logout();
        ThreadContext.unbindSubject();
    }

    protected Subject getSubject(String iniResourcePath) {
        //创建SecurityManager工厂
        Factory<SecurityManager> factory = new IniSecurityManagerFactory(iniResourcePath);
        //获取SecurityManager，并将SecurityManager绑定到SecurityUtils
        SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);
        //获取Subject，并创建token
        return SecurityUtils.getSubject();
    }
}
