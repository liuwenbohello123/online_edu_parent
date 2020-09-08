package com.atguigu.security.filter;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//基于用户名和密码的身份验证过滤器
public class LoginAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    public LoginAuthenticationFilter() {
        this.setPostOnly(false);
        //第一个参数 登录过滤的路径 第二个参数 方法类型POST必须大写
        AntPathRequestMatcher matcher = new AntPathRequestMatcher("/acl/index/login", "POST");
        //告诉过滤器 拦截的内容
        this.setRequiresAuthenticationRequestMatcher(matcher);
    }
    //尝试认证
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        return super.attemptAuthentication(request, response);
    }
}
