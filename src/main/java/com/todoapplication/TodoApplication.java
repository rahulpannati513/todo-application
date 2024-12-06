package com.todoapplication;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class TodoApplication {

    @Value("${frontend.url.local:http://localhost:3000}")
    private String frontendUrlLocal; // Default to localhost if not set

    @Value("${frontend.url.prod:https://your-production-frontend.com}")
    private String frontendUrlProd; // Default to production URL if not set

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    public static void main(String[] args) {
        SpringApplication.run(TodoApplication.class, args);
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                // Allow both frontend URLs for CORS
                registry.addMapping("/api/**")
                        .allowedMethods("*")
                        .allowedOrigins(frontendUrlLocal, frontendUrlProd); // Allow both URLs
            }
        };
    }
}
