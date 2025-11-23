package com.ktn3.catalog_service.repository.mysql;

import com.ktn3.catalog_service.entity.mysql.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, String>, CategoryCriteriaRepository {
    boolean existsByNameIgnoreCase(String name);
    boolean existsBySlugIgnoreCase(String slug);

    boolean existsByNameIgnoreCaseAndIdNot(String name, String id);

    boolean existsBySlugIgnoreCaseAndIdNot(String slug, String id);
}
