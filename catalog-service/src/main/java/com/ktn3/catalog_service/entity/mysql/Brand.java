package com.ktn3.catalog_service.entity.mysql;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "brands")
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Brand {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;

    @Column(nullable = false, unique = true, length = 100)
    String name;

    @Column(nullable = false, unique = true, length = 100)
    String slug;

    String description;

    @ManyToOne
    @JoinColumn(name = "parent_id")
    Brand parent;

    Boolean active = true;

}
