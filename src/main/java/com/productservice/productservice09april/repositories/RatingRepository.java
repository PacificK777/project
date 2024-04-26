package com.productservice.productservice09april.repositories;

import com.productservice.productservice09april.models.Rating;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RatingRepository extends JpaRepository<Rating, Long> {
    Rating save(Rating rating);
}
