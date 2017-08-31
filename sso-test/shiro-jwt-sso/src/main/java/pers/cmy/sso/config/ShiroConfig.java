package pers.cmy.sso.config;

import org.apache.shiro.authc.credential.AllowAllCredentialsMatcher;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pers.cmy.sso.GowildInnerManagerRealm;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * <p>@author chenmingyi
 * <p>@version 1.0
 * <p>date: 2017/8/23
 */
@Configuration
public class ShiroConfig {

    @Bean
    public SecurityManager securityManager(GowildInnerManagerRealm gowildInnerManagerRealm) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
//      <!-- 用户授权/认证信息Cache, 采用EhCache 缓存 -->
        securityManager.setRealm(gowildInnerManagerRealm);
        return securityManager;
    }

    @Bean
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    @Bean
    public CredentialsMatcher credentialsMatcher() {
        return new AllowAllCredentialsMatcher();
    }

    @Bean
    public GowildInnerManagerRealm gowildInnerManagerRealm(CredentialsMatcher credentialsMatcher) {
        GowildInnerManagerRealm realm = new GowildInnerManagerRealm();
        realm.setCredentialsMatcher(credentialsMatcher);
        return realm;
    }

    @Bean
    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager) {

        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        // 必须设置 SecurityManager
        shiroFilterFactoryBean.setSecurityManager(securityManager);

        //拦截器.
        Map<String,String> filterChainDefinitionMap = new LinkedHashMap<>();

        filterChainDefinitionMap.put("/login", "anon");

        filterChainDefinitionMap.put("/read/count", "authc,roles[read],perms[count]");
        filterChainDefinitionMap.put("/read/add", "authc,roles[read],perms[add]");
        filterChainDefinitionMap.put("/write/add", "authc,roles[write],perms[add]");
        filterChainDefinitionMap.put("/read/**", "authc,roles[read]");

        //配置退出 过滤器,其中的具体的退出代码Shiro已经替我们实现了
        filterChainDefinitionMap.put("/logout", "logout");
        //<!-- 过滤链定义，从上向下顺序执行，一般将/**放在最为下边 -->:这是一个坑呢，一不小心代码就不好使了;
        //<!-- authc:所有url都必须认证通过才可以访问; anon:所有url都都可以匿名访问-->
        filterChainDefinitionMap.put("/**", "authc");
        // 如果不设置默认会自动寻找Web工程根目录下的"/login.jsp"页面
        //shiroFilterFactoryBean.setLoginUrl("/login");

        //未授权界面;
        shiroFilterFactoryBean.setUnauthorizedUrl("/403");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);

        return shiroFilterFactoryBean;
    }
}
