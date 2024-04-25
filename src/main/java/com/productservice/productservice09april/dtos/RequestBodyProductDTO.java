package com.productservice.productservice09april.dtos;

import com.productservice.productservice09april.models.Category;
import lombok.Getter;
import lombok.Setter;
import org.springframework.transaction.CannotCreateTransactionException;

@Getter
@Setter

public class RequestBodyProductDTO {
    private String title;
    private double price;
    private String description;
    private String image;
    private String category;
}
