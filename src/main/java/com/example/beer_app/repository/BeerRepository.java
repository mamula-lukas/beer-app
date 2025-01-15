package com.example.beer_app.repository;

import com.example.beer_app.model.Beer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @author mamula.lukas@gmail.com
 */
@Repository
public interface BeerRepository extends MongoRepository<Beer, Long> {
}
