package com.estatehub.payment.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Document(collection = "notification_logs")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NotificationLog {

    @Id
    private String id;

    private String recipient;

    private NotificationType type;

    private String subject;

    private String message;

    private NotificationStatus status;

    private LocalDateTime sentAt;

    
    protected void onCreate() {
        this.sentAt = LocalDateTime.now();
    }

    public enum NotificationType {
        EMAIL, SMS
    }

    public enum NotificationStatus {
        PENDING, SENT, FAILED
    }
}
