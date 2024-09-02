package com.fdifrison.autoconfig.security.interceptors;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class AuthorizationHeaderInterceptor implements ClientHttpRequestInterceptor {
    private final HttpServletRequest currentRequest;

    public AuthorizationHeaderInterceptor(HttpServletRequest currentRequest) {
        this.currentRequest = currentRequest;
    }

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution)
            throws IOException {
        var authorization = currentRequest.getHeader(HttpHeaders.AUTHORIZATION);
        if (authorization != null) {
            request.getHeaders().add(HttpHeaders.AUTHORIZATION, authorization);
        }
        return execution.execute(request, body);
    }

    /*
    Check if the current request has an authorization header to propagating it in the chain.
     */
}
