package com.mp.todo.security.config;

import com.mp.todo.security.service.AppUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity
@Configuration
@Order(2)
public class BackofficeSecurityConfigurer extends WebSecurityConfigurerAdapter {
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
        http
//                .csrf().disable()
                .antMatcher("/backoffice/**")
                .authorizeRequests()

                .and().formLogin()
                    .loginProcessingUrl("/backoffice/auth")
                    .loginPage("/backoffice/auth/login")
                    .defaultSuccessUrl("/backoffice/task")
                    .failureUrl("/backoffice/auth/login?status=failure")
                    .usernameParameter("username")
                    .passwordParameter("password")
                    .and().logout().logoutUrl("/backoffice/auth/logout").logoutSuccessUrl("/backoffice/task")

                .and().authorizeRequests().antMatchers("/backoffice/auth/**").permitAll()
                .and().authorizeRequests().antMatchers("/backoffice/task").hasRole("ADMIN")
                .and().authorizeRequests().antMatchers("/backoffice/task/*/edit").hasRole("ADMIN")
                .and().authorizeRequests().antMatchers("/backoffice/task/add").hasRole("ADMIN")
        ;



    }


}