package com.mito.auth.config;

import com.mito.auth.service.impl.UserDetailsServiceImpl;
import jakarta.annotation.Resource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Resource
    UserDetailsServiceImpl userDetailsServiceImpl;

    @Resource
    AuthenticationEntryPoint authenticationEntryPoint;

    @Resource
    AccessDeniedHandler accessDeniedHandler;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(){

        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(userDetailsServiceImpl);
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());

        ProviderManager providerManager = new ProviderManager(daoAuthenticationProvider);

        return providerManager;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

        return httpSecurity
                .authorizeHttpRequests(new Customizer<AuthorizeHttpRequestsConfigurer<org.springframework.security.config.annotation.web.builders.HttpSecurity>.AuthorizationManagerRequestMatcherRegistry>() {
                    @Override
                    public void customize(AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry matcherRegistry) {
                        matcherRegistry
                                .requestMatchers("/auth/login").anonymous();

                        matcherRegistry
                                .anyRequest().permitAll();
                    }
                })
                .csrf(new Customizer<CsrfConfigurer<HttpSecurity>>() {
                    @Override
                    public void customize(CsrfConfigurer<HttpSecurity> csrfConfigurer) {
                        csrfConfigurer.disable();
                    }
                })
                .exceptionHandling(new Customizer<ExceptionHandlingConfigurer<HttpSecurity>>() {
                    @Override
                    public void customize(ExceptionHandlingConfigurer<HttpSecurity> exceptionHandlingConfigurer) {
                        exceptionHandlingConfigurer.authenticationEntryPoint(authenticationEntryPoint)
                                .accessDeniedHandler(accessDeniedHandler);
                    }
                })
                .formLogin(new Customizer<FormLoginConfigurer<HttpSecurity>>() {
                    @Override
                    public void customize(FormLoginConfigurer<HttpSecurity> formLoginConfigurer) {
                        formLoginConfigurer.disable();
//                        formLoginConfigurer.loginPage("http://localhost:18000/login")
//                                .successForwardUrl("http://localhost:18000/home")
//                                .failureForwardUrl("http://localhost:18000/login")
//                                .permitAll();
                    }
                })
                .logout(new Customizer<LogoutConfigurer<HttpSecurity>>() {
                    @Override
                    public void customize(LogoutConfigurer<HttpSecurity> logoutConfigurer) {
                        logoutConfigurer.disable();
//                        logoutConfigurer.logoutUrl("http://localhost:18000/logout")
//                                .logoutSuccessUrl("http://localhost:18000/login")
//                                .permitAll();
                    }
                })
                .rememberMe(new Customizer<RememberMeConfigurer<HttpSecurity>>() {
                    @Override
                    public void customize(RememberMeConfigurer<HttpSecurity> rememberMeConfigurer) {
                        rememberMeConfigurer.rememberMeParameter("remember-me");
                    }
                })
                .build();
    }
}
