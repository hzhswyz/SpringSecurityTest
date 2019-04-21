package com.hzh.config.securityconfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.*;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.*;

import javax.sql.DataSource;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {


    @Autowired
    private DataSource dataSource;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .jdbcAuthentication()
                .dataSource(dataSource)
                .usersByUsernameQuery("SELECT account as username, password, true from user WHERE user.account = ?")
                .authoritiesByUsernameQuery("SELECT account as username, role from user WHERE user.account = ?");
    }

    @Configuration
    @Order(2)
    public static class ApiWebSecurityConfigurationAdapter extends WebSecurityConfigurerAdapter {
        @Autowired
        MyLogoutHandler myLogoutHandler;
        @Autowired
        MyLogoutSuccessHandler myLogoutSuccessHandler;
        protected void configure(HttpSecurity http) throws Exception {
            http
                    .authorizeRequests()
                    .antMatchers("/resources/**", "/signup", "/about").permitAll()
                    .anyRequest().authenticated()
                    .and()
                    .formLogin()
                    .loginPage("/login")
                    .permitAll()
                    .and()
                    .logout()
                    .logoutUrl("/logout")
                    .logoutSuccessUrl("/")
                    .logoutSuccessHandler(myLogoutSuccessHandler)
                    .invalidateHttpSession(true)
                    .addLogoutHandler(myLogoutHandler);
            // .deleteCookies(cookieNamesToClear)
        }

        @Configuration
        @Order(1)
        public static class FormLoginWebSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {
            @Override
            protected void configure(HttpSecurity http) throws Exception {
                http    .antMatcher("/admin/**")
                        .authorizeRequests()
                        .antMatchers("/admin/db/**").access("hasRole('ADMIN') and hasRole('DBA')")
                        .anyRequest().hasRole("ADMIN")
                        .and()
                        .formLogin()
                        .loginPage("/admin/login")
                        .successForwardUrl("/admin")
                        .permitAll();
            }
        }

    }
}
