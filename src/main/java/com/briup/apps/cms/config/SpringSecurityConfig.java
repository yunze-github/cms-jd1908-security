package com.briup.apps.cms.config;

import com.briup.apps.cms.service.security.CustDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;



/**
 * <h3>cms_jd1908</h3>
 * <p>访问controllerAPI接口需要Spring Security</p>
 *
 * @author : yunze
 * @date : 2019-11-18 10:09
 **/

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {


    //注册用户权限
    @Autowired
    private CustDetailsService custDetailsService;

    /**
     * 拦截管理规则匹配
     */
    //Spring Security对ignore指定的路径不需要过滤拦截进行认证
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/user/login","/user/logout","/swagger-ui.html");

    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //角色匹配规则，指定某一个角色的权限
        http
                .authorizeRequests()
                .antMatchers("/**").hasRole("admin")
                .antMatchers("/article/*").hasRole("editor")
                .and().cors()           //跨域请求，前台访问后端接口
                .and().csrf().disable();//跨站请求伪造

        /*
        登录和退出
         */
        //开启自动登录功能，效果，如果没有登录，没有权限就会来到登录界面
        //1 login来到登录界面
        //2 重定向/login?error
        //3更多规则
        //4默认post方式/login代表处理登录
        //5一旦定制loginPage，那么loginPage的请求就是登录

        /*
        logout自动注销功能
         */
        //1访问logout表示用户注销
        //2注销后，返回logoutSuccessUrl指定页面
        //3loginPage指定登录时跳转的页面
        http
                .formLogin().loginPage("http://localhost:9527/#/login?redirect=%2Fdashboard").permitAll()
                .and()
                .logout().logoutSuccessUrl("http://localhost:9527/#/login?redirect=%2Fdashboard")
                .and().cors();

        //开启自动登录
        //http.rememberMe().rememberMeParameter("remeber");

        //登录成功后，将cookie发给浏览器进行保存，点击注销会删除这个cokie
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(custDetailsService).passwordEncoder(new BCryptPasswordEncoder());
    }


}

