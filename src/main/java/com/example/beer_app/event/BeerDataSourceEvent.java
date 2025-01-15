package com.example.beer_app.event;

import com.example.beer_app.model.Beer;
import com.example.beer_app.service.BeerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * @author mamula.lukas@gmail.com
 */
@Component
@Slf4j
@RequiredArgsConstructor
public class BeerDataSourceEvent {
    private static final String BEER_DATA_SOURCE_URL = "https://random-data-api.com/api/v2/beers?size=100";

    private final BeerService beerService;

    @EventListener(ApplicationReadyEvent.class)
    public void startApp() {
        log.info("Starting application. Loading data from the source");
        long count =  beerService.beerCount();
        if (count > 0) {
            log.info("Data already exists");
            return;
        }
        Beer[] beers = loadBeerData();
        if (beers == null) {
            log.info("Cannot download data from the source");
            return;
        }
        beerService.saveAllBeers(List.of(beers));
    }


    private Beer[] loadBeerData() {
        var restTemplate = new RestTemplate();
        return restTemplate.getForObject(BEER_DATA_SOURCE_URL, Beer[].class);
    }
}
