package com.foodtech.mate.security.configs;

import com.foodtech.mate.security.handler.CustomLogoutHandler;
import com.foodtech.mate.security.handler.CustomLogoutSuccessHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/sign-in", "/sign-up","/generate-verificationCode", "/find-userId", "/create-verification-password", "/verification-password", "/change-password").anonymous()
                .antMatchers("/", "/sign-out", "view-orders", "/accept-order", "/cancel-order", "/ready-order", "/request-driver-assignment", "/create-delivery-info", "/delivery-driver-assignment", "/delivery-pickup", "/delivery-complete").hasRole("USER")
                .anyRequest().authenticated()
        .and()
                .formLogin()
                .loginPage("/sign-in")
                .usernameParameter("userId")
                .loginProcessingUrl("/sign-in-proc")
                .defaultSuccessUrl("/")
        .and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/sign-out"))
                .logoutSuccessUrl("/sign-in")
                .addLogoutHandler(new CustomLogoutHandler())
                .logoutSuccessHandler(new CustomLogoutSuccessHandler())
                .deleteCookies("JSESSIONID", "remember-me")
        ;

        return http.build();
    }
}
