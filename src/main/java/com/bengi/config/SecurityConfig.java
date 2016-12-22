package com.bengi.config;

import com.bengi.security.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * Created by Ding Guo Hua on 12/22/2016.
 */

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    @Bean
    public UserDetailsService userDetailsService(){
        return new CustomUserDetailsService();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth.userDetailsService(userDetailsService());
    }

    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/","/home").permitAll()
                .antMatchers("/helloadmin").hasRole("admin")
                .anyRequest().authenticated()
             .and()
                .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/helloadmin")
                .permitAll()
             .and()
                .logout().permitAll();

    }

}
