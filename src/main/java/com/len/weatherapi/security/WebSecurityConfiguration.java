package com.len.weatherapi.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Override
    public void configure(WebSecurity web)  {
        web.ignoring().antMatchers("/api-docs",
                "/api-docs/**",
                "/swagger-ui.html",
                "/swagger-ui/**");
    }
}

