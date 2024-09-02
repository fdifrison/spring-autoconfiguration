package com.fdifrison.autoconfig.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
public class ExampleSecurityConfig {

    /*
    This is the way to add specific path with specific http methods to security filter chain
     */

    @Bean
    public DefaultSecurityAutoConfiguration.PublicUrlCustomizer publicUrlCustomizer() {
        return publicUrl -> publicUrl.add(AntPathRequestMatcher.antMatcher(HttpMethod.GET, "/some-endpoint-goes-public"));
    }

}
