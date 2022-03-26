package com.study.controller;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginInterceptor implements HandlerInterceptor {
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
       if(request.getRequestURI().contains("goLogin")){
          return true;
       }

       if(request.getRequestURI().contains("login")){
           return true;
       }

       if(session.getAttribute("userLoginInfo") != null){
           return true;
       }
       //未登录
        request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request,response);
        return false;
    }
}
