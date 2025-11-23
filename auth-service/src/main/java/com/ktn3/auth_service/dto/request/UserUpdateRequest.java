package com.ktn3.auth_service.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserUpdateRequest {
    String password;
    String phone;
    String firstName;
    String lastName;
    LocalDate dob;
    String address;

    List<String> roles;
}
