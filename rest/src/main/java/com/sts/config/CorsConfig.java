package com.sts.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class CorsConfig {

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();

        // Allow credentials
        config.setAllowCredentials(true);

        // Allow requests from Angular frontend
        config.addAllowedOrigin("http://localhost:4200"); // Angular dev server

        // You can add more origins if needed
        // config.addAllowedOrigin("https://your-production-domain.com");
        // Allow all headers
        config.addAllowedHeader("*");

        // Allow all methods (GET, POST, PUT, DELETE, etc.)
        config.addAllowedMethod("*");

        // Maximum age of pre-flight requests
        config.setMaxAge(3600L);

        // Apply this configuration to all paths
        source.registerCorsConfiguration("/**", config);

        // Special configuration for the user endpoint
        CorsConfiguration userConfig = new CorsConfiguration(config);
        source.registerCorsConfiguration("/api/users/by-email", userConfig);

        return new CorsFilter(source);
    }
}
