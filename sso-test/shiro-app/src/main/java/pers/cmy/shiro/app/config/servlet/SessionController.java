package pers.cmy.shiro.app.config.servlet;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>@author chenmingyi
 * <p>@version 1.0
 * <p>date: 2017/9/2
 */
@RestController
@RequestMapping("auth")
public class SessionController {

    /**
     *
     */
    @RequestMapping(value = "401", method = RequestMethod.GET)
    public String unAuthorized() {
        return "unAuthorized";
    }

    /**
     *
     */
    @RequestMapping(value = "403", method = RequestMethod.GET)
    public String forbidden() {
        return "forbidden";
    }
}
