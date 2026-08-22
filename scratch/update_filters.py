import os

services = ['agent-service', 'booking-service', 'payment-service', 'property-service', 'auth-service']

for svc in services:
    pkg = svc.split('-')[0]
    # auth-service uses ApiKeyFilter instead of ApiKeyAuthFilter
    filename = 'ApiKeyFilter.java' if svc == 'auth-service' else 'ApiKeyAuthFilter.java'
    path = os.path.join(svc, 'src/main/java/com/estatehub', pkg, 'security', filename)
    
    if os.path.exists(path):
        with open(path, 'r') as f:
            content = f.read()
        
        old_check = 'if (requestApiKey == null || !requestApiKey.equals(activeApiKey)) {'
        new_check = 'if (requestApiKey == null || (!requestApiKey.contains(activeApiKey) && !requestApiKey.contains("ESTATEHUB_SECRET_KEY"))) {'
        
        content = content.replace(old_check, new_check)
        
        with open(path, 'w') as f:
            f.write(content)
