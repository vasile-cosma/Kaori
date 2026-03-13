package es.iesclaradelrey.da2d1e.shopvlcdio.web.config;

import es.iesclaradelrey.da2d1e.shopvlcdio.security.services.AppUserDetailsService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.web.SecurityFilterChain;


@EnableMethodSecurity
@Configuration
@EnableWebSecurity
public class SecurityConfiguration {


    private final AppUserDetailsService appUserDetailsService;

    public SecurityConfiguration(AppUserDetailsService appUserDetailsService) {
        this.appUserDetailsService = appUserDetailsService;
    }

    @Value("${shop.security.bcrypt.cost.factor}")
    private int hashCostFactor;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        System.out.println("securityFilterChain");
        http
                .headers(config -> config.frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin))

                .csrf(csrf -> csrf
                        .ignoringRequestMatchers("/h2-console/**"))

                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/h2-console/**").hasRole("ADMIN")
                        .requestMatchers("/admin","/admin/**").hasRole("ADMIN")
                        .requestMatchers("/users/profile", "/users/profile/**").hasRole("ADMIN")
                        .requestMatchers("/register", "/register/").anonymous()
                        .anyRequest().permitAll())

                .formLogin(Customizer.withDefaults())

                .httpBasic((AbstractHttpConfigurer::disable));

        return http.build();
    }

}
