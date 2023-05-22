package br.com.alura.restFull.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.web.SecurityFilterChain

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
class SecurityConfig() {
    @Bean
    fun SecurityFilterChain(http: HttpSecurity): SecurityFilterChain {
        http
             .csrf().disable()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .authorizeHttpRequests()
            .requestMatchers(HttpMethod.POST, "/user").permitAll()
            .requestMatchers(HttpMethod.GET, "/user").permitAll()
            .requestMatchers(HttpMethod.GET, "/topicos").permitAll()
            .anyRequest()
            .authenticated()
            .and()
            .httpBasic()
        return http.build()
    }

    @Bean
    fun PasswordEncoder(): BCryptPasswordEncoder {
        return BCryptPasswordEncoder()
    }

    @Bean
    fun AuthenticationManager(authenticationConfiguration: AuthenticationConfiguration?): AuthenticationManager {
        return authenticationConfiguration?.authenticationManager ?: throw Exception()
    }

}