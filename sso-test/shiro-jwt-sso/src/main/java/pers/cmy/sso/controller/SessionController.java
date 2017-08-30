package pers.cmy.sso.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>@author chenmingyi
 * <p>@version 1.0
 * <p>date: 2017/8/28
 */
@RestController
public class SessionController {

    /**
     *
     */
    @RequestMapping(value = "login", method = RequestMethod.POST)
    public String login(String username, String password) {
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        Subject subject = SecurityUtils.getSubject();

        try {
            subject.login(token);
        } catch (AuthenticationException e) {
            return "go back";
        }

        return "ok";
    }

    /**
     *
     */
    @RequestMapping(value = "read/count", method = RequestMethod.GET)
    public String readCount() {
        return "Can read count";
    }

    /**
     *
     */
    @RequestMapping(value = "read/add", method = RequestMethod.GET)
    public String readAdd() {
        return "Can read add";
    }

    /**
     *
     */
    @RequestMapping(value = "write/add", method = RequestMethod.GET)
    public String writeAdd() {
        return "Can write add";
    }

    /**
     *
     */
    @RequestMapping(value = "read/any", method = RequestMethod.GET)
    public String readAny() {
        return "Can read any";
    }
}
