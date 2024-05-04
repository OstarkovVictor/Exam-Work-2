package ru.ostarkov.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    public static final String INDEX_VIEW = "index";
    public static final String LOGIN_VIEW = "login";
    public static final String ACCESS_DENIED_VIEW = "access-denied";

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName(INDEX_VIEW);
        registry.addViewController("/login").setViewName(LOGIN_VIEW);
        registry.addViewController("/access-denied").setViewName(ACCESS_DENIED_VIEW);
    }
}


