package com.productservice.productservice09april.services;

import com.productservice.productservice09april.models.Product;
import java.util.List;

public interface ProductService {

    Product getSingleProduct(Long productId);


    List<Product> getAllProducts();

    Product createProduct(String title,
                          String description,
                          double price,
                          String category,
                          String Image
                           );
    Product deleteProductById(Long productId);

    Product updateProduct(Long id,
                          String title,
                          String description,
                          double price,
                          String image,
                          String category);

    List<Product> getProductsByCategory(String category);

    List<String> getAllCategories();
}
