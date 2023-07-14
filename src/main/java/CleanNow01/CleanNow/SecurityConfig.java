package CleanNow01.CleanNow;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
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
            .authorizeHttpRequests((authorize) -> authorize
                .requestMatchers(HttpMethod.POST, "/consumidores").permitAll() // permitir el acceso a POST /consumidores sin autenticación
                .requestMatchers("/consumidores/auth/login").permitAll() // permitir el acceso a /auth/login sin autenticación
                .anyRequest().authenticated()
                ); // todas las demás rutas requieren autenticación
            // .addFilterBefore(firebaseFilter, UsernamePasswordAuthenticationFilter.class); // agregar el filtro Firebase antes de UsernamePasswordAuthenticationFilter
        return http.build();
    }

    @Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**")
						.allowedOrigins("http://localhost:4200")
						.allowedMethods("GET", "POST", "PUT", "DELETE", "HEAD", "OPTIONS")
						.allowedHeaders("*")
						.allowCredentials(true);
			}
		};
	}
}