package com.cmy.chapter3;

import com.cmy.BaseTest;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.junit.Test;

/**
 * @author chenmingyi
 * @version 1.0
 * @ DATE:3/15/2017
 */
public class PermissionTest extends BaseTest {

    @Test
    public void testIsPermitted() throws Exception {
        subject = getSubject("classpath:chapter3/shiro-permission.ini");
        token = new UsernamePasswordToken("ruby", "ruby123");
        subject.login(token);


    }
}
