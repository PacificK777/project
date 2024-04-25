package com.productservice.productservice09april.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
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

//@ManyToOne
//    private Rating rating;
@ManyToOne(cascade = {CascadeType.PERSIST}) //m:1
@JsonIgnore
    private Category category;
}
