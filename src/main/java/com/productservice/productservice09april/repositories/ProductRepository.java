package com.productservice.productservice09april.repositories;

import com.productservice.productservice09april.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Long> {
    Product save(Product product);
}
