package com.estatehub.payment.dto;

import com.estatehub.payment.model.NotificationLog.NotificationType;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Schema(description = "Request object for dispatching a notification")
public class NotificationRequest {

    @NotBlank(message = "Recipient is required")
    @Schema(description = "Email address or Phone number", example = "user@example.com")
    private String recipient;

    @NotNull(message = "Type is required")
    @Schema(description = "Type of notification", example = "EMAIL")
    private NotificationType type;

    @NotBlank(message = "Subject is required")
    @Schema(description = "Notification Subject/Title", example = "Payment Confirmation")
    private String subject;

    @NotBlank(message = "Message is required")
    @Schema(description = "Body of the notification", example = "Your payment of 1500 was successful.")
    private String message;
}
