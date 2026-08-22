import os

services = ['agent-service', 'booking-service', 'payment-service', 'property-service', 'auth-service']

for svc in services:
    pkg = svc.split('-')[0]
    filename = 'ApiKeyFilter.java' if svc == 'auth-service' else 'ApiKeyAuthFilter.java'
    path = os.path.join(svc, 'src/main/java/com/estatehub', pkg, 'security', filename)
    
    if os.path.exists(path):
        with open(path, 'r') as f:
            content = f.read()
        
        target1 = 'String apiKeyHeader = request.getHeader("X-API-KEY");'
        target2 = 'String requestApiKey = request.getHeader(API_KEY_HEADER);'
        
        replacement = '''if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
            filterChain.doFilter(request, response);
            return;
        }

        '''
        
        if 'getMethod' not in content:
            if target1 in content:
                content = content.replace(target1, replacement + target1)
            elif target2 in content:
                content = content.replace(target2, replacement + target2)
            
            with open(path, 'w') as f:
                f.write(content)
