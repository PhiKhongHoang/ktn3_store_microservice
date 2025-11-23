package com.ktn3.notify_service.service.impl;

import com.ktn3.notify_service.dto.request.EmailRequest;
import com.ktn3.notify_service.dto.request.SendEmailRequest;
import com.ktn3.notify_service.dto.request.Sender;
import com.ktn3.notify_service.dto.response.EmailResponse;
import com.ktn3.notify_service.exception.AppException;
import com.ktn3.notify_service.exception.ErrorCode;
import com.ktn3.notify_service.repository.httpclient.EmailClient;
import com.ktn3.notify_service.service.EmailService;
import feign.FeignException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class EmailServiceImpl implements EmailService {
    EmailClient emailClient;

    @Value("${notification.email.sender}")
    @NonFinal
    String name;

    @Value("${notification.email.from}")
    @NonFinal
    String email;

    @Value("${notification.email.brevo-apikey}")
    @NonFinal
    String apiKey;

    @Override
//    @Async
    public EmailResponse sendEmail(SendEmailRequest request) {
        EmailRequest emailRequest = EmailRequest.builder()
                .sender(Sender.builder()
                        .name(name)
                        .email(email)
                        .build())
                .to(List.of(request.getTo()))
                .subject(request.getSubject())
                .htmlContent(request.getHtmlContent())
                .build();
        try {
            return emailClient.sendEmail(apiKey, emailRequest);
        } catch (FeignException e){
            throw new AppException(ErrorCode.CANNOT_SEND_EMAIL);
        }
    }

}
