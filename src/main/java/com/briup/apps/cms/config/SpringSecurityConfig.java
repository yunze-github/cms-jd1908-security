package com.briup.apps.cms.config;

import com.briup.apps.cms.service.security.CustDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.ForwardAuthenticationSuccessHandler;

/**
 * <h3>cms_jd1908</h3>
 * <p>访问controllerAPI接口需要oauth2认证的controllerAPI</p>
 *
 * @author : yunze
 * @date : 2019-11-18 10:09
 **/

@Configuration
@EnableWebSecurity
@Order(-1)
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                // 设置静态的资源允许所有访问
                //.antMatchers("/static/base/**").permitAll()
                // 其他所有资源都需要登陆后才能访问
                .anyRequest().authenticated()
                // 设置默认登陆页面/login
                .and().formLogin().loginPage("/login")
                // 强制指定登陆成功后跳转的路劲
                .successHandler(new ForwardAuthenticationSuccessHandler("/loginStatus?status=true"))
                .failureUrl("/loginStatus?status=false")
                .permitAll()
                // 设置缓存，默认2周有效
                .and().rememberMe().tokenValiditySeconds(1209600).key("mykey")
                // 设置登出的路径和登出成功后访问的路径
                .and().logout().logoutUrl("/loginOut").logoutSuccessUrl("/login").permitAll()
                // 金庸crsf
                .and().csrf().disable();

    }

    /**
     * 认证信息管理
     * spring5中摒弃了原有的密码存储格式，官方把spring security的密码存储格式改了
     *
     */
    @Autowired
    private CustDetailsService custDetailsService;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(custDetailsService).passwordEncoder(new BCryptPasswordEncoder());
    }

}

