package com.mito.gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.config.annotation.web.configurers.RememberMeConfigurer;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.server.SecurityWebFilterChain;
import reactor.core.publisher.Mono;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity serverHttpSecurity) {

        return serverHttpSecurity.csrf(new Customizer<ServerHttpSecurity.CsrfSpec>() {
                    @Override
                    public void customize(ServerHttpSecurity.CsrfSpec csrfSpec) {
                        csrfSpec.disable();
                    }
                })
                .authorizeExchange(new Customizer<ServerHttpSecurity.AuthorizeExchangeSpec>() {
                    @Override
                    public void customize(ServerHttpSecurity.AuthorizeExchangeSpec authorizeExchangeSpec) {
                        authorizeExchangeSpec.anyExchange().permitAll();
                    }
                })
                .formLogin(new Customizer<ServerHttpSecurity.FormLoginSpec>() {
                    @Override
                    public void customize(ServerHttpSecurity.FormLoginSpec formLoginSpec) {
                        formLoginSpec.disable();
                    }
                })
                .logout(new Customizer<ServerHttpSecurity.LogoutSpec>() {
                    @Override
                    public void customize(ServerHttpSecurity.LogoutSpec logoutSpec) {
                        logoutSpec.disable();
                    }
                })
                .build();
    }


}
