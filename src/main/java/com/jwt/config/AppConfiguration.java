package com.jwt.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class AppConfiguration {

    //in memory user details
    /*
    @Bean
    public UserDetailsService userDetailsService() {

        UserDetails user = User.builder().username("Tuhin")
                .password(passwordEncoder().encode("hello"))
                .roles("ADMIN").build();

        UserDetails uaer1 = User.builder().username("Prince")
                .password(passwordEncoder().encode("hi"))
                .roles("ADMIN").build();

        return new InMemoryUserDetailsManager(user, uaer1);
    }
    */

    //password encoder
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    //authentication manager
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration auth) throws Exception {
        return auth.getAuthenticationManager();
    }
}
