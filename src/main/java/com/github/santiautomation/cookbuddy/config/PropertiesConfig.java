package com.github.santiautomation.cookbuddy.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

@Configuration
@PropertySource(value = "classpath:application.properties", ignoreResourceNotFound = true)
public class PropertiesConfig {

    @Bean
    public PropertySourcesPlaceholderConfigurer propConfig() { return new PropertySourcesPlaceholderConfigurer(); }
}
