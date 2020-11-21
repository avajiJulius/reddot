package com.reddot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select().apis(RequestHandlerSelectors.basePackage("com.reddot.controllers"))
                .build()
                .apiInfo(metaData());
    }

    protected ApiInfo metaData() {
        return new ApiInfo(
                "RedDot REST API",
                "Description of RedDot project",
                "1.0.0",
                "Terms of service",
                new Contact("avaji", "", "aZimov.avaji@yandex.ru"),
                "Licence of API", "", Collections.emptyList());
    }
}
