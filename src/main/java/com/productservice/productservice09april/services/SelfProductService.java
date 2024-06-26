package com.productservice.productservice09april.services;

import com.productservice.productservice09april.models.Category;
import com.productservice.productservice09april.models.Product;
import com.productservice.productservice09april.repositories.CategoryRepository;
import com.productservice.productservice09april.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
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
        It need to confirm if already the category exists
         */
        Category categoryFromDatabase = categoryRepository.findByCategoryTitle(category);

        if(categoryFromDatabase == null){
            Category category1 = new Category();
            category1.setCategoryTitle(category);
            category1.setCreatedAt(new Date());
            categoryFromDatabase = category1;
        }
        p.setCreatedAt(new Date());
        p.setCategory(categoryFromDatabase);

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
        return productRepository.findAllByCategory(categoryRepository.findByCategoryTitle(category));
    }

    @Override
    public List<String> getAllCategories() {
        return null;
    }
    @Override
    public Product getSingleProduct(Long productId) {
        return productRepository.findByIdIs(productId);
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }
}
