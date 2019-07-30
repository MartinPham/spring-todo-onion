package com.mp.todo.security.config;

import com.mp.todo.security.filter.JWTRequestFilter;
import com.mp.todo.security.filter.JWTResponseFilter;
import com.mp.todo.security.service.AppUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity
@Order(1)
public class ApiSecurityConfigurer extends WebSecurityConfigurerAdapter {
    @Autowired
    AppUserDetailsService userDetailsService;

    public BCryptPasswordEncoder passwordEncoder() {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder;
    }

    @Autowired
    protected void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable();

        http.authorizeRequests().antMatchers("/api/auth/**").permitAll();

        http.authorizeRequests().antMatchers("/api/task").access("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')");
        http.authorizeRequests().antMatchers("/api/task/*/edit").access("hasRole('ROLE_ADMIN')");
        http.authorizeRequests().antMatchers("/api/task/add").access("hasRole('ROLE_ADMIN')");

        http.authorizeRequests().and()
                .addFilter(new JWTResponseFilter(authenticationManager()))
                .addFilter(new JWTRequestFilter(authenticationManager(), userDetailsService))
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

    }


}