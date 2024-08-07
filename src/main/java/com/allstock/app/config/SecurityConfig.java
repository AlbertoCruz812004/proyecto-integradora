package com.allstock.app.config;

import com.allstock.app.config.filter.JwtTokenValidator;
import com.allstock.app.services.UserDetailsServiceImpl;
import com.allstock.app.util.JwtUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@EnableMethodSecurity
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private JwtUtils jwtUtils;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(http -> {
                    http.requestMatchers(HttpMethod.GET, "/").permitAll();
                    http.requestMatchers(HttpMethod.GET, "/404").permitAll();
                    http.requestMatchers(HttpMethod.GET, "/test/hello").permitAll();
                    http.requestMatchers(HttpMethod.GET, "/test/helloSecurity").hasRole("ADMIN");
                    http.requestMatchers(HttpMethod.POST, "/user/create").permitAll();
                    http.requestMatchers(HttpMethod.POST, "/auth/**").permitAll();
                    http.requestMatchers(HttpMethod.GET, "/view/**").permitAll();
                    http.requestMatchers(HttpMethod.GET, "/css/**").permitAll();
                    http.requestMatchers(HttpMethod.GET, "/images/**").permitAll();
                    http.requestMatchers(HttpMethod.GET, "/fonts/**").permitAll();
                    http.requestMatchers(HttpMethod.GET, "/js/**").permitAll();
                    http.requestMatchers(HttpMethod.GET, "/secure/view/dash").authenticated();
                    http.requestMatchers(HttpMethod.POST, "/empresa/create-empresa").permitAll();
                    http.anyRequest().denyAll();
                })
                .logout(LogoutConfigurer::permitAll)
                .addFilterBefore(new JwtTokenValidator(jwtUtils), BasicAuthenticationFilter.class)
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
            throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public AuthenticationProvider authenticationProvider(UserDetailsServiceImpl detailsService) {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder());
        provider.setUserDetailsService(detailsService);
        return provider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // @Bean
    // public WebSecurityCustomizer webSecurityCustomizer() {
    // return web -> web.debug(securityDebug).ignoring().requestMatchers("/css/**",
    // "/js/**", "/img/**", "/lib/**",
    // "/favicon.ico");
    // }

}
