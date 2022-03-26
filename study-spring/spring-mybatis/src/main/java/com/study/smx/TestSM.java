package com.study.smx;

import com.study.smx.model.Blog;
import com.study.smx.service.BlogService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class TestSM {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("application.xml");
        BlogService blogService = classPathXmlApplicationContext.getBean("blogService", BlogService.class);
        List<Blog> blogs = blogService.getBlogs();
        for (int i = 0; i < blogs.size(); i++) {
            System.out.println(blogs.get(i));
        }
        blogs = blogService.getBlogs2();
        for (int i = 0; i < blogs.size(); i++) {
            System.out.println(blogs.get(i));
        }
        int num0001 = blogService.deleteById("num0001");
        System.out.println(num0001);
    }
}
