package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    // Minimal stub so DemoMassiveTestNGTests.sc.api() compiles
    @Bean
    public Object api() {
        return new Object();
    }
}
