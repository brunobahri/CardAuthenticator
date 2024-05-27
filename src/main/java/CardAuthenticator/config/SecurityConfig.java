package CardAuthenticator.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())  // Desabilita CSRF
            .authorizeHttpRequests(authz -> authz
                .requestMatchers("/api/csrf-token").authenticated()  // Exige autenticação para este endpoint
                .anyRequest().permitAll())  // Permite todas as outras requisições
            .httpBasic(httpBasic -> {});  // Utiliza autenticação básica com configuração padrão
        return http.build();
    }
}