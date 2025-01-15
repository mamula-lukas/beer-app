package com.example.beer_app.utils;

import com.example.beer_app.model.BeerScore;
import lombok.extern.slf4j.Slf4j;

/**
 * @author mamula.lukas@gmail.com
 */
@Slf4j
public abstract class ModelUtils {
    /**
     * Creates a new BeerScore object and initializes its properties with predefined values.
     *
     * @return a BeerScore object with preset values for score, userId, userName, email, and comment.
     */
    public static BeerScore createBeerScore() {
        var beerScore = new BeerScore();
        beerScore.setScore(5);
        beerScore.setUserId(String.valueOf(1L));
        beerScore.setComment("Lorem ipsum dolor sit amet, consectetur adipiscing elit.");
        beerScore.setEmail("useremail@user.com");
        beerScore.setUserName("User Name");
        return beerScore;
    }
}
