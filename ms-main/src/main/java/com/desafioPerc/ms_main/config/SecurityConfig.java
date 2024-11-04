/*package com.desafioPerc.ms_main.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Security configuration
 */
/*@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
    //private final JwtAuthFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;

    /**
     * constructor
     * @param authenticationProvider {@Link AuthenticationProvider}
     */
/*    public SecurityConfig(JwtAuthFilter jwtAuthFilterfinal AuthenticationProvider authenticationProvider) {
        //this.jwtAuthFilter = jwtAuthFilter;
        this.authenticationProvider = authenticationProvider;
    }

    /**
     *
     * @param http {@Link HttpSecurity}
     * @throws Exception {@Link Exception}
     * @return SecurityFilterChain {@Link SecurityFilterChain}
     */
 /*   @Bean
    public SecurityFilterChain securityFilterChain(final HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(req ->
                        req.requestMatchers("/auth/**")
                                .permitAll()
                                .anyRequest()
                                .authenticated()
                )
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(this.authenticationProvider);
                //.addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    /**
     *
     * @param config {@Link AuthenticationConfiguration}
     * @throws Exception {@Link Exception}
     * @return AuthenticationManager {@Link AuthenticationManager}
     */
/*    @Bean
    public AuthenticationManager authenticationManager(final AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    /**
     *
     * @return PasswordEncoder {@Link PasswordEncoder}
     */
/*    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
*/
