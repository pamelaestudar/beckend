package com.example.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors() // Habilita CORS
                .and()
                .csrf().disable() // Desativa CSRF
                .authorizeHttpRequests()
                .anyRequest().permitAll() // Permite todas as requisições sem autenticação
                .and()
                .httpBasic().disable(); // Desativa autenticação básica

        return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.addAllowedOriginPattern("*"); // Permite todas as origens
        corsConfiguration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS")); // Métodos HTTP permitidos
        corsConfiguration.setAllowedHeaders(List.of("*")); // Permite todos os cabeçalhos
        corsConfiguration.setAllowCredentials(true); // Permite credenciais (se necessário)

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfiguration); // Aplica as configurações para todas as rotas
        return source;
    }
}
