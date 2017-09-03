package pers.cmy.shiro.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

/**
 * <p>@author chenmingyi
 * <p>@version 1.0
 * <p>date: 2017/9/2
 */
@SpringBootApplication
@ServletComponentScan("pers.cmy.shiro.app.config.servlet")
public class ShiroApp {
    public static void main(String[] args) {
        SpringApplication.run(ShiroApp.class, args);
    }

}
