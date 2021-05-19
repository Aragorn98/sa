package com.example.Timetable1.web;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
//        registry.addViewController("/").setViewName("Teachers");
//        registry.addViewController("/").setViewName("Teachers_admin");
//        registry.addViewController("/Authorization");
        registry.addViewController("/").setViewName("redirect:/departmentList/current/orderList");
    }

}
