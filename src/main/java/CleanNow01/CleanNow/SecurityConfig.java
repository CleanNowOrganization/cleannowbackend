package CleanNow01.CleanNow;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebMvc
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .cors().and()
            .csrf().disable()
            .authorizeHttpRequests((authorize) -> {
                    authorize
                        .requestMatchers(HttpMethod.POST, "/consumidores").permitAll() // permitir el acceso a POST /consumidores sin autenticación
                        .requestMatchers(HttpMethod.GET, "/consumidores/**").permitAll() // permitir el acceso a GET /consumidores sin autenticación
                        .requestMatchers("/auth/login/consumidores").permitAll() // permitir el acceso a /auth/login
                        .requestMatchers("/auth/login/admin").permitAll() // permitir el acceso a  /auth/login
                        .requestMatchers("/auth/login/limpiadores").permitAll() // permitir el acceso a /auth/login
                        .requestMatchers(HttpMethod.GET, "/admin/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/admin/**").permitAll()
                        .requestMatchers("/limpiadores").permitAll()
                        .anyRequest().authenticated();
            }); // todas las demás rutas requieren autenticación
        return http.build();
    }
}