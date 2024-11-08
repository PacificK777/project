package com.productservice.productservice09april.controllers;

import com.productservice.productservice09april.Commons.AuthenticationCommons;
import com.productservice.productservice09april.Exception.InvalidTokenException;
import com.productservice.productservice09april.dtos.RequestBodyProductDTO;
import com.productservice.productservice09april.dtos.UserDTO;
import com.productservice.productservice09april.models.Product;
import com.productservice.productservice09april.services.ProductService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class ProductController {
    ProductService productService;
    private AuthenticationCommons authenticationCommons;

    public ProductController(@Qualifier("selfProductService") ProductService productService,
                             AuthenticationCommons authenticationCommons) {
        this.productService = productService;
        this.authenticationCommons = authenticationCommons;
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
    @GetMapping("/products/{id}/{token}")
    public Product getProductDetails(@PathVariable("id") Long id,@PathVariable("token") String token) throws InvalidTokenException {

        UserDTO userDTO = authenticationCommons.validateToken(token);
        if(userDTO == null){
            //token is invalid
            throw new InvalidTokenException("Invalid token. Please login to get the product details.");
        }
        //token is valid, make a call to product service to fetch the product
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
