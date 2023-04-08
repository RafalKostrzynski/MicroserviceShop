package pl.kostrzynski;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class OrderDetailsApplication {
    public static void main(String[] args) {
        SpringApplication.run(OrderDetailsApplication.class, args);
    }
}
