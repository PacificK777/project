package com.productservice.productservice09april.services;

import com.productservice.productservice09april.models.Category;
import com.productservice.productservice09april.models.Product;
import com.productservice.productservice09april.repositories.CategoryRepository;
import com.productservice.productservice09april.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service("selfProductService")
public class SelfProductService implements ProductService{

    private CategoryRepository categoryRepository;
    private ProductRepository productRepository;

    public SelfProductService(CategoryRepository categoryRepository, ProductRepository productRepository) {
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
    }

    @Override
    public Product createProduct(String title,
                                 String description,
                                 double price,
                                 String category,
                                 String image) {
        Product p = new Product();
        p.setTitle(title);
        p.setDescription(description);
        p.setPrice(price);
        p.setImage(image);
        /*
        I need to confirm if already the category exists
         */
        Category categoryFromDatabase = categoryRepository.findByCategoryTitle(category);

        if(categoryFromDatabase == null){
            Category category1 = new Category();
            category1.setCategoryTitle(category);
            categoryFromDatabase = category1;
        }

        p.setCategory(categoryFromDatabase); //persist as cascade type

        Product savedProduct = productRepository.save(p);
        return savedProduct;
    }

    @Override
    public Product deleteProductById(Long productId) {
        return null;
    }

    @Override
    public Product updateProduct(Long id,
                                 String title,
                                 String description,
                                 double price,
                                 String image,
                                 String category) {
        return null;
    }

    @Override
    public List<Product> getProductsByCategory(String category) {
        return List.of();
    }

    @Override
    public List<String> getAllCategories() {
        return List.of();
    }
    @Override
    public Product getSingleProduct(Long productId) {
        return null;
    }

    @Override
    public List<Product> getAllProducts() {
        return List.of();
    }
}
