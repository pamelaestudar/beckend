package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // Permite todos os endpoints
                .allowedOrigins("http://127.0.0.1:5500", "http://localhost:5500") // Permite acesso do frontend
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Permite os métodos
                .allowedHeaders("*") // Permite todos os headers
                .allowCredentials(true); // Permite envio de cookies (se necessário)
    }
}
