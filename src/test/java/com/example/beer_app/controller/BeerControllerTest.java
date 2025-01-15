package com.example.beer_app.controller;

import com.example.beer_app.constants.WebConstants;
import com.example.beer_app.model.Beer;
import com.example.beer_app.service.BeerService;
import com.fasterxml.jackson.core.type.TypeReference;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author mamula.lukas@gmail.com
 */
public class BeerControllerTest extends AbstractMvcTest {

    @Autowired
    private BeerService beerService;

    @Test
    public void testList() throws Exception {
        String result = mvc.perform(setHeaders(get(WebConstants.BEER_URL)))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        List<Beer> list = deserialize(result, new TypeReference<>() {
        });
        assertNotNull(list);
    }

    @Test
    public void testGetBeer() throws Exception {
        List<Beer> beerList = beerService.getAllBeers();
        Beer beer = beerList.getFirst();

        String result = mvc.perform(setHeaders(get(WebConstants.BEER_URL + "/{id}", beer.getId())))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        Beer resultBeer = deserialize(result, Beer.class);
        assertNotNull(resultBeer);
    }

    @Test
    public void testGetBeer_Fail() throws Exception {
        String result = mvc.perform(setHeaders(get(WebConstants.BEER_URL + "/{id}", Long.MAX_VALUE)))
                .andExpect(status().isNotFound())
                .andReturn()
                .getResponse()
                .getContentAsString();
        ;
    }
}
