package com.reddot.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.servlet.config.annotation.*;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;

@Configuration
@ComponentScan("com.reddot")
@EnableWebMvc
@Import(SwaggerConfig.class)
public class AppConfig implements WebMvcConfigurer {

    private final ApplicationContext applicationContext;
    @Qualifier("userDetailsServiceImpl")
    private final UserDetailsService userDetailsService;

    @Autowired
    public AppConfig(ApplicationContext applicationContext,@Qualifier("userDetailsServiceImpl") UserDetailsService userDetailsService) {
        this.applicationContext = applicationContext;
        this.userDetailsService = userDetailsService;
    }


//    @Override
//    public void addViewControllers(ViewControllerRegistry registry) {
//        registry.addViewController("/login").setViewName("forward:/login");
//    }



    @Override
    public void configureDefaultServletHandling(final DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

}
