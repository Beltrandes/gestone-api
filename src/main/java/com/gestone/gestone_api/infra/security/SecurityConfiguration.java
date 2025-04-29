package com.gestone.gestone_api.infra.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
    @Autowired
    private SecurityFilter securityFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity.cors(cors -> cors.configurationSource(corsConfigurationSource())).sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)).authorizeHttpRequests(authorize ->
                authorize.requestMatchers(HttpMethod.POST, "/api/v1/auth/login").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/v1/auth/register/admin").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/v1/auth/register/employee").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/v1/marbleshop/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/api/v1/marbleshop/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/api/v1/quotation/pdf/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/api/v1/user/admin").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/api/v1/employee").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/api/v1/material").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/api/v1/material").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/api/v1/customer").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/v1/customer").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/v1/customer").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/api/v1/customer").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/api/v1/quotation").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/api/v1/quotation").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/v1/quotation/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/api/v1/material").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/api/v1/material").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/v1/material").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/v1/material").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PATCH, "/api/v1/material/update/price").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/api/v1/miscellaneous-material").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/api/v1/miscellaneous-material").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/v1/miscellaneous-material").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/v1/miscellaneous-material").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PATCH, "/api/v1/miscellaneous-material/update/price").hasRole("ADMIN")
                        .anyRequest().authenticated()).csrf(AbstractHttpConfigurer::disable).addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class).build();
    }

    @Bean
    public UrlBasedCorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of("http://localhost:4200"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(Arrays.asList("Authorization", "Cache-Control", "Content-Type"));
        configuration.setExposedHeaders(List.of("Authorization"));
        configuration.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }


    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
