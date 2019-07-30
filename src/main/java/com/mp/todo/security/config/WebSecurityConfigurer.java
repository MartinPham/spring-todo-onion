package com.mp.todo.security.config;

import com.mp.todo.security.filter.JWTRequestFilter;
import com.mp.todo.security.filter.JWTResponseFilter;
import com.mp.todo.security.service.AppUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

//@EnableWebSecurity
//@Order(2)
public class WebSecurityConfigurer extends WebSecurityConfigurerAdapter {
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

        http.authorizeRequests().antMatchers("/auth/**").permitAll();

        http.authorizeRequests().antMatchers("/task").access("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')");
        http.authorizeRequests().antMatchers("/task/*/edit").access("hasRole('ROLE_ADMIN')");
        http.authorizeRequests().antMatchers("/task/add").access("hasRole('ROLE_ADMIN')");

        http.authorizeRequests().and().exceptionHandling().accessDeniedPage("/auth/login");

        http.authorizeRequests().and().formLogin()
                .loginProcessingUrl("/auth")
                .loginPage("/auth/login")
                .defaultSuccessUrl("/task")
                .failureUrl("/auth/login?status=failure")
                .usernameParameter("username")
                .passwordParameter("password")
                .and().logout().logoutUrl("/auth/logout").logoutSuccessUrl("/task");


    }


}