package com.fdifrison.autoconfig.security;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.CorsConfigurer;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

@AutoConfiguration
@ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.SERVLET)
@ConditionalOnClass({SecurityFilterChain.class, HttpSecurity.class})
@EnableMethodSecurity
@Import(JwtService.class)
public class DefaultSecurityAutoConfiguration {
    private final List<RequestMatcher> PUBLIC_MATCHERS = new ArrayList<>();

    public DefaultSecurityAutoConfiguration() {
        PUBLIC_MATCHERS.add(AntPathRequestMatcher.antMatcher(HttpMethod.GET, "/swagger-ui.html"));
        PUBLIC_MATCHERS.add(AntPathRequestMatcher.antMatcher(HttpMethod.GET, "/swagger-ui/**"));
        PUBLIC_MATCHERS.add(AntPathRequestMatcher.antMatcher(HttpMethod.GET, "/v3/api-docs/**"));
        PUBLIC_MATCHERS.add(AntPathRequestMatcher.antMatcher(HttpMethod.GET, "/actuator/**"));
        PUBLIC_MATCHERS.add(AntPathRequestMatcher.antMatcher("/error"));
    }

    @Bean
    public SecurityFilterChain defaultSecurityFilterChain(
            HttpSecurity http, List<PublicUrlCustomizer> publicUrlCustomizers) throws Exception {
        publicUrlCustomizers.forEach(p -> p.accept(PUBLIC_MATCHERS));
        return http.authorizeHttpRequests(
                        request -> request.requestMatchers(PUBLIC_MATCHERS.toArray(RequestMatcher[]::new))
                                .permitAll()
                                .anyRequest()
                                .authenticated())
                .oauth2ResourceServer(Customizer.withDefaults())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .cors(CorsConfigurer::disable)
                .csrf(CsrfConfigurer::disable)
                .build();
    }

    @FunctionalInterface
    public interface PublicUrlCustomizer extends Consumer<List<RequestMatcher>> {
    }
}
