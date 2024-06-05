//package com.capgemini.wsb.fitnesstracker.user.internal;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;
//
//@Configuration
//public class SecurityConfig extends WebSecurityConfigurerAdapter {
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//            .authorizeRequests()
//            .antMatchers("/actuator/**").permitAll()
//            .antMatchers(HttpMethod.GET, "/api/users/**").authenticated()
//            .antMatchers("/api/users/**").hasRole("ADMIN")
//            .and()
//            .httpBasic()
//            .and()
//            .csrf().disable();
//    }
//
//    @Bean
//    @Override
//    public UserDetailsService userDetailsService() {
//        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
//        manager.createUser(User.withUsername("user").password("{noop}password").roles("USER").build());
//        manager.createUser(User.withUsername("admin").password("{noop}admin").roles("ADMIN").build());
//        return manager;
//    }
//}