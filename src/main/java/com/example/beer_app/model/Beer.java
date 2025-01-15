package com.example.beer_app.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author mamula.lukas@gmail.com
 */
@Data
@Document( collection = "Beers")
public class Beer {
    @Id
    private Long id;
    private String uid;
    private String brand;
    private String name;
    private String style;
    private String hop;
    private String yeast;
    private String malts;
    private String ibu;
    private String alcohol;
    private String blg;
}
