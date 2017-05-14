package com.cmy.chapter3;

import com.cmy.BaseTest;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>@author chenmingyi
 * <p>@version 1.0
 * <p>Date: 2017/5/14
 * <p>
 * To change this template use File | Settings | File and Code Templates | Includes .
 */
public class AuthorizerTest extends BaseTest {

    private static final Logger log = LoggerFactory.getLogger(AuthorizerTest.class);

    @Test
    public void testIsPermitted() throws Exception {
        subject = getSubject("classpath:chapter3/shiro-authorizer.ini");
        token = new UsernamePasswordToken("ruby", "ruby123");
        subject.login(token);

        if (log.isInfoEnabled()) {
            log.info("LOG00000:{}", log.isDebugEnabled());
            log.info("LOG00000:{}", log.isInfoEnabled());

            log.info("subject.isPermitted(\"user1:update\") = " + subject.isPermitted("user1:update"));
            log.info("subject.isPermitted(\"user2:view\") = " + subject.isPermitted("user2:view"));

            log.info("subject.isPermitted(\"+user1+2\") = " + subject.isPermitted("+user1+2"));
            log.info("subject.isPermitted(\"+user1+8\") = " + subject.isPermitted("+user1+8"));
            log.info("subject.isPermitted(\"+user2+8\") = " + subject.isPermitted("+user2+10"));
            log.info("subject.isPermitted(\"+user1+4\") = " + subject.isPermitted("+user1+4"));
            log.info("subject.isPermitted(\"menu:view\") = " + subject.isPermitted("menu:view"));
        }

    }
}
