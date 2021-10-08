package net.k9connect.k9connect;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
public class K9ConnectApplication {

    public static void main(String[] args) {
        SpringApplication.run(K9ConnectApplication.class, args);
    }

}
