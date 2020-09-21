package com.revature.services;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity security) throws Exception
    {
//     security.httpBasic().disable();
     security.formLogin().disable();
     security.csrf().disable()
     .authorizeRequests().antMatchers(HttpMethod.OPTIONS,"*/").permitAll()
     .antMatchers(HttpMethod.GET,"/login").permitAll();
    }
}