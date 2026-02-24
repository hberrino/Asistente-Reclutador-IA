package com.hernan.asistente.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

@Configuration
public class CorsConfig {

    @Value("${app.frontend.url:http://localhost:3000}")
    private String frontendUrl;

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/api/**")
                        .allowedOrigins(frontendUrl, "http://localhost:5173")
                        .allowedMethods("GET", "POST")
                        .allowedHeaders("*");
                
                // Health check endpoint - público para robots
                registry.addMapping("/health")
                        .allowedOrigins("*")
                        .allowedMethods("GET")
                        .allowedHeaders("*");
            }
        };
    }
}