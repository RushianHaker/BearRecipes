package com.service.bearrecipes.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@EnableWebSecurity
@Configuration
public class SecurityConfiguration implements WebMvcConfigurer {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.sessionManagement(sessionManagement ->
                        sessionManagement.sessionCreationPolicy(SessionCreationPolicy.ALWAYS))
                .authorizeHttpRequests(request ->
                        request.requestMatchers(
                                        "/",
                                        "/user/registration",
                                        "/error").permitAll()
                                .requestMatchers(
                                        "/logout",
                                        "/approvepurchases",
                                        "/receipt",
                                        "/receipt/inforeceipt/{receiptId}",
                                        "/order",
                                        "/order/createorder",
                                        "/user/profileuser").hasAnyRole("ADMIN", "USER", "DEV")
                                .requestMatchers(
                                        "/receipt/addreceipt",
                                        "/receipt/editreceipt/{receiptId}",
                                        "/receipt/delreceipt/{receiptId}",
                                        "/ingredient/addingredient/{receiptId}",
                                        "/ingredient/deleteingredient/{ingredientId}",
                                        "/stepinfo/addstep/{receiptId}",
                                        "/stepinfo/deletestep/{stepInfoId}").hasAnyRole("ADMIN", "DEV")
                                .requestMatchers(
                                        "/actuator",
                                        "/v3/api-docs/**",
                                        "/swagger-ui/**",
                                        "/swagger-ui.html").hasRole("DEV")
                                .anyRequest()
                                .authenticated())
                .formLogin(Customizer.withDefaults())
                .csrf(AbstractHttpConfigurer::disable)
                .build();
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
    }

    @SuppressWarnings("deprecation")
    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }
}
