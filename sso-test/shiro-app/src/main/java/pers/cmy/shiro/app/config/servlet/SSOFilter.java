package pers.cmy.shiro.app.config.servlet;

import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

import static javax.servlet.http.HttpServletResponse.SC_FORBIDDEN;
import static javax.servlet.http.HttpServletResponse.SC_OK;

/**
 * <p>@author chenmingyi
 * <p>@version 1.0
 * <p>date: 2017/9/2
 */
@WebFilter(filterName = "ssoFilter", urlPatterns = "/*")
public class SSOFilter implements Filter {

    private static final Logger log = LoggerFactory.getLogger(SSOFilter.class);

    private String checkTokenUrl = "http://localhost:8083/auth/check";
    private String unAuthorizedUrl = "/auth/401";
    private String forbiddenUrl = "/auth/403";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("SSOFilter init.....");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        String token = req.getHeader("Authorization");
        if (StringUtils.hasLength(token)) {
            int statusCode;
            try {
                statusCode = Unirest.get(checkTokenUrl).queryString("token", token).asString().getStatus();
            } catch (UnirestException e) {
                e.printStackTrace();
                throw new IOException("验证token出错，checkTokenUrl:" + checkTokenUrl, e);
            }
            switch (statusCode) {
                case SC_OK:
                    chain.doFilter(request, response);
                    break;
                case SC_FORBIDDEN:
                    request.getServletContext().getRequestDispatcher(forbiddenUrl).forward(request, response);
                    break;
                default:
                    request.getServletContext().getRequestDispatcher(unAuthorizedUrl).forward(request, response);
            }
        }
    }

    @Override
    public void destroy() {
        log.info("SSOFilter destroy.....");
    }
}
