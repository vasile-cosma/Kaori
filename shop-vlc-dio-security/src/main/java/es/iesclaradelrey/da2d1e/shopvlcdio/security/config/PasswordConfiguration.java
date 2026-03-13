package es.iesclaradelrey.da2d1e.shopvlcdio.security.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class PasswordConfiguration {
    @Value("${shop.security.bcrypt.cost.factor}")
    private int hashCostFactor;
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(hashCostFactor);
    }
}
