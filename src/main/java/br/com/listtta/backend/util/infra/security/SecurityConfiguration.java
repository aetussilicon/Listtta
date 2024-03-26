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

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {

    private final SecurityFilter securityFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(csrf -> csrf.disable())
                .cors(cors -> cors.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(HttpMethod.POST, "/auth/signup").permitAll()
                        .requestMatchers(HttpMethod.POST, "/auth/login").permitAll()
                        .requestMatchers(HttpMethod.GET, "/blog/list/{blogPostId}").permitAll()
                        .requestMatchers(HttpMethod.GET, "/blog/list/all").permitAll()

                        //Requests apenas usuários
                        .requestMatchers(HttpMethod.PATCH, "/users/update/{username}").hasRole("USER")
                        .requestMatchers(HttpMethod.GET, "/professionals/list/all").hasRole("USER")
                        .requestMatchers(HttpMethod.GET, "/filters/list/{filterName}").hasRole("USER")
                        .requestMatchers(HttpMethod.GET, "/filters/list/all").hasRole("USER")
                        //Requests apenas para profissionais
                        .requestMatchers(HttpMethod.PATCH, "/professionals/update/{username}").hasRole("PROFESSIONAL")
                        .requestMatchers(HttpMethod.GET, "/professionals/list/{username}").hasRole("PROFESSIONALS")
                        //Requests apenas para admins
                        .requestMatchers(HttpMethod.GET, "/users/list/all").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/professionals/list/all").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/filters/create").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PATCH, "/filters/update/{filterId}").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/filters/delete/{filterId}").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/blog/new-post").hasRole("ADMIN")
                        .anyRequest().authenticated()
                )
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }


    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
