package com.productservice.productservice09april.dtos;

import com.productservice.productservice09april.models.Category;
import com.productservice.productservice09april.models.Product;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FakeStoreProductDto {
    private Long id;
    private String title;
    private String description;
    private double price;
    private String image;
    private String category;
    private double rate;
    private int count;
//    private Rating rating;

    public Product toProduct(){
        Product product = new Product();
        product.setId(id);
        product.setTitle(title);
        product.setPrice(price);
        product.setDescription(description);
        product.setImage(image);

//        Rating rate = new Rating();
//        rate.setRate(getRate());
//        rate.setCount(getCount());
//
//        product.setRating(rating);

        Category category1 = new Category();
        category1.setCategoryTitle(category);

        product.setCategory(category1);

        return product;
    }
}
