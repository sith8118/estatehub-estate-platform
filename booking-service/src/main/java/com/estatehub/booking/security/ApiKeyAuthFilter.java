package com.estatehub.booking.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class ApiKeyAuthFilter extends OncePerRequestFilter {

    private final String expectedApiKey = "viva-super-secret-key";

    public ApiKeyAuthFilter(String unused) {
        // Constructor maintained for compatibility with SecurityConfig
    }
    
    public ApiKeyAuthFilter() {
        // Default constructor
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String path = request.getRequestURI();

        // Whitelist Exceptions
        if (path.contains("/swagger-ui") || path.contains("/v3/api-docs") || path.contains("/api-docs")) {
            filterChain.doFilter(request, response);
            return;
        }

        String apiKeyHeader = request.getHeader("X-Internal-API-Key");

        if (apiKeyHeader == null || !apiKeyHeader.equals(expectedApiKey)) {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            response.setContentType("application/json");
            response.getWriter().write("{\"error\": \"Forbidden\", \"message\": \"Missing or invalid X-Internal-API-Key.\"}");
            return;
        }

        filterChain.doFilter(request, response);
    }
}
