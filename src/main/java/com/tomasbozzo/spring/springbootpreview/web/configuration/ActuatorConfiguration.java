package com.tomasbozzo.spring.springbootpreview.web.configuration;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ActuatorConfiguration {

    @Bean
    public HealthIndicator healthIndicator() {
        return () -> Health
                .up()
                .withDetail("OK?", "OK")
                .build();
    }

    @Bean
    public InfoContributor infoContributor() {
        return builder -> builder
                .withDetail("application", "spring-boot-preview")
                .withDetail("version", "2.0");
    }
}
