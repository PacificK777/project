package com.productservice.productservice09april.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Product extends BaseModel{
    private String title;
    private double price;
    private String description;
    private String image;

    @OneToOne(cascade = CascadeType.ALL)
    private Rating rating;

    @ManyToOne(cascade = {CascadeType.PERSIST}) //m:1
    private Category category;
}