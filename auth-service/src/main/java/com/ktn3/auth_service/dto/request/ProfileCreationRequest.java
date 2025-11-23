package com.ktn3.auth_service.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProfileCreationRequest {
    String userId;
    String username;
    String email;
    String phone;
    String firstName;
    String lastName;
    LocalDate dob;
    String address;
}
