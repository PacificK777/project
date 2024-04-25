package com.productservice.productservice09april.controllers;

import com.productservice.productservice09april.dtos.RequestBodyProductDTO;
import com.productservice.productservice09april.models.Product;
import com.productservice.productservice09april.services.ProductService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class ProductController {
    ProductService productService;

    public ProductController(@Qualifier("selfProductService") ProductService productService) {
        this.productService = productService;
    }
    /*
    Qualifier is used to identify the dependency to be injected here
     */

    // TO CREATE A NEW PRODUCT
    @PostMapping("/products")
    public Product createProduct(@RequestBody RequestBodyProductDTO request){
        return productService.createProduct(request.getTitle(),
                                        request.getDescription(),
                                        request.getPrice(),
                                        request.getCategory(),
                                        request.getImage()
                                        );
    }

    // TO GET SPECIFIC PRODUCT DETAILS
    @GetMapping("/products/{id}")
    public Product getProductDetails(@PathVariable("id") Long id){

        return productService.getSingleProduct(id);
    }

    // TO GET ALL PRODUCTS
    @GetMapping("/products")
    public List<Product> getAllProducts(){
        return productService.getAllProducts();
    }

    // TO UPDATE A SPECIFIC PRODUCT
    @PutMapping("/products/{id}")
    public Product updateProduct(@PathVariable("id") Long id, @RequestBody RequestBodyProductDTO request) {
        return productService.updateProduct(id,
                request.getTitle(),
                request.getDescription(),
                request.getPrice(),
                request.getImage(),
                request.getCategory()
        );
    }

    //TO DELETE A SPECIFIC PRODUCT
    @DeleteMapping("/products/{id}")
    public Product deleteProductByID(@PathVariable("id") Long id){
        return productService.deleteProductById(id);
    }

    @GetMapping("/products/categories")
    public List<String> getAllCategories() {
        return productService.getAllCategories();
    }

    @GetMapping("/products/categories/{category}")
    public List<Product> getProductByCategory(@PathVariable("category") String Category) {
        return productService.getProductsByCategory(Category);
    }
}
