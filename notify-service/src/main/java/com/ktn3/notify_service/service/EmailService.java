package com.ktn3.notify_service.service;

import com.ktn3.notify_service.dto.request.SendEmailRequest;
import com.ktn3.notify_service.dto.response.EmailResponse;

public interface EmailService {

    EmailResponse sendEmail(SendEmailRequest request);

}
