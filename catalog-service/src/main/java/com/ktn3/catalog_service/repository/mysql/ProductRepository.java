package com.ktn3.catalog_service.repository.mysql;

import com.ktn3.catalog_service.entity.mysql.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, String>, ProductCriteriaRepository {
}