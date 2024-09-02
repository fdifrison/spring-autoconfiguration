package com.fdifrison.autoconfig.properties;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.PropertySource;

@AutoConfiguration
@ConditionalOnWebApplication
@PropertySource("classpath:/properties/some.spring-security.properties")
public class PropertiesAutoConfiguration {}

// Autoconfigure specific spring boot properties to the values defined in the /properties/some.spring-security.properties