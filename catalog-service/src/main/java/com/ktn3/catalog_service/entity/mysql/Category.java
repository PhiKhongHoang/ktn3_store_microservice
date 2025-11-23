package com.ktn3.catalog_service.entity.mysql;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "categories")
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Category {

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
    Category parent;

    Boolean active = true;

}
