package com.example.project3.Config;

import com.example.project3.Service.MyUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class ConfigSecurity {
    private final MyUserDetailsService myUserDetailsService;

    @Bean
    public DaoAuthenticationProvider getDaoAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(myUserDetailsService);
        provider.setPasswordEncoder(new BCryptPasswordEncoder());
        return provider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                .and()
                .authenticationProvider(getDaoAuthenticationProvider())
                .authorizeHttpRequests()
                .requestMatchers("api/v1/auth/register/**").permitAll()
                .requestMatchers("api/v1/auth/login/**").permitAll()
                .requestMatchers("api/v1/auth/logout/**").permitAll()
                .requestMatchers("api/v1/auth/get").hasAuthority("ADMIN")
                .requestMatchers("api/v1/auth/delete/**").hasAuthority("ADMIN")
                .requestMatchers("api/v1/auth/update/**").hasAuthority("CUSTOMER")
                .requestMatchers("api/v1/customer/get").hasAnyAuthority("ADMIN", "CUSTOMER")
                .requestMatchers("api/v1/customer/update").hasAuthority("CUSTOMER")
                .requestMatchers("api/v1/customer/one").hasAnyAuthority("ADMIN", "CUSTOMER")
                .requestMatchers("api/v1/account/get-all").hasAuthority("ADMIN")
                .requestMatchers("api/v1/account/get-one/**").hasAuthority("CUSTOMER")
                .requestMatchers("api/v1/account/get-mine").hasAuthority("CUSTOMER")
                .requestMatchers("api/v1/account/add").hasAuthority("CUSTOMER")
                .requestMatchers("api/v1/account/update").hasAuthority("CUSTOMER")
                .requestMatchers("api/v1/account/delete").hasAuthority("CUSTOMER")
                .requestMatchers("api/v1/account/activate/**").hasAuthority("CUSTOMER")
                .requestMatchers("api/v1/account/deposit/**").hasAuthority("CUSTOMER")
                .requestMatchers("api/v1/account/withdraw/**").hasAuthority("CUSTOMER")
                .requestMatchers("api/v1/account/transfer/**").hasAuthority("CUSTOMER")
                .requestMatchers("api/v1/account/block/**").hasAuthority("ADMIN")
                .requestMatchers("api/v1/employee/get").hasAnyAuthority("ADMIN", "EMPLOYEE")
                .requestMatchers("api/v1/employee/update").hasAuthority("EMPLOYEE")
                .requestMatchers("api/v1/employee/one").hasAnyAuthority("ADMIN", "EMPLOYEE")
                .anyRequest().authenticated()
                .and()
                .logout()
                .logoutUrl("/api/v1/auth/logout")
                .deleteCookies("JSESSIONID")
                .invalidateHttpSession(true)
                .and()
                .httpBasic();
        return http.build();


    }
}
