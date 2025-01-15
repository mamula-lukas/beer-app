package com.example.beer_app.service;

import com.example.beer_app.excepion.EntityNotFoundException;
import com.example.beer_app.model.BeerScore;
import com.example.beer_app.repository.BeerScoreRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author mamula.lukas@gmail.com
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class BeerScoreService {

    private final BeerScoreRepository beerScoreRepository;

    @Transactional
    public BeerScore createBeerScore(BeerScore beerScore) {
        return beerScoreRepository.save(beerScore);
    }

    @Transactional
    public BeerScore updateBeerScore(BeerScore beerScore) {
        beerScoreRepository.findById(beerScore.getId())
                .orElseThrow(() -> new EntityNotFoundException("Beer Score not found"));
        return beerScoreRepository.save(beerScore);
    }

    public BeerScore getBeerScoreById(Long id) {
        return beerScoreRepository.findById(String.valueOf(id))
                .orElseThrow(() -> new EntityNotFoundException("Beer Score not found"));
    }

    public List<BeerScore> getAllBeerScores() {
        return beerScoreRepository.findAll();
    }

    public BeerScore getScoreByBeerId(Long id) {
        return beerScoreRepository.findByBeerId(id)
                .orElseThrow(() -> new EntityNotFoundException("Beer Score not found"));
    }
}
