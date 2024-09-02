package com.fdifrison.autoconfig.security;

import com.fdifrison.autoconfig.security.interceptors.AuthorizationHeaderInterceptor;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.web.client.RestClientCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

@AutoConfiguration
@ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.SERVLET)
@Import(AuthorizationHeaderInterceptor.class)
public class AuthorizationHeaderInterceptorAutoConfiguration {
    @Bean
    public RestClientCustomizer restClientCustomizer(AuthorizationHeaderInterceptor authorizationHeaderInterceptor) {
        return restClientBuilder -> restClientBuilder.requestInterceptor(authorizationHeaderInterceptor);
    }

    /*
    Add a request interceptor to the rest client configuration
     */
}
