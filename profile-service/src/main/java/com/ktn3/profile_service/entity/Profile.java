package com.ktn3.profile_service.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.Instant;
import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity(name = "profiles")
public class Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;

    String userId;

    @Column(length = 512)
    String avatar;
    String username;
    String email;
    String phone;
    String firstName;
    String lastName;
    LocalDate dob;
    String address;

    Instant createdAt;
    Instant updatedAt;

    @PrePersist
    void onCreate() {
        createdAt = Instant.now();
    }

    @PreUpdate
    void onUpdate() {
        updatedAt = Instant.now();
    }

}
