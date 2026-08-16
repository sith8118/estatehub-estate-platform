package com.estatehub.payment.controller;

import com.estatehub.payment.dto.NotificationRequest;
import com.estatehub.payment.model.NotificationLog;
import com.estatehub.payment.service.NotificationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/notifications")
@RequiredArgsConstructor
@Tag(name = "Notification Management", description = "Endpoints for dispatching Emails/SMS and checking logs")
@SecurityRequirement(name = "ApiKeyAuth")
public class NotificationController {

    private final NotificationService notificationService;

    @PostMapping("/email")
    @Operation(summary = "Send Email Notification", description = "Dispatches a mock email via JavaMailSender and saves the log.")
    public ResponseEntity<String> sendEmail(@Valid @RequestBody NotificationRequest request) {
        // Enforce type
        request.setType(NotificationLog.NotificationType.EMAIL);
        notificationService.sendEmail(request);
        return ResponseEntity.ok("Email dispatch initiated.");
    }

    @PostMapping("/sms")
    @Operation(summary = "Send SMS Notification", description = "Simulates SMS dispatch and saves the log.")
    public ResponseEntity<String> sendSms(@Valid @RequestBody NotificationRequest request) {
        // Enforce type
        request.setType(NotificationLog.NotificationType.SMS);
        notificationService.sendSms(request);
        return ResponseEntity.ok("SMS dispatch initiated.");
    }

    @GetMapping("/logs")
    @Operation(summary = "Get Notification Logs", description = "Retrieves the history of all dispatched notifications.")
    public ResponseEntity<List<NotificationLog>> getNotificationLogs() {
        return ResponseEntity.ok(notificationService.getNotificationLogs());
    }
}
