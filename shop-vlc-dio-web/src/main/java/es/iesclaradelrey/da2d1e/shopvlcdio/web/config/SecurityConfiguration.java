package es.iesclaradelrey.da2d1e.shopvlcdio.web.config;

import es.iesclaradelrey.da2d1e.shopvlcdio.security.services.AppUserDetailsService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.DefaultAuthenticationEventPublisher;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    private final AppUserDetailsService appUserDetailsService;
    private final SecurityMonitor securityMonitor;

    public SecurityConfiguration(AppUserDetailsService appUserDetailsService, SecurityMonitor securityMonitor) {
        this.appUserDetailsService = appUserDetailsService;;
        this.securityMonitor = securityMonitor;
    }

    @Bean
    public DefaultAuthenticationEventPublisher authenticationEventPublisher(
            ApplicationEventPublisher applicationEventPublisher) {
        return new DefaultAuthenticationEventPublisher(applicationEventPublisher);
    }
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

                .formLogin(form -> form
                        .loginPage("/login").permitAll())

                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessHandler(securityMonitor))

                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.ALWAYS))


                .httpBasic((AbstractHttpConfigurer::disable));

        return http.build();
    }

}
