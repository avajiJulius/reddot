package com.reddot.config;

import com.reddot.services.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.ui.Model;

import static org.mockito.Mockito.mock;

@Profile("test")
@Configuration
public class TestConfig {

    @Bean
    public UserService userService() {
        return mock(UserService.class);
    }

    @Bean
    public Model model() { return mock(Model.class);}
}
