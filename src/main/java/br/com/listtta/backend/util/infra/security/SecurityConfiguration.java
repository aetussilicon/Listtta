package br.com.listtta.backend.util.infra.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.Collections;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {

    private final SecurityFilter securityFilter;
    private final String listttaUrl = System.getenv("LISTTTA_URL");

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .cors(withDefaults())
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize -> authorize
                        // Endpoints
                        // Autenticação
                        .requestMatchers(HttpMethod.POST, "/auth/signup").permitAll()
                        .requestMatchers(HttpMethod.POST, "/auth/login").permitAll()
                        .requestMatchers(HttpMethod.GET, "/oauth/google/grantcode").permitAll()

                        // Usuários
                        .requestMatchers(HttpMethod.PATCH, "/users/update/{puid}").hasRole("USER")
                        .requestMatchers(HttpMethod.GET, "/users/list/{puid}").hasRole("USER")
                        .requestMatchers(HttpMethod.GET, "/users/list/all").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PATCH, "/users/update/picture/{puid}").hasRole("USER")

                        // Profissionais
                        .requestMatchers(HttpMethod.GET, "/professionals/list/all").permitAll()
                        .requestMatchers(HttpMethod.GET, "/professionals/list/{puid}").hasRole("PROFESSIONAL")
                        .requestMatchers(HttpMethod.PATCH, "/professionals/update/{puid").hasRole("PROFESSIONAL")

                        // Filtros
                        .requestMatchers(HttpMethod.GET, "/filters/list/all").permitAll()
                        .requestMatchers(HttpMethod.GET, "/filters/list/{filterName}").permitAll()
                        .requestMatchers(HttpMethod.POST, "/filters/create").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PATCH, "/filters/update/{filterId}").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/filters/delete/{filterId}").hasRole("ADMIN")

                        // Newsletter
                        .requestMatchers(HttpMethod.POST, "/newsletter/new").permitAll()

                        .anyRequest().authenticated())
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
            throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();

        configuration.setAllowedOrigins(Collections.singletonList(listttaUrl));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(Arrays.asList("*"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
