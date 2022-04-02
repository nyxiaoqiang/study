package com.study.security.boot08security.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.study.security.boot08security.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Map;

@Configuration
@EnableWebSecurity
public class SecurityConfigJsonRequest extends WebSecurityConfigurerAdapter {

    @Autowired
    UserServiceImpl userService;

    //密码加密方式
    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    //认证（基于数据库）
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(passwordEncoder());
    }

    //授权和配置
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .anyRequest().authenticated()//所有的路径都是登录后即可访问
                .and().formLogin().loginPage("/doLogin")//如果是未登录的会自动跳到该接口（根据需要自己实现，返回页面或返回json）
                .loginProcessingUrl("/login")//发起登录请求的接口
                .usernameParameter("username")//设置登录请求接口的参数（用户名）
                .passwordParameter("password")//设置登录请求接口的参数（密码）
                .permitAll()
                .and()
                .logout()//注销登录接口（/logout）
                .logoutUrl("/logout")
                .logoutSuccessHandler(new LogoutSuccessHandler() {//注销成功时的处理
                    @Override
                    public void onLogoutSuccess(HttpServletRequest req, HttpServletResponse resp, Authentication authentication) throws IOException, ServletException {
                        resp.setContentType("application/json;charset=utf-8");
                        PrintWriter out = resp.getWriter();
                        out.write("注销成功");
                        out.flush();
                        out.close();
                    }
                })
                .permitAll().and().csrf().disable().exceptionHandling()
                .accessDeniedHandler(getAccessDeniedHandler());//用户权限不足时的处理

        http.addFilterAt(customAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    /**
     * 用户权限不足时的处理
     *
     * @return
     */
    @Bean
    AccessDeniedHandler getAccessDeniedHandler() {
        return new AuthenticationAccessDeniedHandler();
    }

    /**
     * 自定义security过滤器，以实现用post发起登录请求时，参数用json传递
     * @return
     * @throws Exception
     */
    @Bean
    CustomAuthenticationFilter customAuthenticationFilter() throws Exception {
        CustomAuthenticationFilter filter = new CustomAuthenticationFilter();

        /**登录成功**/
        filter.setAuthenticationSuccessHandler(new AuthenticationSuccessHandler() {
            @Override
            public void onAuthenticationSuccess(HttpServletRequest req, HttpServletResponse resp, Authentication authentication) throws IOException, ServletException {
                resp.setContentType("application/json;charset=utf-8");
                PrintWriter out = resp.getWriter();
                out.write("{\"status\":\"success\",\"msg\":\"登录成功\"}");
                out.flush();
                out.close();
            }
        });

        /**登录失败**/
        filter.setAuthenticationFailureHandler(new AuthenticationFailureHandler() {
            @Override
            public void onAuthenticationFailure(HttpServletRequest req, HttpServletResponse resp, AuthenticationException e) throws IOException, ServletException {
                resp.setContentType("application/json;charset=utf-8");
                PrintWriter out = resp.getWriter();
                out.write("{\"status\":\"error\",\"msg\":\"登录失败！！\"}");
                out.flush();
                out.close();
            }
        });


        filter.setAuthenticationManager(authenticationManagerBean());
        return filter;
    }

    class CustomAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
        @Override
        public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

            //拦截请求头,可以自定义配置，如果想用表单数据也可同时用json也可以用MediaType类型配置，这里只配置了json
            if (request.getContentType().equals(MediaType.APPLICATION_JSON_UTF8_VALUE) || request.getContentType().equals(MediaType.APPLICATION_JSON_VALUE)) {
                ObjectMapper mapper = new ObjectMapper();
                UsernamePasswordAuthenticationToken authRequest = null;

                try (InputStream is = request.getInputStream()) {
                    Map<String, String> authenticationBean = mapper.readValue(is, Map.class);

                    authRequest = new UsernamePasswordAuthenticationToken(
                            authenticationBean.get("username"),
                            authenticationBean.get("password")
                    );

                } catch (IOException e) {
                    e.printStackTrace();
                    authRequest = new UsernamePasswordAuthenticationToken("", "");

                } finally {
                    setDetails(request, authRequest);
                    return this.getAuthenticationManager().authenticate(authRequest);
                }

            } else {
                return super.attemptAuthentication(request, response);
            }
        }
    }

    /**
     * @ClassName : AuthenticationAccessDeniedHandler
     * @Description : security用户权限不足时的处理
     * @Author : CJH
     * @Date: 2020-08-31 16:59
     */
    class AuthenticationAccessDeniedHandler implements AccessDeniedHandler {
        @Override
        public void handle(HttpServletRequest req, HttpServletResponse resp, AccessDeniedException e) throws IOException, ServletException {
            resp.setStatus(HttpServletResponse.SC_FORBIDDEN);
            resp.setCharacterEncoding("UTF-8");
            PrintWriter out = resp.getWriter();
            out.write("权限不足,请联系管理员!");
            out.flush();
            out.close();
        }
    }
}
