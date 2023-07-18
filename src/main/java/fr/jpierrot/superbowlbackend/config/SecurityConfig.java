package fr.jpierrot.superbowlbackend.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final JwtAuthFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        // TODO: ** PRODUCTION - IMPORTANT ** => réactiver la configuration csrf pour la production
        // TODO: ** PRODUCTION - IMPORTANT ** => configurer CORS

        // TODO : need to be edited as this is a temporary security config for testing
        http
            .csrf().disable()
            .cors().disable()
                .authorizeHttpRequests()
                .requestMatchers("/","/api/swagger-hui.html",
                        "/api",
                        "/api/matches/**",
                        "/api/teams/**",
                        "/api/players/**",
                        "/api/users/new",
                        "/admin27864/**"
                ).permitAll()
            .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
            .cors().disable()
        ;

        return http.build();
    }
}