package es.iesclaradelrey.da2d1e.shopvlcdio.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.persistence.autoconfigure.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;


@SpringBootApplication(scanBasePackages = {
        "es.iesclaradelrey.da2d1e.shopvlcdio.common",
        "es.iesclaradelrey.da2d1e.shopvlcdio.web",
        "es.iesclaradelrey.da2d1e.shopvlcdio.security"})

@EntityScan("es.iesclaradelrey.da2d1e.shopvlcdio.common")
@EnableJpaRepositories("es.iesclaradelrey.da2d1e.shopvlcdio.common")
public class ShopVlcDioWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShopVlcDioWebApplication.class, args);
    }

}
