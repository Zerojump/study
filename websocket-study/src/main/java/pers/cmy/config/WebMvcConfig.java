package pers.cmy.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import pers.cmy.domain.Monitor;

/**
 * <p>@author chenmingyi
 * <p>@version 1.0
 * <p>date: 2017/11/27
 */
@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/ws").setViewName("/ws");
        registry.addViewController("/ws2").setViewName("/ws2");
        registry.addViewController("/login").setViewName("/login");
        registry.addViewController("/chat").setViewName("/chat");
    }

    @Bean(initMethod = "sendMsg")
    public Monitor monitor() {
        return new Monitor();
    }
}
