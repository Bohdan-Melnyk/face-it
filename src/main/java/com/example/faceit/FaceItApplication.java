package com.example.faceit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;

@EnableScheduling
@SpringBootApplication
public class FaceItApplication {

    public static void main(String[] args) {
        SpringApplication.run(FaceItApplication.class, args);
    }

    @Bean
    public RestTemplate getRestTemplateBean() {
        return new RestTemplate();
    }

}
