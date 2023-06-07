package com.order.manager.security.configs;

import com.order.manager.security.handler.CustomLogoutHandler;
import com.order.manager.security.handler.CustomLogoutSuccessHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
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
                .antMatchers("/users/sign-in", "/users/sign-up","/users/find-id", "/users/find-id/verify", "/users/reset-password/verify", "/users/reset-password").anonymous()
//                .antMatchers("/users", "/orders", "/users/sign-out", "/orders/waiting", "/orders/preparing", "/orders/complete", "/{orderId}/accept", "/{orderId}/ready", "/{orderId}/cancel", "/{orderId}/pickup", "/deliveries/{orderId}/create", "/deliveries/{deliveryId}/assignment", "/delivery-driver-assignment", "/deliveries-pickup", "/deliveries-complete", "/deliveries/tracking", "/deliveries/detail").hasRole("USER")
                .anyRequest().authenticated()
        .and()
                .formLogin()
                .loginPage("/users/sign-in")
                .usernameParameter("userId")
                .loginProcessingUrl("/users/sign-in")
                .defaultSuccessUrl("/users")
        .and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/users/sign-out"))
                .logoutSuccessUrl("/users/sign-in")
                .addLogoutHandler(new CustomLogoutHandler())
                .logoutSuccessHandler(new CustomLogoutSuccessHandler())
                .deleteCookies("JSESSIONID", "remember-me")
        ;

        return http.build();
    }
}
