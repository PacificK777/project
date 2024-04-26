package com.productservice.productservice09april.repositories;

import com.productservice.productservice09april.models.Category;
import com.productservice.productservice09april.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Category findByCategoryTitle(String title);
    Category save(Category category);




}
