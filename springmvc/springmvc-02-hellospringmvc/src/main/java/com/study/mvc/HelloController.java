package com.study.mvc;


import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HelloController implements Controller {

    public ModelAndView handleRequest(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
        System.out.println("------------------------");
        //ModelAndView模型和试图
        ModelAndView mv = new ModelAndView();
        //封装对象，放在ModelANdView中
        mv.addObject("msg","Hello-SpringMvc");
        mv.setViewName("hello");
        return mv;
    }
}
