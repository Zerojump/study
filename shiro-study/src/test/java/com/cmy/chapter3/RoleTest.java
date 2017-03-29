package com.cmy.chapter3;

import com.cmy.BaseTest;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.junit.Test;

import java.util.Arrays;

/**
 * @author chenmingyi
 * @version 1.0
 * @ DATE:3/15/2017
 */
public class RoleTest extends BaseTest {

    @Test
    public void testHasRole() throws Exception {
        subject = getSubject("classpath:chapter3/shiro-role.ini");
        token = new UsernamePasswordToken("ruby", "ruby123");
        subject.login(token);

        boolean hasRole = subject.hasRole("role1");
        System.out.println("hasRole = " + hasRole);
        boolean hasAllRoles = subject.hasAllRoles(Arrays.asList("role1", "role2"));
        System.out.println("hasAllRoles = " + hasAllRoles);
        boolean[] hasRoles = subject.hasRoles(Arrays.asList("role1", "role2", "role3"));
        System.out.println("Arrays.toString(hasRoles) = " + Arrays.toString(hasRoles));
    }

    @Test
    public void testCheckRole() throws Exception {
        subject = getSubject("classpath:chapter3/shiro-role.ini");
        token = new UsernamePasswordToken("ruby", "ruby123");
        subject.login(token);

        subject.checkRole("role1");
        subject.checkRoles("role1","role2");
    }
}
