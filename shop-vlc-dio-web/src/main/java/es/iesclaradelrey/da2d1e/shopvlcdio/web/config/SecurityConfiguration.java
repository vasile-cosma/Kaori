package es.iesclaradelrey.da2d1e.shopvlcdio.web.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {



    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        System.out.println("securityFilterChain");
        http
                .headers(config -> config.frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin))

                .csrf(csrf -> csrf
                        .ignoringRequestMatchers("/h2-console/**"))

                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/h2-console", "/h2-console/**").authenticated()
                        .requestMatchers("/admin","/admin/**").authenticated()
                        .requestMatchers("/register", "/register/").anonymous()
                        .anyRequest().permitAll())

                .formLogin(Customizer.withDefaults())

                .httpBasic((AbstractHttpConfigurer::disable));

        return http.build();
    }

}
