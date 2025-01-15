package com.example.beer_app.service;

import com.example.beer_app.excepion.EntityNotFoundException;
import com.example.beer_app.model.Beer;
import com.example.beer_app.repository.BeerRepository;
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
public class BeerService {
    private final BeerRepository beerRepository;

    public List<Beer> getAllBeers() {
        return beerRepository.findAll();
    }

    public Beer getBeerById(Long id) {
        return beerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Beer with id " + id + " does not exists"));
    }

    public long beerCount() {
        return beerRepository.count();
    }

    @Transactional
    public void saveAllBeers(List<Beer> beers) {
        beerRepository.saveAll(beers);
    }
}
