package com.productservice.productservice09april.models;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(value = {"id", "createdAt", "lastUpdated", "isDeleted"}) // Ignore specified properties during serialization
public class Rating extends BaseModel{
    private Double rate;
    private Integer count;
}
