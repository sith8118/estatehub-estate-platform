import os

api_key_filter_content = """package {package_name}.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class {class_name} extends OncePerRequestFilter {

    private final String expectedApiKey = "viva-super-secret-key";

    public {class_name}(String unused) {
        // Constructor maintained for compatibility with SecurityConfig
    }
    
    public {class_name}() {
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
            response.getWriter().write("{\\"error\\": \\"Forbidden\\", \\"message\\": \\"Missing or invalid X-Internal-API-Key.\\"}");
            return;
        }

        filterChain.doFilter(request, response);
    }
}
"""

services = {
    "auth-service": {"package": "com.estatehub.auth", "class": "ApiKeyFilter"},
    "property-service": {"package": "com.estatehub.property", "class": "ApiKeyAuthFilter"},
    "agent-service": {"package": "com.estatehub.agent", "class": "ApiKeyAuthFilter"},
    "booking-service": {"package": "com.estatehub.booking", "class": "ApiKeyAuthFilter"},
    "payment-service": {"package": "com.estatehub.payment", "class": "ApiKeyAuthFilter"}
}

for service, meta in services.items():
    file_path = f"{service}/src/main/java/{meta['package'].replace('.', '/')}/security/{meta['class']}.java"
    if os.path.exists(file_path):
        with open(file_path, "w") as f:
            content = api_key_filter_content.replace("{package_name}", meta['package']).replace("{class_name}", meta['class'])
            if service == "auth-service":
                content = content.replace("public class ApiKeyFilter", "@Component\\npublic class ApiKeyFilter")
            f.write(content)
            print(f"Updated {file_path}")
        
app_yml = "api-gateway/src/main/resources/application.yml"
with open(app_yml, "r") as f:
    content = f.read()

content = content.replace("X-API-KEY, ESTATEHUB_SECRET_KEY", "X-Internal-API-Key, viva-super-secret-key")
with open(app_yml, "w") as f:
    f.write(content)
print("Updated application.yml")
