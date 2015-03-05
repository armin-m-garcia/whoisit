package com.inpowered.whoisit.spring.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan(basePackages = "com.inpowered")
@PropertySource(value = { "classpath:twitter.properties" })
public class WhoIsItConfiguration {

}
