package com.ktn3.catalog_service.repository.mongo;

import com.ktn3.catalog_service.entity.mongo.ProductDetail;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductDetailRepository extends MongoRepository<ProductDetail, String> {
    Optional<ProductDetail> findByProductId(String productId);
}