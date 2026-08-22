import os

exception_handler_content = """package {package_name}.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Object> handleRuntimeException(RuntimeException ex) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        
        if (ex.getMessage() != null && (ex.getMessage().contains("not found") || ex.getMessage().contains("Not found") || ex.getMessage().contains("No value present"))) {
            body.put("status", HttpStatus.NOT_FOUND.value());
            body.put("error", "Not Found");
            body.put("message", ex.getMessage());
            return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
        } else if (ex.getMessage() != null && (ex.getMessage().contains("already exists") || ex.getMessage().contains("already processed") || ex.getMessage().contains("Invalid"))) {
            body.put("status", HttpStatus.BAD_REQUEST.value());
            body.put("error", "Bad Request");
            body.put("message", ex.getMessage());
            return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
        } else if (ex instanceof IllegalArgumentException) {
            body.put("status", HttpStatus.BAD_REQUEST.value());
            body.put("error", "Bad Request");
            body.put("message", ex.getMessage());
            return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
        }
        
        body.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
        body.put("error", "Internal Server Error");
        body.put("message", ex.getMessage());
        return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Object> handleIllegalArgumentException(IllegalArgumentException ex) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("status", HttpStatus.BAD_REQUEST.value());
        body.put("error", "Bad Request");
        body.put("message", ex.getMessage());
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }
}
"""

services = [
    {"dir": "auth-service", "package": "com.estatehub.auth"},
    {"dir": "property-service", "package": "com.estatehub.property"},
    {"dir": "agent-service", "package": "com.estatehub.agent"},
    {"dir": "booking-service", "package": "com.estatehub.booking"},
    {"dir": "payment-service", "package": "com.estatehub.payment"}
]

for service in services:
    package_path = service["package"].replace(".", "/")
    target_dir = f"{service['dir']}/src/main/java/{package_path}/exception"
    os.makedirs(target_dir, exist_ok=True)
    file_path = f"{target_dir}/GlobalExceptionHandler.java"
    with open(file_path, "w") as f:
        f.write(exception_handler_content.replace("{package_name}", service["package"]))
    print(f"Created {file_path}")
