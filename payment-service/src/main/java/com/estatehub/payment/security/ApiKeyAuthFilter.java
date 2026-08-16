package com.estatehub.payment.security;

import com.estatehub.payment.dto.ErrorResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class ApiKeyAuthFilter extends OncePerRequestFilter {

    private static final String API_KEY_HEADER = "X-API-KEY";
    private final String expectedApiKey;
    private final ObjectMapper mapper = new ObjectMapper();

    public ApiKeyAuthFilter(String expectedApiKey) {
        this.expectedApiKey = expectedApiKey;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String requestPath = request.getRequestURI();
        
        // Skip filter for non-api endpoints explicitly mapped as permitAll in SecurityConfig
        if (requestPath.startsWith("/swagger-ui") || requestPath.startsWith("/v3/api-docs") || requestPath.startsWith("/h2-console")) {
            filterChain.doFilter(request, response);
            return;
        }

        String providedApiKey = request.getHeader(API_KEY_HEADER);

        if (providedApiKey == null || !providedApiKey.equals(expectedApiKey)) {
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            ErrorResponse errorResponse = new ErrorResponse(
                    HttpStatus.UNAUTHORIZED.value(),
                    "Unauthorized",
                    "Missing or invalid X-API-KEY header"
            );
            response.getWriter().write(mapper.writeValueAsString(errorResponse));
            return;
        }

        // Key is valid, establish security context
        ApiKeyAuthenticationToken authentication = new ApiKeyAuthenticationToken(providedApiKey);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        filterChain.doFilter(request, response);
    }
}
