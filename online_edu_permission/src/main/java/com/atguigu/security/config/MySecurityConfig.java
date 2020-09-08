package com.atguigu.security.config;

import com.atguigu.security.filter.LoginAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

//把该类当做一个xml文件
@Configuration
//启用springsecurity
@EnableWebSecurity
//开启 判断用户是否对某个控制层拥有访问权限
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class MySecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private RedisTemplate redisTemplate;

    //认证或者授权限的配置(用那个类实现)
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
    }

    //主要配置使用那个过滤器 过滤器请求拦截
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //1.不拦截csrf
        http.csrf().disable();
        //2.登录认证过滤器  authenticationManager()和通过@Autowired是一样的道理
        http.addFilter(new LoginAuthenticationFilter());
    }
}
