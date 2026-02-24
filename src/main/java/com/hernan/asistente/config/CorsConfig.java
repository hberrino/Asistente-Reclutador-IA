package com.hernan.asistente.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Configuration
public class CorsConfig {

    @Value("${app.frontend.url:http://localhost:3000}")
    private String frontendUrl;

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                List<String> allowedOrigins = Stream.of(frontendUrl.split(","))
                        .map(String::trim)
                        .filter(url -> !url.isEmpty())
                        .collect(Collectors.toList());
                
                // Agregar URLs de desarrollo por defecto si no están ya incluidas
                List<String> defaultDevUrls = Arrays.asList("http://localhost:3000", "http://localhost:5173");
                for (String devUrl : defaultDevUrls) {
                    if (!allowedOrigins.contains(devUrl)) {
                        allowedOrigins.add(devUrl);
                    }
                }
                
                String[] originsArray = allowedOrigins.toArray(new String[0]);
                
                registry.addMapping("/api/**")
                        .allowedOrigins(originsArray)
                        .allowedMethods("GET", "POST")
                        .allowedHeaders("*");
                
                registry.addMapping("/health")
                        .allowedOrigins("*")
                        .allowedMethods("GET")
                        .allowedHeaders("*");
            }
        };
    }
}