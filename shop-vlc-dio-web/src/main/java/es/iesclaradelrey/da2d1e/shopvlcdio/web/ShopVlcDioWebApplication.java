package es.iesclaradelrey.da2d1e.shopvlcdio.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {
        "es.iesclaradelrey.da2d1e.shopvlcdio.common",
        "es.iesclaradelrey.da2d1e.shopvlcdio.web"})
public class ShopVlcDioWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShopVlcDioWebApplication.class, args);
    }

}
