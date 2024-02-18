package com.ejercicio.hito1magicwater.security;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class DatabaseWebSecurity {

    @Autowired
    private DataSource dataSource;

    @Bean
    public UserDetailsService userDetailsService() {
        JdbcUserDetailsManager userDetailsManager = new JdbcUserDetailsManager(dataSource);
        userDetailsManager.setUsersByUsernameQuery("select nif, password, activo from usuario where nif=?");
        userDetailsManager.setAuthoritiesByUsernameQuery("select nif, permiso from usuario where nif=?");
        return userDetailsManager;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeHttpRequests(authz -> authz
                        .requestMatchers("/css/**", "/js/**", "/images/**").permitAll()
                        .requestMatchers("/", "/login", "/registro").permitAll()
                        .requestMatchers("/admin/**").hasAuthority("SUPERVISOR")
                        .anyRequest().authenticated())
                .formLogin(form -> form
                        .loginPage("/login")
//                        .defaultSuccessUrl("/proyectos", true)
                        .permitAll())
                .logout(logout -> logout
                        .logoutUrl("/logout") // URL para invocar el cierre de sesión
                        .logoutSuccessUrl("/") // URL a redirigir después del cierre de sesión
                        .invalidateHttpSession(true) // Invalida la sesión
                        .deleteCookies("JSESSIONID") // Borra la cookie de sesión
                        .permitAll());

        return http.build();
    }
}