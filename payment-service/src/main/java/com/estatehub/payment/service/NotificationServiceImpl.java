package com.estatehub.payment.service;

import com.estatehub.payment.dto.NotificationRequest;
import com.estatehub.payment.model.NotificationLog;
import com.estatehub.payment.model.NotificationLog.NotificationStatus;
import com.estatehub.payment.repository.NotificationLogRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationServiceImpl implements NotificationService {

    private final JavaMailSender mailSender;
    private final NotificationLogRepository notificationLogRepository;

    @Override
    @Transactional
    public void sendEmail(NotificationRequest request) {
        log.info("Preparing to send email to {}", request.getRecipient());
        NotificationLog notificationLog = NotificationLog.builder()
                .recipient(request.getRecipient())
                .type(NotificationLog.NotificationType.EMAIL)
                .subject(request.getSubject())
                .message(request.getMessage())
                .status(NotificationStatus.PENDING) // Temporary state before save
                .sentAt(LocalDateTime.now())
                .build();

        try {
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setTo(request.getRecipient());
            mailMessage.setSubject(request.getSubject());
            mailMessage.setText(request.getMessage());
            mailMessage.setFrom("no-reply@estatehub.com");

            mailSender.send(mailMessage);
            
            log.info("Email sent successfully to {}", request.getRecipient());
            notificationLog.setStatus(NotificationStatus.SENT);
        } catch (Exception e) {
            log.error("Failed to send email to {}", request.getRecipient(), e);
            notificationLog.setStatus(NotificationStatus.FAILED);
        }

        notificationLogRepository.save(notificationLog);
    }

    @Override
    @Transactional
    public void sendSms(NotificationRequest request) {
        log.info("Simulating SMS dispatch to {}", request.getRecipient());
        
        // Mock SMS Gateway dispatch
        boolean success = true; // Simulating success
        
        NotificationLog notificationLog = NotificationLog.builder()
                .recipient(request.getRecipient())
                .type(NotificationLog.NotificationType.SMS)
                .subject(request.getSubject())
                .message(request.getMessage())
                .status(success ? NotificationStatus.SENT : NotificationStatus.FAILED)
                .sentAt(LocalDateTime.now())
                .build();

        notificationLogRepository.save(notificationLog);
        log.info("SMS processed with status {} for {}", notificationLog.getStatus(), request.getRecipient());
    }

    @Override
    public List<NotificationLog> getNotificationLogs() {
        return notificationLogRepository.findAll();
    }
}
