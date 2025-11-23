package com.ktn3.profile_service.dto.request;

import jakarta.persistence.Column;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AvatarUpdateRequest {
    @Column(length = 512)
    String avatar;
}
