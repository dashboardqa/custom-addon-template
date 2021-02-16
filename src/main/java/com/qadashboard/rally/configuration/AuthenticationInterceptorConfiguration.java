package com.qadashboard.rally.configuration;

import com.qadashboard.rally.auth.AuthenticationInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * YOU_CAN_CHANGE
 * If you dont use any authentication, you can delete this class
 */
@Configuration
public class AuthenticationInterceptorConfiguration implements WebMvcConfigurer {

    @Bean
    AuthenticationInterceptor getAuthenticationInterceptor() {
        return new AuthenticationInterceptor();
    }

    @Override
    public void addInterceptors (InterceptorRegistry registry) {
        registry.addInterceptor(getAuthenticationInterceptor());
    }
}
