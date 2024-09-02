package com.fdifrison.autoconfig.annotation;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
@ConditionalOnProperty(prefix = "root", value = "some-property", havingValue = "some-value")
public @interface ConditionalAnnotation {}

/*
An annotation that serves as a profiler given a specific the presence of a specific property with a specific value in
the application.yml. In this case, it looks for a "property root.some-property" that should have the value "some-value"
 */