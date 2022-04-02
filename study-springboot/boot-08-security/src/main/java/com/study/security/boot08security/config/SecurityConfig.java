package com.study.security.boot08security.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.study.security.boot08security.service.UserService;
import com.study.security.boot08security.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/*@Configuration
@EnableWebSecurity*/
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    UserServiceImpl userService;

    /**
     * 从 Spring5 开始，强制要求密码要加密，如果不想加密，可以使用一个过时的 PasswordEncoder 的实例 NoOpPasswordEncoder
     * BCryptPasswordEncoder 密码编码工具
     * @return
     */
    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    //配置忽略掉的 URL 地址,一般用于js,css,图片等静态资源
    @Override
    public void configure(WebSecurity web) throws Exception {
        //web.ignoring() 用来配置忽略掉的 URL 地址，一般用于静态文件
        web.ignoring().antMatchers("/js/**", "/css/**","/fonts/**","/images/**","/lib/**");
    }

    // （认证）配置用户及其对应的角色
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //数据在内存中定义，一般要去数据库取，jdbc中去拿，
        /**
         * 懒羊羊,灰太狼,喜羊羊,小灰灰分别具有vip0,vip1,vip2,vip3的权限
         * root则同时又vip0到vip3的所有权限
         */
        //Spring security 5.0中新增了多种加密方式，也改变了密码的格式。
        //要想我们的项目还能够正常登陆，需要修改一下configure中的代码。我们要将前端传过来的密码进行某种方式加密
        //spring security 官方推荐的是使用bcrypt加密方式。
      /*  auth.inMemoryAuthentication()
                .withUser("懒羊羊").password(passwordEncoder().encode("123")).roles("vip0")
                .and()
                .withUser("灰太狼").password("123").roles("vip1")
                .and()
                .withUser("喜羊羊").password("123").roles("vip2")
                .and()
                .withUser("小灰灰").password("123").roles("vip3")
                .and()
                .withUser("root").password("123").roles("vip1","vip2","vip3");*/
        auth.userDetailsService(userService).passwordEncoder(passwordEncoder());
    }

    // (授权)配置 URL 访问权限,对应用户的权限
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.headers().frameOptions().disable();//开启运行iframe嵌套页面
        http.exceptionHandling().accessDeniedPage("/error.html");//配置没有权限访问跳转的自定义页面
        //任何请求都必须经过身份验证
        http.authorizeRequests()
                .antMatchers("/vip/vip0/**").hasRole("vip0")//vip1具有的权限：只有vip1用户才可以访问包含url路径"/vip/vip0/**"
                .antMatchers("/vip/vip1/**").hasRole("vip1")//vip1具有的权限：只有vip1用户才可以访问包含url路径"/vip/vip1/**"
                .antMatchers("/vip/vip2/**").hasRole("vip2")//vip2具有的权限：只有vip2用户才可以访问url路径"/vip/vip2/**"
                .antMatchers("/vip/vip3/**").hasRole("vip3");//vip3具有的权限：只有vip3用户才可以访问url路径"/vip/vip3/**"
                //.anyRequest().authenticated();//任何请求都必须经过身份验证


        //开启表单验证
        http.formLogin()
                .and()
                .formLogin()//开启表单验证
                .loginPage("/toLogin")//跳转到自定义的登录页面
                .usernameParameter("name")//自定义表单的用户名的name,默认为username
                .passwordParameter("pwd")//自定义表单的密码的name,默认为password
                .loginProcessingUrl("/login")//表单请求的地址，一般与form的action属性一致，注意：不用自己写doLogin接口，只要与form的action属性一致即可
                .successForwardUrl("/index")//登录成功后跳转的页面（重定向）
                .failureForwardUrl("/toLogin")//登录失败后跳转的页面（重定向）
                .and()
                .logout()//开启注销功能
                .logoutSuccessUrl("/toLogin")//注销后跳转到哪一个页面
                .logoutUrl("/logout") // 配置注销登录请求URL为"/logout"（默认也就是 /logout）
                .clearAuthentication(true) // 清除身份认证信息
                .invalidateHttpSession(true) //使Http会话无效
                .permitAll() // 允许访问登录表单、登录接口
                .and().csrf().disable(); // 关闭csrf
    }

    //前后端分离版本的security
   /* @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()             //开启登录配置
                .antMatchers("/admin/**")
                //访问"admin/**"模式的URL必须具备ADMIN权限
                .hasRole("ADMIN")
                .antMatchers("/user/**")
                //访问"user/**"模式的 URL必须具备 AD1IN、USER 的角色
                .access( "hasAnyRole('ADMIN','USER') ")
                //除上面配置外的其它url都需要登录
                .anyRequest()
                .authenticated()
                .and()
                //开启表单登录
                .formLogin()
                //配置登录接口为“/login”
                .loginProcessingUrl("/login")
                //登录相关的接口不需要认证
                .permitAll()
                .and()
                //关闭 csrf
                .csrf()
                .disable()
                //开启表单登录
                .formLogin()
                //配置登录接口为“/login”
                .loginProcessingUrl("/login")
                //登录页面
                .loginPage("/login_page")
                //自定义认证所需的用户名和密码的参数名
                .usernameParameter("username")
                .passwordParameter("password")
                //定义登录成功的处理逻辑,这里是模拟前后端分离的情况，返回一段json，不前后端分离的话可以直接返回页面
                .successHandler(new AuthenticationSuccessHandler() {
                    @Override
                    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication auth) throws IOException, ServletException {
                        Object principal = auth.getPrincipal();
                        response.setContentType("application/json;charset=utf-8");
                        PrintWriter out = response.getWriter();
                        response.setStatus(200);
                        Map<String, Object> map = new HashMap<>();
                        map.put("status", 200);
                        map.put("msg", principal);
                        ObjectMapper om = new ObjectMapper();
                        out.write(om.writeValueAsString(map));
                        out.flush();
                        out.close();
                    }
                })
                //定义登录失败的处理逻辑
                .failureHandler(new AuthenticationFailureHandler() {
                    @Override
                    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
                        response.setContentType("application/json;charset=utf-8");
                        PrintWriter out = response.getWriter();
                        response.setStatus(401);
                        Map<String, Object> map = new HashMap<>();
                        map.put("status", 401);
                        if (e instanceof LockedException) {
                            map.put("msg", "账号被锁定,登录失败 ！");
                        } else if (e instanceof BadCredentialsException) {
                            map.put("msg", "账户名或密码输入错误， 登录失败!");
                        } else if (e instanceof DisabledException) {
                            map.put("msg", "账户被禁用，登录失败！");
                        } else if (e instanceof AccountExpiredException) {
                            map.put("msg", "账户已过期，登录失败！");
                        } else {
                            map.put("msg", "登录失败！");
                        }
                        ObjectMapper om = new ObjectMapper ();
                        out .write(om.writeValueAsString(map));
                        out.flush();
                        out.close();
                    }
                })
                //登录相关的接口不需要认证
                .permitAll()
                .and();
    }*/
}
