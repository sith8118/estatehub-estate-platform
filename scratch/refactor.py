import os, re, glob

# Find all java files
java_files = []
for root, dirs, files in os.walk('.'):
    for f in files:
        if f.endswith('.java'):
            java_files.append(os.path.join(root, f))

for path in java_files:
    with open(path, 'r', encoding='utf-8') as f:
        content = f.read()
    
    orig_content = content
    
    # 1. Imports
    content = re.sub(r'import jakarta\.persistence\..*?;\n', '', content)
    content = content.replace('import org.springframework.data.jpa.repository.JpaRepository;', 'import org.springframework.data.mongodb.repository.MongoRepository;')
        
    # 2. Entity annotations
    if '@Entity' in content:
        table_match = re.search(r'@Table\(name\s*=\s*"(.*?)"\)', content)
        collection_name = table_match.group(1) if table_match else 'documents'
        
        content = re.sub(r'@Entity', f'@Document(collection = "{collection_name}")', content)
        content = re.sub(r'@Table\(.*?\)\n', '', content)
        
        # Add Mongo imports
        imports = 'import org.springframework.data.annotation.Id;\nimport org.springframework.data.mongodb.core.mapping.Document;\n'
        content = re.sub(r'(package .*?;\n)', r'\1\n' + imports, content, 1)

    # 3. Field annotations
    content = re.sub(r'@GeneratedValue\(.*?\)\n\s*', '', content)
    content = re.sub(r'@Column\(.*?\)\n\s*', '', content)
    content = re.sub(r'@Enumerated\(.*?\)\n\s*', '', content)
    
    # PrePersist / PreUpdate
    content = re.sub(r'@PrePersist', '', content)
    content = re.sub(r'@PreUpdate', '', content)

    # 4. Long id -> String id
    content = re.sub(r'\bLong id\b', 'String id', content)
    content = re.sub(r'\bLong\s+customerId\b', 'String customerId', content)
    content = re.sub(r'\bLong\s+propertyId\b', 'String propertyId', content)
    content = re.sub(r'\bLong\s+agentId\b', 'String agentId', content)
    content = re.sub(r'\bLong\s+bookingId\b', 'String bookingId', content)
    content = re.sub(r'\bLong\s+paymentId\b', 'String paymentId', content)
    
    # Replace JpaRepository<Entity, Long> -> MongoRepository<Entity, String>
    content = re.sub(r'JpaRepository<([^,]+),\s*Long>', r'MongoRepository<\1, String>', content)
    content = re.sub(r'JpaRepository<([^,]+),\s*String>', r'MongoRepository<\1, String>', content)
    
    # Fix any remaining 'Long id;' in DTOs and Entities
    content = re.sub(r'private Long id;', 'private String id;', content)
    content = re.sub(r'private Long customerId;', 'private String customerId;', content)
    content = re.sub(r'private Long propertyId;', 'private String propertyId;', content)
    content = re.sub(r'private Long agentId;', 'private String agentId;', content)
    
    # Also in method signatures: (Long id) -> (String id)
    content = re.sub(r'\(Long id\)', '(String id)', content)
    content = re.sub(r'\(Long customerId\)', '(String customerId)', content)
    
    if content != orig_content:
        with open(path, 'w', encoding='utf-8') as f:
            f.write(content)
