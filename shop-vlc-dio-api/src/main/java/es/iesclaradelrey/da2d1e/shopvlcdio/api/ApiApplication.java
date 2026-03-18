package es.iesclaradelrey.da2d1e.shopvlcdio.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.persistence.autoconfigure.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication(scanBasePackages = {
        "es.iesclaradelrey.da2d1e.shopvlcdio.common",
        "es.iesclaradelrey.da2d1e.shopvlcdio.api",
        "es.iesclaradelrey.da2d1e.shopvlcdio.security"})

@EntityScan("es.iesclaradelrey.da2d1e.shopvlcdio.common")
@EnableJpaRepositories("es.iesclaradelrey.da2d1e.shopvlcdio.common")
public class ApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiApplication.class, args);
    }

}
