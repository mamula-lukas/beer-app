package com.example.beer_app.repository;

import com.example.beer_app.model.BeerScore;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

/**
 * @author mamula.lukas@gmail.com
 */
public interface BeerScoreRepository extends MongoRepository<BeerScore, String> {
    Optional<BeerScore> findByBeerId(Long beerId);
}
