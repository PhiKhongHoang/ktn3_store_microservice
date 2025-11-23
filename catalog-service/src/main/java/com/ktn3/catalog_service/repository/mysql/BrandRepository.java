package com.ktn3.catalog_service.repository.mysql;

import com.ktn3.catalog_service.entity.mysql.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BrandRepository extends JpaRepository<Brand, String>, BrandCriteriaRepository {
    boolean existsByNameIgnoreCase(String name);
    boolean existsBySlugIgnoreCase(String slug);

    boolean existsByNameIgnoreCaseAndIdNot(String name, String id);

    boolean existsBySlugIgnoreCaseAndIdNot(String slug, String id);
}
