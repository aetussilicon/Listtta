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
                        //Endpoints públicos
                        .requestMatchers(HttpMethod.POST, "/auth/signup").permitAll()
                        .requestMatchers(HttpMethod.POST, "/auth/login").permitAll()
                        .requestMatchers(HttpMethod.GET, "/blog/get/{postId}").permitAll()
                        .requestMatchers(HttpMethod.GET, "/blog/get/all").permitAll()

                        //Endpoints usuários
                        .requestMatchers(HttpMethod.GET, "/filters/list/all").permitAll()//hasRole("USER")
                        .requestMatchers(HttpMethod.GET, "/professionals/list/all").permitAll()//hasRole("USER")
                        .requestMatchers(HttpMethod.GET, "/filters/list/{filterName}").permitAll()//.hasRole("USER")
                        .requestMatchers(HttpMethod.POST, "/users/update/{userTag}").permitAll()//hasRole("USER")
                        .requestMatchers(HttpMethod.GET, "/users/list/{userTag}").permitAll()//hasRole("USER")

                        //Endpoints profissionais
                        .requestMatchers(HttpMethod.GET, "/professionals/list/{userTag}").hasRole("PROFESSIONAL")
                        .requestMatchers(HttpMethod.POST, "/professionals/update/{userTag}").permitAll()//hasRole("PROFESSIONAL")

                        //Endpoints admins
                        .requestMatchers(HttpMethod.POST, "/filters/create").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/filters/update/{filterId}").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/filters/delete/{filterId}").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/users/list/all").permitAll()//hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/blog/publish").permitAll()
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
