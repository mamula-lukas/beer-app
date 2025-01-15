package com.example.beer_app.controller;

import com.example.beer_app.constants.WebConstants;
import com.example.beer_app.model.Beer;
import com.example.beer_app.service.BeerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping(WebConstants.BEER_URL)
public class BeerController {
    private final BeerService beerService;

    @GetMapping
    public ResponseEntity<List<Beer>> list() {
        var result = beerService.getAllBeers();
        if (result.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Beer> get(@PathVariable Long id) {
        var beer = beerService.getBeerById(id);
        return ResponseEntity.ok(beer);
    }
}
