package com.br.gi3.security.jwt;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.*;

import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private static final String USER = "USER";
    private static final String ADMIN = "ADMIN";

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, JwtRequestFilter jwtRequestFilter) throws Exception {
        return http
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/authenticate/**").permitAll()
                        .requestMatchers("/api/usuario/findById/**").hasAnyRole(USER, ADMIN)
                        .requestMatchers("/api/usuario/findAll").hasAnyRole(USER, ADMIN)
                        .requestMatchers("/api/usuario/save").hasRole(ADMIN)
                        .requestMatchers("/api/usuario/delete/**").hasRole(ADMIN)
                        .requestMatchers("/api/usuario/update").hasRole(ADMIN)
                        .requestMatchers("/api/prestacaoServico/save").hasRole(ADMIN)
                        .requestMatchers("/api/prestacaoServico/repasse").hasAnyRole(USER, ADMIN)
                        .requestMatchers("/api/prestacaoServico/findAll").hasAnyRole(USER, ADMIN)
                        .requestMatchers("/api/prestacaoServico/delete/**").hasRole(ADMIN)
                        .requestMatchers("/api/prestacaoServico/update").hasRole(ADMIN)
                        .requestMatchers("/api/upload/bancorbras").hasRole(ADMIN)
                        .requestMatchers("/api/upload/hs").hasRole(ADMIN)
                        .requestMatchers("/api/upload/prestacaoServico").hasRole(ADMIN)
                        .requestMatchers("/api/repasse/saveHs").hasAnyRole(ADMIN)
                        .requestMatchers("/api/repasse/repasseHs").hasAnyRole(USER, ADMIN)
                        .requestMatchers("/api/repasse/findAllHs").hasAnyRole(USER, ADMIN)
                        .requestMatchers("/api/repasse/deleteHs/**").hasAnyRole(ADMIN)
                        .requestMatchers("/api/repasse/updateHs/**").hasAnyRole(ADMIN)
                        .requestMatchers("/api/repasse/saveBancorbras").hasAnyRole(ADMIN)
                        .requestMatchers("/api/repasse/repasseBancorbras").hasAnyRole(USER, ADMIN)
                        .requestMatchers("/api/repasse/findAllBancorbras").hasAnyRole(USER, ADMIN)
                        .requestMatchers("/api/repasse/deleteBancorbras/**").hasAnyRole(ADMIN)
                        .requestMatchers("/api/repasse/updateBancorbras/**").hasAnyRole(ADMIN)
                        .anyRequest().authenticated()
                )
                .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();

        config.setAllowedOrigins(List.of("http://localhost:4200"));
        config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        config.setAllowedHeaders(List.of("*"));
        config.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);

        return source;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}