package com.productservice.productservice09april.services;

import com.productservice.productservice09april.dtos.FakeStoreProductDto;
//import com.productservice.productservice09april.models.Rating;
import com.productservice.productservice09april.models.Product;
import jdk.jfr.Category;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service("FakeStoreProductService")
public class FakeStoreProductService implements ProductService{

    private RestTemplate restTemplate;
    public FakeStoreProductService(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }

    @Override
    public Product getSingleProduct(Long productId) {

        if(productId<=0){
            throw new IllegalArgumentException("Invalid Product ID, retry !!");
        }


        ResponseEntity<FakeStoreProductDto> fakeStoreProductResponse = restTemplate.getForEntity(
                "https://fakestoreapi.com/products/" + productId,
                FakeStoreProductDto.class);
        return fakeStoreProductResponse.getBody().toProduct();
    }

    @Override
    public List<Product> getAllProducts() {
        List<Product> products = new ArrayList<>();
       FakeStoreProductDto[] fakeStoreProductDtos =  restTemplate.getForObject(
               "https://fakestoreapi.com/products",
               FakeStoreProductDto[].class);
       for(FakeStoreProductDto fakeStoreProductDto : fakeStoreProductDtos){
           products.add(fakeStoreProductDto.toProduct());
       }
       return products;
    }

    @Override
    public Product createProduct(String title,
                                 String description,
                                 double price,
                                 String category,
                                 String Image) {
        FakeStoreProductDto fakeStoreProductDto = new FakeStoreProductDto();
        fakeStoreProductDto.setTitle(title);
        fakeStoreProductDto.setDescription(description);
        fakeStoreProductDto.setPrice(price);
        fakeStoreProductDto.setCategory(category);
        fakeStoreProductDto.setImage(Image);

        FakeStoreProductDto fakeStoreProductDto1 = restTemplate.postForObject("https://fakestoreapi.com/products",
                fakeStoreProductDto,
                FakeStoreProductDto.class);

        return fakeStoreProductDto1.toProduct();
    }

    @Override
    public Product deleteProductById(Long productId) {
        Product productToDelete = getSingleProduct(productId);
        restTemplate.delete("https://fakestoreapi.com/products/" + productId);
        return productToDelete;


    }

    @Override
    public Product updateProduct(Long id,
                                 String title,
                                 String description,
                                 double price,
                                 String image,
                                 String category) {
        //Get the existing product
        Product existingProduct = getSingleProduct(id);

        if (existingProduct != null) {
            //Create fakeStoreProductDto object and set values
            FakeStoreProductDto fakeStoreProductDto = new FakeStoreProductDto();
            fakeStoreProductDto.setId(id);
            fakeStoreProductDto.setTitle(title);
            fakeStoreProductDto.setDescription(description);
            fakeStoreProductDto.setPrice(price);
            fakeStoreProductDto.setImage(image);
            fakeStoreProductDto.setCategory(category);

            //Use RestTemplate to send the update
            restTemplate.put("https://fakestoreapi.com/products/" + id,
                    fakeStoreProductDto,
                    FakeStoreProductDto.class);

            //Return the updated product
            return fakeStoreProductDto.toProduct();
        }
        throw new IllegalArgumentException("Product not found with id : " + id);
    }

    @Override
    public List<Product> getProductsByCategory(String category) {
        List<Product> productsByCategory = new ArrayList<>();
        FakeStoreProductDto[] fakeStoreProductDtos = restTemplate.getForObject(
                "https://fakestoreapi.com/products/category/" + category,
                FakeStoreProductDto[].class);
        for (FakeStoreProductDto fakeStoreProductDto : fakeStoreProductDtos) {
            productsByCategory.add(fakeStoreProductDto.toProduct());
        }
        return productsByCategory;
    }

    @Override
    public List<String> getAllCategories() {
        List<String> categories = new ArrayList<>();
        List<Product> products = getAllProducts();

        for (Product product : products) {
            String categoryTitle = product.getCategory().getCategoryTitle();
            if (!categories.contains(categoryTitle)) {
                categories.add(categoryTitle);
            }
        }
        return categories;
    }
}
