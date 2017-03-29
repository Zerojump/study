package com.cmy.chapter2;

import com.cmy.BaseTest;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.junit.Test;

/**
 * @author chenmingyi
 * @version 1.0
 * @ DATE:3/15/2017
 */
public class AuthenticatorTest extends BaseTest {

    @Test
    public void testAllSuccessful() throws Exception {
        subject = getSubject("classpath:chapter2/shiro-authenticator-all-success.ini");
        token = new UsernamePasswordToken("myName", "myPassword");
        //token = new UsernamePasswordToken("ruby", "ruby-password");

    }

    @Test
    public void testAtLeastOneSuccessful() throws Exception {
        subject = getSubject("classpath:chapter2/shiro-authenticator-at-least-success.ini");
        token = new UsernamePasswordToken("ruby", "ruby-password");
    }

    @Test
    public void testOnlyOneSuccessful() throws Exception {
        subject = getSubject("classpath:chapter2/shiro-authenticator-only-one.ini");
        //token = new UsernamePasswordToken("ruby", "ruby-password");
        token = new UsernamePasswordToken("myName", "myPassword");
    }

    @Test
    public void testAtLeastTwoSuccessful() throws Exception {
        subject = getSubject("classpath:chapter2/shiro-authenticator-at-least-2.ini");
        //token = new UsernamePasswordToken("ruby", "ruby-password");
        token = new UsernamePasswordToken("myName", "myPassword");
    }
}
