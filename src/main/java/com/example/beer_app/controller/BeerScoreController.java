package com.example.beer_app.controller;

import com.example.beer_app.constants.WebConstants;
import com.example.beer_app.model.BeerScore;
import com.example.beer_app.service.BeerScoreService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

/**
 * @author mamula.lukas@gmail.com
 */
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping(WebConstants.BEER_SCORE_URL)
public class BeerScoreController {

    private final BeerScoreService beerScoreService;

    @PostMapping
    public ResponseEntity<BeerScore> createBeerScore(@RequestBody BeerScore beerScore) {
        var created = beerScoreService.createBeerScore(beerScore);
        var createdUri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(createdUri).body(created);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<BeerScore> update(@PathVariable("id") String id,
                                            @RequestBody BeerScore beerScore) {
        return ResponseEntity.ok(beerScoreService.updateBeerScore(beerScore));
    }

    @GetMapping("/{id}")
    public ResponseEntity<BeerScore> get(@PathVariable Long id) {
        var beer = beerScoreService.getBeerScoreById(id);
        return ResponseEntity.ok(beer);
    }

    @GetMapping("/{beerId}/score-of-beer")
    public ResponseEntity<BeerScore> getByBeerId(@PathVariable Long id) {
        var beer = beerScoreService.getBeerScoreById(id);
        return ResponseEntity.ok(beer);
    }
}
