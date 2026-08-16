package com.estatehub.payment.service;

import com.estatehub.payment.dto.NotificationRequest;
import com.estatehub.payment.model.NotificationLog;

import java.util.List;

public interface NotificationService {
    void sendEmail(NotificationRequest request);
    void sendSms(NotificationRequest request);
    List<NotificationLog> getNotificationLogs();
}
