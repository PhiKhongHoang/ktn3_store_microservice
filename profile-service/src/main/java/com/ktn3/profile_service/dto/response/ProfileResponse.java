package com.ktn3.profile_service.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.Instant;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProfileResponse {
    String id;
    String userId;
    String username;
    String avatar;
    String email;
    String phone;
    String firstName;
    String lastName;
    LocalDate dob;
    String address;
    Instant createdAt;
    Instant updatedAt;
}
