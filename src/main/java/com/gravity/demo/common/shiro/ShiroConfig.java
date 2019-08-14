package com.gravity.demo.common.shiro;

import com.gravity.demo.common.shiro.JWTRealm;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.util.UrlPathHelper;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * shiro配置类
 *
 * @author qijunlin
 * @date 2019-08-06 19:38
 */
@Configuration
public class ShiroConfig {

    @Bean("securityManager")
    public SecurityManager securityManager(@Autowired JWTRealm JWTRealm) {
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
        defaultWebSecurityManager.setRealm(JWTRealm);
        return defaultWebSecurityManager;
    }

    @Bean("shiroFiler")
    public ShiroFilterFactoryBean shiroFiler(SecurityManager securityManager) {

        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        shiroFilterFactoryBean.setLoginUrl("/account/token");
        //登录成功的url
//        shiroFilterFactoryBean.setSuccessUrl("/index");
        //权限不足跳转页面
        shiroFilterFactoryBean.setUnauthorizedUrl("/notRole");
        Map<String, String> filterMap = new LinkedHashMap<>();
        // authc:所有url都必须认证通过才可以访问; anon:所有url都都可以匿名访问
        filterMap.put("/", "anon");
        filterMap.put("/static/**", "anon");
        filterMap.put("/**/**.*", "anon");
        filterMap.put("/v2/api-docs", "anon");
        filterMap.put("/swagger-resources/**", "anon");
//        filterMap.put("/api/**", "anon");
//        filterMap.put("/admin/**", "authc");
//        filterMap.put("/user/**", "authc");
        //主要这行代码必须放在所有权限设置的最后，不然会导致所有 url 都被拦截
        // 剩余的都需要认证
        filterMap.put("/**", "authc");


        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);
        return shiroFilterFactoryBean;
    }

}
