package com.example.beer_app.controller;

import com.example.beer_app.constants.WebConstants;
import com.example.beer_app.model.BeerScore;
import com.example.beer_app.service.BeerScoreService;
import com.example.beer_app.service.BeerService;
import com.example.beer_app.utils.ModelUtils;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author mamula.lukas@gmail.com
 */
@RequiredArgsConstructor
public class BeerScoreControllerTest extends AbstractMvcTest {

    @Autowired
    private BeerService beerService;

    @Autowired
    private BeerScoreService beerScoreService;

    @Test
    void testCreateBeerScore() throws Exception {
        var beerScore = getBeerScore();

        var result = mvc.perform(
                        setHeaders(post(WebConstants.BEER_SCORE_URL)
                                .content(serialize(beerScore))))
                .andExpect(status().isCreated())
                .andReturn().getResponse().getContentAsString();
        BeerScore saved = deserialize(result, BeerScore.class);
        assertNotNull(saved);
        assertNotNull(saved.getId());
    }

    @Test
    void testUpdate() throws Exception {
        List<BeerScore> beerScoreList = beerScoreService.getAllBeerScores();
        BeerScore beerScore = (beerScoreList.isEmpty())
                ? beerScoreService.createBeerScore(getBeerScore())
                : beerScoreList.getFirst();
        int oldScore = beerScore.getScore();
        int newScore = (oldScore < 5)
                ? oldScore + 1
                : oldScore - 1;
        beerScore.setScore(newScore);
        var result = mvc.perform(
                        setHeaders(patch(WebConstants.BEER_SCORE_URL + "/{id}", beerScore.getId())
                                .content(serialize(beerScore))))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        BeerScore saved = deserialize(result, BeerScore.class);
        assertNotNull(saved);
        assertNotNull(saved.getId());
        assertNotEquals(oldScore, saved.getScore());
    }

    private BeerScore getBeerScore() {
        var beerScore = ModelUtils.createBeerScore();
        var beerList = beerService.getAllBeers();
        beerScore.setBeerId(String.valueOf(beerList.getFirst().getId()));
        return beerScore;
    }
}
