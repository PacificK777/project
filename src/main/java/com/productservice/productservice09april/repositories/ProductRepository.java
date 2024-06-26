package com.productservice.productservice09april.repositories;

import com.productservice.productservice09april.models.Category;
import com.productservice.productservice09april.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Long> {
    Product save(Product product);

    Product findByIdIs(long id);


    List<Product> findAll();

    List<Product> findAllByCategory(Category category);


}
