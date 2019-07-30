package com.mp.todo.security.config;

import com.mp.todo.security.filter.JWTRequestFilter;
import com.mp.todo.security.filter.JWTResponseFilter;
import com.mp.todo.security.service.AppUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

//@Configuration
@EnableWebSecurity
public class AppSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    AppUserDetailsService userDetailsService;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder;
    }

    @Autowired
    protected void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    // Secure the endpoins with HTTP Basic authentication
    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        http.csrf().disable();
//
//        http.authorizeRequests().antMatchers("/auth/**").permitAll();
//
//        http.authorizeRequests().antMatchers("/task").access("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')");
//
//        http.authorizeRequests().antMatchers("/task/*/edit").access("hasRole('ROLE_ADMIN')");
//        http.authorizeRequests().antMatchers("/task/add").access("hasRole('ROLE_ADMIN')");
//
//        http.authorizeRequests().and().exceptionHandling().accessDeniedPage("/auth/login");
//
//        http.authorizeRequests().and().formLogin()
//                .loginProcessingUrl("/auth")
//                .loginPage("/auth/login")
//                .defaultSuccessUrl("/task")
//                .failureUrl("/auth/login?status=failure")
//                .usernameParameter("username")
//                .passwordParameter("password")
//                .and().logout().logoutUrl("/auth/logout").logoutSuccessUrl("/task");

        http.cors().and().csrf().disable().authorizeRequests()
                .antMatchers("/api/task").access("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
                .antMatchers("/api/auth/**").permitAll()
                .and()
                .addFilter(new JWTResponseFilter(authenticationManager()))
                .addFilter(new JWTRequestFilter(authenticationManager(), userDetailsService))
                // this disables session creation on Spring Security
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

    }


}