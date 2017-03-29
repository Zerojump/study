package com.cmy.chapter2;

import com.cmy.BaseTest;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.junit.Test;

/**
 * @author chenmingyi
 * @version 1.0
 * @ DATE:3/8/2017
 */
public class LoginLogoutTest extends BaseTest {

    @Test
    public void testHelloWorld() throws Exception {
        subject = getSubject("classpath:chapter2/shiro.ini");
        token = new UsernamePasswordToken("chan", "321");
    }

    @Test
    public void testCustomRealm() throws Exception {
        subject = getSubject("classpath:chapter2/shiro-realm.ini");
        token = new UsernamePasswordToken("myName", "myPassword");
    }

    @Test
    public void testMultiCustomAndJDBCRealm() throws Exception {
        subject = getSubject("classpath:chapter2/shiro-jdbc.ini");
        //token = new UsernamePasswordToken("ruby", "ruby-password");
        token = new UsernamePasswordToken("myName", "myPassword");
    }
}
